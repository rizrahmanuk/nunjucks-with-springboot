package com.jira.springboot.entity;

//import javax.persistence.*;
//import javax.validation.constraints.Size;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;


@Entity
@Table(name = "person")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @Size(min = 3, max = 20)
    @Size(min = 3, max = 20)
    private String name;

    @Size(min = 4, max = 6)
    private String sex;

    public @Size(min = 4, max = 6) String getSex() {
        return sex;
    }

    public void setSex(@Size(min = 4, max = 6) String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name= "age")
    @Range(min=16, max=90)
    private Integer age;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}