package com.jira.springboot.service;

import com.jira.springboot.entity.Employee;

public interface EmployeeService {

    public Employee getEmployeeByName(Employee employee);
    public Employee getEmployeeByAge(int age);
    public Employee postEmployee(Employee employee);
}
