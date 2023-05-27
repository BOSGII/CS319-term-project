package com.bosgii.internshipmanagement.services;

import com.bosgii.internshipmanagement.documents.Feedback;
import com.bosgii.internshipmanagement.entities.Version;
import com.bosgii.internshipmanagement.repos.FeedbackRepository;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FeedbackService {
    FeedbackRepository feedbackRepository;
    DocumentService documentService;

    VersionService versionService;

    public FeedbackService(FeedbackRepository feedbackRepository, DocumentService documentService, VersionService versionService){
        this.feedbackRepository = feedbackRepository;
        this.documentService = documentService;
        this.versionService = versionService;
    }

    public ResponseEntity<Resource> getFeedbackById(Long id){

        return documentService.getDocumentByFolderNameAndRequestID("feedbacks",id);
    }

    public ResponseEntity<Resource> getFeedbackByVersionId(Long id){
        Feedback f = feedbackRepository.findByVersionId(id);
        return documentService.getDocumentByFolderNameAndRequestID("feedbacks",f.getId());
    }

    public Feedback addFeedbackToVersion(MultipartFile file,Long versionId){
        Version v = versionService.getVersionEntityById(versionId);
        Feedback feedbackToSave = new Feedback();
        feedbackToSave.setVersion(v);
        Feedback savedFeedback = feedbackRepository.saveAndFlush(feedbackToSave);
        documentService.saveDocument(file,"feedbacks", savedFeedback.getId());
        return feedbackRepository.save(feedbackToSave);
    }
}
