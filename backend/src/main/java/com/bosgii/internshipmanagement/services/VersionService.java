package com.bosgii.internshipmanagement.services;
import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;


import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import com.bosgii.internshipmanagement.entities.Version;
import com.bosgii.internshipmanagement.enums.VersionStatus;
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
	private final DocumentService documentService;
	
	public VersionService(VersionRepository versionRepository, SubmissionService submissionService, CommentRepository commentRepository, DocumentService documentService) {
		this.versionRepository = versionRepository;
		this.submissionService = submissionService;
		this.commentRepository = commentRepository;
		this.documentService = documentService;
	}

	// TODO
	public ResponseEntity<Resource> getVersionByID(Long id){
		return documentService.getDocumentByFolderNameAndRequestID("versions",id);
	}

	public Version getVersionEntityById(Long id){
		return versionRepository.findById(id).get();
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

		version.setVersionNumber(newSubmission.getNumOfVersions());
		version.setStatus(VersionStatus.NOT_EVALUATED);
		version.setSubmission(newSubmission);

		// TODO: handle uploaded report
		System.out.println(req.getReport().getOriginalFilename());
		// documentService.saveDocument(file,"versions",savedVersion.getId());
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
		} else {
			version.setIsFeedbackFileProvided(false);
		}
		

        return null;
    }

}
