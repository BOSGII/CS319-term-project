package com.bosgii.internshipmanagement.services;

import com.bosgii.internshipmanagement.documents.Feedback;
import com.bosgii.internshipmanagement.entities.Version;
import com.bosgii.internshipmanagement.repos.FeedbackRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FeedbackService {
    FeedbackRepository feedbackRepository;
    DocumentService documentService;

    public FeedbackService(FeedbackRepository feedbackRepository, DocumentService documentService){
        this.feedbackRepository = feedbackRepository;
        this.documentService = documentService;
    }

    public Resource getFeedbackByVersionId(Long id){
        Feedback f = feedbackRepository.findByVersionId(id);
        return documentService.getDocumentByFolderNameAndRequestID("feedbacks",f.getId());
    }

    public Feedback addFeedbackToVersion(MultipartFile file, Version version){
        Feedback feedback = new Feedback();
        feedback.setVersion(version);
        Feedback savedFeedback = feedbackRepository.saveAndFlush(feedback);
        documentService.saveDocument(file,"feedbacks", savedFeedback.getId());
        return savedFeedback;
    }
}
