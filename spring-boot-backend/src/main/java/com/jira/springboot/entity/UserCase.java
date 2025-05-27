package com.jira.springboot.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

import static jakarta.persistence.TemporalType.TIMESTAMP;

/**
 * Represents a Case entity in the application.
 * TODO: generate Getters and Setters using lombok annotations.
 */
@Entity
//@Data // Generates getters, setters, toString, equals, and hashCode methods.
//@NoArgsConstructor // Generates a no-args constructor.
//@AllArgsConstructor // Generates a constructor with all arguments.
//@Builder // Generates a builder pattern for creating instances.
public class UserCase {


    @Id
    @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "case_id_seq",
            sequenceName = "case_id_seq",
            allocationSize = 50

    )
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TIMESTAMP)
    public Date createdDate;

    @UpdateTimestamp
    @Temporal(TIMESTAMP)
    public Date modified;

    @Column(nullable = false, unique = true, updatable = false)
    private String caseNumber;

    private String title;
    private String description;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}