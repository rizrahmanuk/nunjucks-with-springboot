package com.jira.springboot.service;

import com.jira.springboot.entity.UserCase;

import java.util.List;
import java.util.Optional;

public interface CaseService {
   List<UserCase> findAll();
   Optional<UserCase> findById(Long id);
   UserCase getByCaseNumber(String caseNumber);
   UserCase updateCase(UserCase userCase);
   UserCase createCase(UserCase userCase);

   void deleteCase(Long id);
}
