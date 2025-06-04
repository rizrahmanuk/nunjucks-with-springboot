package com.jira.springboot.controller;

import com.jira.springboot.entity.usercase.PoliceCase;
import com.jira.springboot.entity.usercase.PoliceOfficer;
import com.jira.springboot.service.CaseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/case")
public class CaseController {

    private static final Logger log = LoggerFactory.getLogger(CaseController.class);

    @Autowired
    private CaseService caseService;

    @GetMapping(path = "/get-cases",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PoliceCase> getCases() {
        return caseService.findAll();
//        return """
//                {
//                "items":[
//                {"id": 121200, "caseNumber": "1", "title": "Update case details",
//                 "description": "user failed to attend interview",
//                 "status": "UNDER-REVIEW",
//                 "createdDate": "2025-06-10T13:45:46.245987"
//                 },
//                  {"id": 121201, "caseNumber": "2", "title": "case details",
//                 "description": "user applied to attend interview",
//                 "status": "INTERVIEW-SCHEDULED",
//                 "createdDate": "2025-06-10T14:46:46.243447"
//                 }
//                 ]
//                 }
//                """;
    }

    @GetMapping(path = "/get-case/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<PoliceCase> getCase(@PathVariable("id") Long id, HttpServletRequest request) {
//        return """
//                {"id": 1, "caseNumber": "1", "title": "Update case details",
//                 "description": "user failed to attend interview",
//                 "status": "UNDER-REVIEW",
//                 "createdDate": "2025-06-10T13:45:46.245987"
//                 }
//                """;
        return caseService.findById(id);
    }

    @PostMapping(path = "/create-case",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PoliceCase createUserCase(@RequestBody PoliceCase policeCase) {

        try {
//            PoliceOfficer policeOfficerCreated = caseService.createOfficer(policeOfficer);
            final UUID uuid = UUID.randomUUID();
//            policeCase.setUser(policeOfficerCreated);
            policeCase.setCaseNumber(uuid.toString());
            PoliceOfficer policeOfficerPersisted = caseService.createOfficer(policeCase.getUser());
            policeCase.setUser(policeOfficerPersisted);
            PoliceCase policeCase1 = caseService.createCase(policeCase);
            return policeCase1;
        } catch (Exception exception) {
            log.error("ERROR: " + exception.getMessage());
            return new PoliceCase();
        }

    }

    private PoliceOfficer createOfficerCase(PoliceOfficer policeOfficer) {

        try {
            final UUID uuid = UUID.randomUUID();
            return caseService.createOfficer(policeOfficer);
        } catch (Exception exception) {
            log.error("ERROR: " + exception.getMessage());
            return new PoliceOfficer();
        }

    }

    @PutMapping(path = "/update-case/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PoliceCase updateUserCase(@PathVariable("id") int id, @RequestBody PoliceCase userCase, HttpServletRequest request, HttpServletResponse response) {
//        return """
//                {"id": 121200, "caseNumber": "1", "title": "Update caaaaaase details",
//                 "description": "user failed to attend interview",
//                 "status": "UNDER-REVIEW",
//                 "createdDate": "2025-06-10T13:45:46.245987"
//                 }
//                """;
        return caseService.updateCase(userCase);
    }


    @DeleteMapping(path = "/delete-case/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteUserCase(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
//        return """
//                {"id": 121200, "caseNumber": "1", "title": "Update caaaaaase details",
//                 "description": "user failed to attend interview",
//                 "status": "UNDER-REVIEW",
//                 "createdDate": "2025-06-10T13:45:46.245987"
//                 }
//                """;
        caseService.deleteCase(id);
        return "{ 'response': 'successfully deleted usercase having id ="+id+" }";
    }
}
