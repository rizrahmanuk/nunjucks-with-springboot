package com.jira.springboot.repository;

import com.jira.springboot.entity.usercase.PoliceCase;
import com.jira.springboot.entity.usercase.PoliceOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Department entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository // Indicates that this interface is a Spring Data repository.
public interface CaseOfficerRepository extends JpaRepository<PoliceOfficer, Long> {

    Optional<PoliceOfficer> findById(Long id);

    @Override
    <S extends PoliceOfficer> S save(S entity);

}
