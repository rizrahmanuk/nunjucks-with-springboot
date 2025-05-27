package com.jira.springboot.service;

import com.jira.springboot.entity.UserCase;
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

    @Override
    public List<UserCase> findAll() {
        return caseRepository.findAll();
    }

    @Override
    public Optional<UserCase> findById(Long id) {
        return caseRepository.findById(id);
    }

    public UserCase getByCaseNumber(String caseNum){
        return caseRepository.findByCaseNumber(caseNum);
    }

    @Override
    public UserCase updateCase(UserCase userCase) {
        Optional<UserCase> userCaseDB = findById(userCase.getId());
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

    public UserCase createCase(UserCase userCase){
        return caseRepository.saveAndFlush(userCase);
    }

    @Override
    public void deleteCase(Long id) {
        caseRepository.deleteById(id);
    }
}
