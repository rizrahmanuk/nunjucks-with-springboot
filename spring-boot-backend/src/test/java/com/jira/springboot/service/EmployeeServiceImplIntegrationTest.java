package com.jira.springboot.service;

import com.jira.springboot.entity.Employee;
import com.jira.springboot.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplIntegrationTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public EmployeeService employeeService() {
            return new EmployeeServiceImpl();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() {
        Employee employee = new Employee();
        employee.setName("deepika");
        employee.setSex("female");
        employee.setAge(55);

        Mockito.when(employeeRepository.findByName(employee.getName()))
                .thenReturn(employee);
    }

    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        Employee employee = new Employee();
        employee.setName("deepika");
        Employee found = employeeService.getEmployeeByName(employee);

        Assert.assertEquals(found.getName(), employee.getName());
    }

    @Test
    public void getByAge_thenEmployeeShouldBeFound() {
        Employee employee = new Employee();
        employee.setName("rizwan");
        employee.setSex("male");
        employee.setAge(20);
        employee.setId(1L);
        Mockito.when(employeeRepository.findByAge(20)).thenReturn(employee);

        OngoingStubbing<Employee> employeeById = Mockito.when(employeeRepository.getReferenceById(1)).thenReturn(employee);
        Employee emplyeeStub = (Employee) employeeRepository.getReferenceById(1);
        Assert.assertEquals(employeeRepository.findByAge(20).getAge(), Integer.valueOf(20));
    }
}
