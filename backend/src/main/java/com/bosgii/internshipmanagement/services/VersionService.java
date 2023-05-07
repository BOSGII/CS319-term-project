package com.bosgii.internshipmanagement.services;
import java.util.Optional;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.entities.Version;
import com.bosgii.internshipmanagement.enums.VersionStatus;
import com.bosgii.internshipmanagement.entities.Submission;
import com.bosgii.internshipmanagement.repos.VersionRepository;
import com.bosgii.internshipmanagement.repos.SubmissionRepository;
import com.bosgii.internshipmanagement.requests.AddVersionRequest;
import com.bosgii.internshipmanagement.requests.ChangeVersionRequest;

@Service
public class VersionService {

	private VersionRepository versionRepository;

	SubmissionService submissionService;

	
	public VersionService(VersionRepository versionRepository, SubmissionService submissionService) {
		this.versionRepository = versionRepository;
		this.submissionService = submissionService;
	}
	
	public List<Version> getAllVersionsOfASubmission(Long submissionId) {
		Submission submission = submissionService.getOneSubmissionById(submissionId);

		if(submission != null){
			return versionRepository.getAllBySubmissionId(submissionId);	
		}

		return null;
	}

	public Version addVersionOnASubmission(Long submissionId, AddVersionRequest req) {
		Submission s = submissionService.getOneSubmissionById(submissionId);
		Version version = new Version();
		version.setStatus(VersionStatus.NOT_EVALUATED);
		version.setSubmission(s);
		return versionRepository.save(version);

	}

	public Version changeVersion(Long versionId, ChangeVersionRequest req) {
		Optional<Version> version = versionRepository.findById(versionId);

		if(version.isPresent()){
			Version foundVersion = version.get();
			foundVersion.setStatus(req.getVersionStatus());
			versionRepository.save(foundVersion);
			return foundVersion;
		}

		return null;
	}

	public Version deleteVersion(Long versionId) {
		Optional<Version> version = versionRepository.findById(versionId);
		versionRepository.deleteById(versionId);
		return version.get();
	}

    public Optional<Version> getVersionById(Long versionId) {
        return versionRepository.findById(versionId);
    }

}
