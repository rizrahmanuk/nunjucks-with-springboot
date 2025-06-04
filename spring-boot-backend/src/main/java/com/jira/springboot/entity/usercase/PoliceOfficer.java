package com.jira.springboot.entity.usercase;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.*;

import static jakarta.persistence.TemporalType.TIMESTAMP;

/**
 * Represents a Case entity in the application.
 */
@Entity
@Data // Generates getters, setters, toString, equals, and hashCode methods.
@NoArgsConstructor // Generates a no-args constructor.
@AllArgsConstructor // Generates a constructor with all arguments.
@Builder // Generates a builder pattern for creating instances.
public class PoliceOfficer {


    @Id
    @Column(insertable = false, updatable = false)
    @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "user_id_seq",
            sequenceName = "user_id_seq",
            allocationSize = 20
    )
    private long officer_id;

    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TIMESTAMP)
    public Date createdDate;

    @UpdateTimestamp
    @Temporal(TIMESTAMP)
    public Date modified;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private ArrayList<PoliceCase> cases;

    private String title;
    private String given_name;
    private String surname;
    private String address_line1;
    private String address_line2;
    private String postcode;

}