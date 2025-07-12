package com.jira.springboot.entity.usercase;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Array;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.*;

import static jakarta.persistence.TemporalType.TIMESTAMP;

/**
 * Represents a Case entity in the application.
 */
@Entity
@NoArgsConstructor // Generates a no-args constructor.
@AllArgsConstructor // Generates a constructor with all arguments.
@Builder // Generates a builder pattern for creating instances.
public class PoliceOfficer {
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PoliceOfficer{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", modified=" + modified +
                ", policeCases=" + policeCases +
                ", title='" + title + '\'' +
                ", given_name='" + given_name + '\'' +
                ", surname='" + surname + '\'' +
                ", address_line1='" + address_line1 + '\'' +
                ", address_line2='" + address_line2 + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PoliceOfficer that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(createdDate, that.createdDate) && Objects.equals(modified, that.modified) && Objects.equals(policeCases, that.policeCases) && Objects.equals(title, that.title) && Objects.equals(given_name, that.given_name) && Objects.equals(surname, that.surname) && Objects.equals(address_line1, that.address_line1) && Objects.equals(address_line2, that.address_line2) && Objects.equals(postcode, that.postcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdDate, modified, policeCases, title, given_name, surname, address_line1, address_line2, postcode);
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @Column(insertable = false, updatable = false)
    @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "user_id_seq",
            sequenceName = "user_id_seq",
            allocationSize = 20
    )
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TIMESTAMP)
    public Date createdDate;

    @UpdateTimestamp
    @Temporal(TIMESTAMP)
    public Date modified;

    @OneToMany(mappedBy = "policeOfficer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PoliceCase> policeCases;

    public PoliceOfficer addPoliceCase(PoliceCase policeCase){
        policeCases.add(policeCase);
        policeCase.setPoliceOfficer(this);
        return this;
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

    public List<PoliceCase> getPoliceCases() {
        return policeCases;
    }

    public void setPoliceCases(List<PoliceCase> policeCases) {
        this.policeCases = policeCases;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress_line1() {
        return address_line1;
    }

    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    public String getAddress_line2() {
        return address_line2;
    }

    public void setAddress_line2(String address_line2) {
        this.address_line2 = address_line2;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    private String title;
    private String given_name;
    private String surname;
    private String address_line1;
    private String address_line2;
    private String postcode;

}