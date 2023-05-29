package com.bosgii.internshipmanagement.services;

import java.util.Optional;

import javax.print.attribute.standard.Media;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.entities.Submission;
import com.bosgii.internshipmanagement.entities.Version;
import com.bosgii.internshipmanagement.enums.InternshipType;
import com.bosgii.internshipmanagement.enums.SubmissionStatus;
import com.bosgii.internshipmanagement.enums.VersionStatus;
import com.bosgii.internshipmanagement.repos.SubmissionRepository;
import com.bosgii.internshipmanagement.repos.VersionRepository;
import com.bosgii.internshipmanagement.requests.ChangeSubmissionRequest;
import com.bosgii.internshipmanagement.requests.FinalizeSubmissionRequest;

@Service
public class SubmissionService {
	
	private SubmissionRepository submissionRepository;
	private VersionRepository versionRepository;
	private InternshipService internshipService;
	private DocumentService documentService;
	
	public SubmissionService(SubmissionRepository submissionRepository, VersionRepository versionRepository, InternshipService internshipService, DocumentService documentService) {
		this.submissionRepository = submissionRepository;
		this.versionRepository = versionRepository;
		this.internshipService = internshipService;
		this.documentService = documentService;
	}
	

	public Optional<Submission> findSubmission(Optional<Long> internshipId, Optional<Long> studentId,
			Optional<InternshipType> type) {
		if(internshipId.isPresent()) {
			return submissionRepository.findSubmissionByInternshipId(internshipId.get());
		} else if(studentId.isPresent() && type.isPresent()) {
			return findSubmissionByStudentIdAndInternshipType(studentId.get(), type.get());
		}
		
		return Optional.empty();
	}
	
	public Submission getOneSubmissionById(Long SubmissionId) {
		return submissionRepository.findById(SubmissionId).orElse(null);
	}
	
	private Optional<Submission> findSubmissionByStudentIdAndInternshipType(Long studentId, InternshipType internshipType){
		Optional<Internship> i = internshipService.findInternshipByStudentIdAndType(studentId, internshipType);
		if(i.isPresent()) {
			return findSubmissionOfAnInternship(i.get().getId());
		}
		
		return Optional.empty();
	}

	public Optional<Submission> findSubmissionOfAnInternship(Long internshipId) {
		return submissionRepository.findSubmissionByInternshipId(internshipId);
	}

	public Submission addSubmissionOnAnInternship(Long internshipId) {
		Optional<Internship> internship = internshipService.getOneInternshipById(internshipId);

		if(internship.isEmpty())
			return null;
		
		internshipService.handleNewSubmission(internshipId);

		Submission submission = new Submission();
		submission.setStatus(SubmissionStatus.UNDER_FORMAT_CHECK);//dikkat 
		submission.setNumOfVersions(0);
		submission.setInternship(internship.get());
		return submissionRepository.save(submission);
	}

	public Submission changeSubmission(Long submissionId, ChangeSubmissionRequest req) {
		Optional<Submission> submission = submissionRepository.findById(submissionId);

		if(submission.isPresent()){
			Submission foundsSubmission = submission.get();
			foundsSubmission.setStatus(req.getSubmissionStatus());
			submissionRepository.save(foundsSubmission);
			return foundsSubmission;
		}

		return null;
	}

	public Submission deleteSubmission(Long submissionId) {
		
		Optional<Submission> submission = submissionRepository.findById(submissionId);
		submissionRepository.deleteById(submissionId);
		return submission.get();
		
	}

	public void handleNewVersion(Long submissionId){
		Submission s = submissionRepository.findById(submissionId).get();
		s.setNumOfVersions(s.getNumOfVersions() + 1);
		submissionRepository.save(s);
		internshipService.handleNewVersion(s.getInternship());
	}

	public Submission getOneSubmissionByInternshipId(Long internshipId) {
		return submissionRepository.findSubmissionByInternshipId(internshipId).get();
	}

    public Submission finalizeSubmission(Long submissionId, FinalizeSubmissionRequest req) {
		Submission s = submissionRepository.findById(submissionId).get();
		Version v = versionRepository.findOneBySubmissionIdAndVersionNumber(submissionId, s.getNumOfVersions()).get();
		s.setStatus(SubmissionStatus.CLOSED);
		v.setStatus(VersionStatus.OLD_VERSION);
		Long internshipId = submissionRepository.getInternshipId(submissionId);
		String fileName = internshipService.handleFinalizeInternship(internshipId, req);
		s.setFinalReportName(fileName);
        return submissionRepository.save(s);
    }

	public ResponseEntity<Resource> downloadFinalReport(Long submissionId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		Long internshipId = submissionRepository.getInternshipId(submissionId);
		Resource report = documentService.getDocumentByFolderNameAndRequestID("final_pdf", internshipId);
		return new ResponseEntity<Resource>(report, headers, HttpStatus.OK);
	}
}
