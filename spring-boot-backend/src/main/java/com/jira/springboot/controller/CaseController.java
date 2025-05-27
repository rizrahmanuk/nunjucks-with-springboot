package com.jira.springboot.controller;

import com.jira.springboot.entity.UserCase;
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
    public List<UserCase> getCases() {
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
    public Optional<UserCase> getCase(@PathVariable("id") Long id, HttpServletRequest request) {
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
    public UserCase createUserCase(@RequestBody UserCase userCase) {

        try {
            final UUID uuid = UUID.randomUUID();
            userCase.setCaseNumber(uuid.toString());
            return caseService.createCase(userCase);
        } catch (Exception exception) {
            log.error("ERROR: " + exception.getMessage());
            return new UserCase();
        }

    }

    @PutMapping(path = "/update-case/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserCase updateUserCase(@PathVariable("id") int id, @RequestBody UserCase userCase, HttpServletRequest request, HttpServletResponse response) {
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
