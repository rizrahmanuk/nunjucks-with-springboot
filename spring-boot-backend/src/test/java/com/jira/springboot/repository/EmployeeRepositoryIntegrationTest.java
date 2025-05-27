package com.jira.springboot.repository;

import com.jira.springboot.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        String name = "Saifur Rahman";
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(29);
        entityManager.persist(employee);
        entityManager.flush();

        // when
        Employee found = employeeRepository.findByName(employee.getName());

        // then
        Assert.assertNotNull(found);
        Assert.assertEquals(found.getName(),name);

    }

}