package com.bosgii.internshipmanagement.services;
import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.bosgii.internshipmanagement.entities.Version;
import com.bosgii.internshipmanagement.enums.VersionStatus;
import com.bosgii.internshipmanagement.documents.Report;
import com.bosgii.internshipmanagement.entities.Comment;
import com.bosgii.internshipmanagement.entities.Submission;
import com.bosgii.internshipmanagement.repos.CommentRepository;
import com.bosgii.internshipmanagement.repos.VersionRepository;
import com.bosgii.internshipmanagement.requests.AddVersionRequest;
import com.bosgii.internshipmanagement.requests.AskForRevisionRequest;

@Service
public class VersionService {

	private final VersionRepository versionRepository;
	private final SubmissionService submissionService;
	private final CommentRepository commentRepository;
	private final FeedbackService feedbackService;
	private final ReportService reportService;
	
	public VersionService(VersionRepository versionRepository, SubmissionService submissionService, CommentRepository commentRepository, FeedbackService feedbackService, ReportService reportService) {
		this.versionRepository = versionRepository;
		this.submissionService = submissionService;
		this.commentRepository = commentRepository;
		this.feedbackService = feedbackService;
		this.reportService = reportService;
	}

	public Optional<Version> getOneVersion(Optional<Long> submissionId, Optional<Long> internshipId, int versionNumber) {
		Optional<Version> version;
		if(submissionId.isPresent()){
			version = versionRepository.findOneBySubmissionIdAndVersionNumber(submissionId.get(), versionNumber);
		} else if(internshipId.isPresent()){
			Submission s = submissionService.findSubmissionOfAnInternship(internshipId.get()).get();
			version = versionRepository.findOneBySubmissionIdAndVersionNumber(s.getId(), versionNumber);
		} else {
			version = Optional.empty();
		}

		return version;
	}

	public ResponseEntity<Resource> downloadReportOfVersion(Long versionId){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF); // Content type for the file

		Resource report = reportService.getReportByVersionId(versionId);

		return new ResponseEntity<Resource>(report, headers, HttpStatus.OK);
	}

	public ResponseEntity<Resource> downloadFeedbackOfVersion(Long versionId){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF); // Content type for the file

		Resource feedback = feedbackService.getFeedbackByVersionId(versionId);

		return new ResponseEntity<Resource>(feedback, headers, HttpStatus.OK);
	}

	public Version addVersionOnASubmission(Long internshipId, AddVersionRequest req) {
		// check if submission exists
		Optional<Submission> s = submissionService.findSubmission(Optional.of(internshipId), Optional.empty(), Optional.empty());
		Submission newSubmission;

		if(s.isEmpty()){
			// create a submission
			newSubmission = submissionService.addSubmissionOnAnInternship(internshipId);
		} else {
			newSubmission = s.get();
		}
		// there will be definitely a file in the request, but replies are not present in the inital submission
		if(req.getReplies() != null && !req.getReplies().isEmpty()){
			// get the last version
			Version lastVersion = this.getOneVersion(Optional.empty(), Optional.of(internshipId), newSubmission.getNumOfVersions()).get();
			List<Comment> comments = commentRepository.getAllByVersionId(lastVersion.getId());
			for(int i = 0; i < req.getReplies().size(); i++){
				Comment c = comments.get(i);
				c.setReply(req.getReplies().get(i));
			}
			lastVersion.setStatus(VersionStatus.OLD_VERSION);
			commentRepository.saveAllAndFlush(comments);
		}

		submissionService.handleNewVersion(newSubmission.getId());

		// add version to submission
		Version version = new Version();
		// save version to get auto generated id
		version = versionRepository.saveAndFlush(version);

		version.setVersionNumber(newSubmission.getNumOfVersions());
		version.setStatus(VersionStatus.NOT_EVALUATED);
		version.setSubmission(newSubmission);

		// handle uploaded report
		reportService.addReportToVersion(req.getReport(), version);
		version.setReportFileName(req.getReport().getOriginalFilename());

		return versionRepository.save(version);
	}

	public Version deleteVersion(Long versionId) {
		Optional<Version> version = versionRepository.findById(versionId);
		versionRepository.deleteById(versionId);
		return version.get();
	}

    public Optional<Version> getVersionById(Long versionId) {
        return versionRepository.findById(versionId);
    }

    public Version requestRevisionForVersion(Long versionId, AskForRevisionRequest req) {
		Version version = versionRepository.findById(versionId).get();
		version.setStatus(VersionStatus.REVISION_REQUIRED);

		if(!req.getComments().isEmpty()){
			version.setAreCommentsProvided(true);

			for(String message : req.getComments()) {
				Comment comment = new Comment();
				comment.setMessage(message);
				comment.setVersion(version);
				commentRepository.saveAndFlush(comment);
			}
		} else {
			version.setAreCommentsProvided(false);
		}

		if(req.getFeedback() != null){
			version.setIsFeedbackFileProvided(true);
			feedbackService.addFeedbackToVersion(req.getFeedback(), version);
			version.setFeedbackFileName(req.getFeedback().getOriginalFilename());
		} else {
			version.setIsFeedbackFileProvided(false);
		}
		
        return versionRepository.save(version);
    }

}
