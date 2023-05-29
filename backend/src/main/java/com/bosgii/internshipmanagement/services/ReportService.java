package com.bosgii.internshipmanagement.services;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.bosgii.internshipmanagement.documents.Report;
import com.bosgii.internshipmanagement.entities.Version;
import com.bosgii.internshipmanagement.repos.ReportRepository;

@Service
public class ReportService {
    ReportRepository reportRepository;
    DocumentService documentService;

    public ReportService(ReportRepository reportRepository, DocumentService documentService){
        this.reportRepository = reportRepository;
        this.documentService = documentService;
    }

    public Resource getReportByVersionId(Long versionId){
        Report r = reportRepository.findByVersionId(versionId);
        return documentService.getDocumentByFolderNameAndRequestID("reports", r.getId());
    }

    public Report addReportToVersion(MultipartFile file, Version version){
        Report report = new Report();
        report.setVersion(version);
        Report savedReport = reportRepository.saveAndFlush(report);
        documentService.saveDocument(file, "reports", savedReport.getId());
        return savedReport;
    }

    public void changeReportOfVersion(MultipartFile file, Version version){
        System.out.println(file.getSize());
        Report report = reportRepository.findByVersionId(version.getId());
        documentService.saveDocument(file, "reports", report.getId());
    }
}