package com.bosgii.internshipmanagement.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.services.ImportService;

@RestController
@RequestMapping("/api")
public class ImportController {
    private final ImportService importService;

    public ImportController(ImportService importService) {
        this.importService = importService;
    }

    @PostMapping("/import")
    public List<Internship> importInternshipsFromExcelFile(@RequestBody MultipartFile file){
        return importService.importInternshipsFromExcelFile(file);
    }
}
