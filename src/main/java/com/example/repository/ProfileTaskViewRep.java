package com.example.repository;

import com.example.model.ProfileTaskView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileTaskViewRep extends JpaRepository<ProfileTaskView, Long> {
    Page<ProfileTaskView> findAllByProfileTaskId(Long id, Pageable page);
    Page<ProfileTaskView> findAllByProfileTaskIdAndDomain(Long id,String domain, Pageable page);
    Page<ProfileTaskView> findAllByProfileTaskIdAndTableName(Long id, String tableName, Pageable page);
    Page<ProfileTaskView> findAllByProfileTaskIdAndDomainAndTableName(Long id, String domain, String tableName, Pageable page);
}
