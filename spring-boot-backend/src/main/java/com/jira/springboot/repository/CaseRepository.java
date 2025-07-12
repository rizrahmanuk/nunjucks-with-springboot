package com.jira.springboot.repository;

import com.jira.springboot.entity.usercase.PoliceCase;
import com.jira.springboot.entity.usercase.PoliceOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Repository interface for Department entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository // Indicates that this interface is a Spring Data repository.
public interface CaseRepository extends JpaRepository<PoliceCase, Long> {

    @Override
    <S extends PoliceCase> S save(S entity);

    List<PoliceCase> findByPoliceOfficerId(Long id);

    PoliceCase findByCaseNumber(String caseNumber);

}
