package com.jira.springboot.service;

import com.jira.springboot.entity.usercase.PoliceCase;
import com.jira.springboot.entity.usercase.PoliceOfficer;
import com.jira.springboot.repository.CaseOfficerRepository;
import com.jira.springboot.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    CaseOfficerRepository caseOfficerRepository;

    @Override
    public List<PoliceCase> findAllCases() {
        return caseRepository.findAll();
    }

    public List<PoliceOfficer> findAllOfficers() {
        try {
           return caseOfficerRepository.findAll();

        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Optional<PoliceCase> findCaseById(Long id) {
        return caseRepository.findById(id);
    }

    public PoliceCase getByCaseNumber(String caseNum){
        return caseRepository.findByCaseNumber(caseNum);
    }

    @Override
    public PoliceCase updateCase(PoliceCase userCase) {
        Optional<PoliceCase> userCaseDB = findCaseById(userCase.getId());
        // Updates fields if they are not null or empty.
        if(userCaseDB.isPresent()) {
            if (Objects.nonNull(userCase.getTitle()) && !"".equalsIgnoreCase(userCase.getTitle())) {
                userCaseDB.get().setTitle(userCase.getTitle());
            }
            if (Objects.nonNull(userCase.getDescription()) && !"".equalsIgnoreCase(userCase.getDescription())) {
                userCaseDB.get().setDescription(userCase.getDescription());
            }
            if (Objects.nonNull(userCase.getStatus()) && !"".equalsIgnoreCase(userCase.getStatus())) {
                userCaseDB.get().setStatus(userCase.getStatus());
            }

            // Saves and returns the updated department entity.
            return caseRepository.save(userCaseDB.get());
        }

        return userCase;

    }

    public PoliceCase createCase(PoliceCase userCase){
        return caseRepository.save(userCase);
    }

    @Override
    public PoliceOfficer createOfficer(PoliceOfficer policeOfficer) {
        return caseOfficerRepository.save(policeOfficer);
    }

    public PoliceOfficer updateOfficer(PoliceOfficer policeOfficer){
        return caseOfficerRepository.saveAndFlush(policeOfficer);
    }

    @Override
    public Optional<PoliceOfficer> findOfficerById(Long id) {
        return caseOfficerRepository.findById(id);
    }

    @Override
    public void deleteCase(Long id) {
        caseRepository.deleteById(id);
    }
}
