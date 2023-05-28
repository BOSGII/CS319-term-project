package com.bosgii.internshipmanagement.services;

import com.bosgii.internshipmanagement.documents.CompanyEvaluationForm;
import com.bosgii.internshipmanagement.entities.CompanyEvaluation;
import com.bosgii.internshipmanagement.repos.CompanyEvaluationFormRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class CompanyEvaluationFormService {
    private final CompanyEvaluationFormRepository companyEvaluationFormRepository;
    private final DocumentService documentService;

    public CompanyEvaluationFormService(CompanyEvaluationFormRepository companyEvaluationFormRepository, DocumentService documentService){
        this.companyEvaluationFormRepository = companyEvaluationFormRepository;
        this.documentService = documentService;
    }

    public Resource getCompanyEvaluationFormByCompanyEvaluationId(Long id){
        CompanyEvaluationForm cef = companyEvaluationFormRepository.findByCompanyEvaluationId(id);
        return documentService.getDocumentByFolderNameAndRequestID("company_evaluation_forms", cef.getId());
    }

    public CompanyEvaluationForm addCompanyEvaluationFormToCompanyEvaluation(MultipartFile file, CompanyEvaluation companyEvaluation){
        CompanyEvaluationForm cef = new CompanyEvaluationForm();
        cef.setCompanyEvaluation(companyEvaluation);
        CompanyEvaluationForm savedCompanyEvaluationForm = companyEvaluationFormRepository.saveAndFlush(cef);
        documentService.saveDocument(file, "company_evaluation_forms", savedCompanyEvaluationForm.getId());
        return savedCompanyEvaluationForm;
    }
}
