package com.jira.springboot.service;

import com.jira.springboot.entity.usercase.PoliceCase;
import com.jira.springboot.entity.usercase.PoliceOfficer;
import com.jira.springboot.repository.CaseOfficerRepository;
import com.jira.springboot.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    CaseOfficerRepository caseOfficerRepository;

    @Override
    public List<PoliceCase> findAll() {
        return caseRepository.findAll();
    }

    @Override
    public Optional<PoliceCase> findById(Long id) {
        return caseRepository.findById(id);
    }

    public PoliceCase getByCaseNumber(String caseNum){
        return caseRepository.findByCaseNumber(caseNum);
    }

    @Override
    public PoliceCase updateCase(PoliceCase userCase) {
        Optional<PoliceCase> userCaseDB = findById(userCase.getId());
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

    @Override
    public void deleteCase(Long id) {
        caseRepository.deleteById(id);
    }
}
