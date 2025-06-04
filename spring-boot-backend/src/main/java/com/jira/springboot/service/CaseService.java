package com.jira.springboot.service;

import com.jira.springboot.entity.usercase.PoliceCase;
import com.jira.springboot.entity.usercase.PoliceOfficer;

import java.util.List;
import java.util.Optional;

public interface CaseService {
   List<PoliceCase> findAll();
   Optional<PoliceCase> findById(Long id);
   PoliceCase getByCaseNumber(String caseNumber);
   PoliceCase updateCase(PoliceCase userCase);
   PoliceCase createCase(PoliceCase userCase);
   PoliceOfficer createOfficer(PoliceOfficer policeOfficer);

   void deleteCase(Long id);
}
