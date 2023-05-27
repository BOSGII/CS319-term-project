package com.bosgii.internshipmanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.entities.TA;
import com.bosgii.internshipmanagement.repos.TARepository;
import com.bosgii.internshipmanagement.requests.AddTARequest;
import com.bosgii.internshipmanagement.requests.ChangeTARequest;

@Service
public class TAService {
    private final TARepository taRepository;
    private final InternshipService internshipService;

    public TAService(TARepository taRepository, InternshipService internshipService) {
        this.taRepository = taRepository;
        this.internshipService = internshipService;
    }

    public List<TA> getAllTAs() {
        return taRepository.findAll();
    }

    public TA createTA(AddTARequest req) {
        TA newTA = new TA();
        newTA.setId(req.getId());
        newTA.setFullName(req.getFullName());
		newTA.setMail(req.getMail());
		newTA.setDepartment(req.getDepartment());

        newTA.setNumOfAssignedInternships(0);
        newTA.setCompleted(0);
        newTA.setRole("ta");

        return taRepository.save(newTA);
    }

    public TA changeTADetails(Long taId, ChangeTARequest req) {
        TA toBeUpdated;
		Optional<TA> opt = taRepository.findById(taId);
		
		if (opt.isPresent()) {
			toBeUpdated = opt.get();
		} else {
			toBeUpdated = new TA();
			toBeUpdated.setCompleted(0);
			toBeUpdated.setRole("ta");
		}

		toBeUpdated.setFullName(req.getFullName());
		toBeUpdated.setMail(req.getMail());
		toBeUpdated.setDepartment(req.getDepartment());

		return taRepository.save(toBeUpdated);
    }

    public void deleteTA(Long taId) {
        taRepository.deleteById(taId);
    }

    public void matchTAs() {
        List<Internship> internships = internshipService.getAllInternships(null, null);
        List<TA> tas = taRepository.findAll();
        int taSize = tas.size();
        int i = 0;

        for (Internship internship: internships) {
            internship.settA(tas.get(i));
            i = (i + 1) % taSize;
        }
    }
    
}
