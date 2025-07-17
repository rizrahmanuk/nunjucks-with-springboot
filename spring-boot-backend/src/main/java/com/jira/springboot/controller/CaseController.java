package com.jira.springboot.controller;

import com.jira.springboot.entity.usercase.PoliceCase;
import com.jira.springboot.entity.usercase.PoliceOfficer;
import com.jira.springboot.service.CaseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(path = "/get-officers",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PoliceOfficer>> getCases() {
        List<PoliceOfficer> policeOfficers = caseService.findAllOfficers();
        return new ResponseEntity<>(policeOfficers, HttpStatus.OK);
    }

    @GetMapping(path = "/get-case/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<PoliceCase> getCase(@PathVariable("id") Long id, HttpServletRequest request) {
        return caseService.findCaseById(id);
    }

    @GetMapping(path = "/get-officer/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<PoliceOfficer> getOfficer(@PathVariable("id") Long id, HttpServletRequest request) {
        return caseService.findOfficerById(id);
    }

    @PostMapping(path = "/create-case",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PoliceCase createUserCase(@RequestBody PoliceCase policeCase) {

        try {
            final UUID uuid = UUID.randomUUID();
            policeCase.setCaseNumber(uuid.toString());
            PoliceOfficer persistedPoliceOfficer = caseService.createOfficer(policeCase.getPoliceOfficer());
            policeCase.setPoliceOfficer(persistedPoliceOfficer);
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

        return caseService.updateCase(userCase);
    }


    @DeleteMapping(path = "/delete-case/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteUserCase(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        
        caseService.deleteCase(id);
        return "{ 'response': 'successfully deleted usercase having id ="+id+" }";
    }
}
