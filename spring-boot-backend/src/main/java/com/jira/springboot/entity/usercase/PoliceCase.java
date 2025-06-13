package com.jira.springboot.entity.usercase;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

import static jakarta.persistence.TemporalType.TIMESTAMP;

/**
 * Represents a Case entity in the application.
 */
@Entity
@Data // Generates getters, setters, toString, equals, and hashCode methods.
@NoArgsConstructor // Generates a no-args constructor.
@AllArgsConstructor // Generates a constructor with all arguments.
@Builder // Generates a builder pattern for creating instances.
public class PoliceCase {


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
    @JoinColumn(name="officer_id", updatable = false, nullable=false)
//    @Getter
//    @Setter
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
    private String description;
    private String status;

}

