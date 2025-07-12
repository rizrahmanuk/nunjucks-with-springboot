package com.jira.springboot.entity.usercase;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Objects;

import static jakarta.persistence.TemporalType.TIMESTAMP;

/**
 * Represents a Case entity in the application.
 */
@Entity
//@Data // Generates getters, setters, toString, equals, and hashCode methods.
@NoArgsConstructor // Generates a no-args constructor.
@AllArgsConstructor // Generates a constructor with all arguments.
@Builder // Generates a builder pattern for creating instances.
public class PoliceCase {

    @Override
    public String toString() {
        return "PoliceCase{" +
                "id=" + id +
                ", policeOfficer=" + policeOfficer +
                ", createdDate=" + createdDate +
                ", modified=" + modified +
                ", caseNumber='" + caseNumber + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PoliceCase that)) return false;
        return id == that.id && Objects.equals(policeOfficer, that.policeOfficer) && Objects.equals(createdDate, that.createdDate) && Objects.equals(modified, that.modified) && Objects.equals(caseNumber, that.caseNumber) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(status, that.status);
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, policeOfficer, createdDate, modified, caseNumber, title, description, status);
    }

    @Id
    @Column(insertable = false, updatable = false)
    @GeneratedValue(generator = "case_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "case_id_seq",
            sequenceName = "case_id_seq",
            allocationSize = 20

    )
    private long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private PoliceOfficer policeOfficer;

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

    public PoliceOfficer getPoliceOfficer() {
        return policeOfficer;
    }

    public void setPoliceOfficer(PoliceOfficer policeOfficer) {
        this.policeOfficer = policeOfficer;
    }

    private String description;
    private String status;

}

