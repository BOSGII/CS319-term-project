package com.bosgii.internshipmanagement.services;

import com.bosgii.internshipmanagement.entities.Secretary;

import com.bosgii.internshipmanagement.exceptions.InvalidMailAddressException;

import com.bosgii.internshipmanagement.requests.AddSecretaryRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private SecretaryService secretaryService;

    public AdminService(SecretaryService secretaryService){
        this.secretaryService = secretaryService;
    }

    public List<Secretary> getAllSecretaries(){
        return secretaryService.getAllSecretaries();
    }

    public Secretary createSecretary(AddSecretaryRequest req) throws InvalidMailAddressException{
        return secretaryService.createSecretary(req);
    }

    public void deleteSecretary(Long id) {
        secretaryService.deleteSecretary(id);
    }
}
