package com.jira.springboot.controller;

import com.jira.springboot.entity.Employee;
import com.jira.springboot.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

//import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public Employee getAllEmployees(@RequestBody Employee employee, HttpServletRequest request) {
        Employee employeereturned = employeeService.getEmployeeByName(employee);
        return employeereturned;
    }

    @PostMapping(path = "/employee/create",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee postEmployee(@RequestBody Employee employee, HttpServletRequest request) {
        return employeeService.postEmployee(employee);
    }
}
