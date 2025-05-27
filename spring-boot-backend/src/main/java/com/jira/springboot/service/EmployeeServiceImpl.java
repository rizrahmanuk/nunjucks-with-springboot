package com.jira.springboot.service;

import com.jira.springboot.entity.Employee;
import com.jira.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeByName(Employee employee) {
        Employee employee1 =  employeeRepository.findByName(employee.getName());

        return employee1;
    }

    @Override
    public Employee getEmployeeByAge(int age) {
        Employee employee = employeeRepository.getReferenceById(1);
        Employee employeeByAge = employeeRepository.findByAge(age);
        if(employee.getAge().shortValue() == employeeByAge.getAge().shortValue())
            return employeeRepository.findByAge(age);
        else
            return employee;
    }

    @Override
    public Employee postEmployee(Employee employee) {
        Employee persistedEmployee = employeeRepository.saveAndFlush(employee);
        return persistedEmployee;
    }


}
