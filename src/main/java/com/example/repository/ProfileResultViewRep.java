package com.example.repository;

import com.example.model.ProfileResultView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileResultViewRep extends JpaRepository<ProfileResultView, Long> {

    List<ProfileResultView> findAllBySourceName(String sourceName);
    Page<ProfileResultView> findAllBySourceName(String sourceName, Pageable pageable);
    List<ProfileResultView> findAllByProfileResultId(Long id);
    Page<ProfileResultView> findAllByProfileResultId(Long id, Pageable page);
    Page<ProfileResultView> findAllByProfileTaskId(Long id, Pageable page);
    Page<ProfileResultView> findAllByProfileTaskIdAndNameDomain(Long id, String domain, Pageable page);
  //  Page<ProfileResultView> findAll
}
