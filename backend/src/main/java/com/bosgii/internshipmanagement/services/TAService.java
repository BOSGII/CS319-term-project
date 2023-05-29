package com.bosgii.internshipmanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.entities.TA;
import com.bosgii.internshipmanagement.exceptions.InvalidMailAddressException;
import com.bosgii.internshipmanagement.exceptions.UserIdExistsException;
import com.bosgii.internshipmanagement.repos.InternshipRepository;
import com.bosgii.internshipmanagement.repos.TARepository;
import com.bosgii.internshipmanagement.repos.UserRepository;
import com.bosgii.internshipmanagement.requests.AddTARequest;
import com.bosgii.internshipmanagement.requests.ChangeTARequest;

@Service
public class TAService {
    private final TARepository taRepository;
    private final InternshipService internshipService;
    private final InternshipRepository internshipRepository;
    private final UserRepository userRepository;

    public TAService(TARepository taRepository, InternshipService internshipService, InternshipRepository internshipRepository, UserRepository userRepository) {
        this.taRepository = taRepository;
        this.internshipService = internshipService;
        this.internshipRepository = internshipRepository;
        this.userRepository = userRepository;
    }

    public List<TA> getAllTAs() {
        return taRepository.findAll();
    }

    public TA createTA(AddTARequest req) throws UserIdExistsException, InvalidMailAddressException {
        TA newTA = new TA();
        if (userRepository.existsById(req.getId())) {
			throw new UserIdExistsException(req.getId());
		}
        newTA.setId(req.getId());
        newTA.setFullName(req.getFullName());
		newTA.setMail(req.getMail());
		newTA.setDepartment(req.getDepartment());

        newTA.setNumOfAssignedInternships(0);
        newTA.setCompleted(0);
        newTA.setRole("ta");

        return taRepository.save(newTA);
    }

    public TA changeTADetails(Long taId, ChangeTARequest req) throws InvalidMailAddressException {
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
        final List<Internship> internships = internshipService.getAllInternships(Optional.empty(), Optional.empty());
        List<TA> tas = taRepository.findAll();
        int taSize = tas.size();
        int i = 0;

        for (Internship internship: internships) {
            internship.settA(tas.get(i));
            tas.get(i).setNumOfAssignedInternships(tas.get(i).getNumOfAssignedInternships() + 1);
            i = (i + 1) % taSize;
        }
        internshipRepository.saveAll(internships);
        taRepository.saveAll(tas);
    }
    
}
