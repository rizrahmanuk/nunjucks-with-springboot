package com.jira.springboot.repository;

import com.jira.springboot.entity.UserCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Department entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository // Indicates that this interface is a Spring Data repository.
public interface CaseRepository extends JpaRepository<UserCase, Long> {

    Optional<UserCase> findById(Long id);

    @Override
    <S extends UserCase> S save(S entity);

    UserCase findByCaseNumber(String caseNumber);

}
