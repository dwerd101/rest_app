package com.example.repository;

import com.example.model.Source;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SourceRep extends JpaRepository<Source, Long> {
    Optional<Source> findById(Long id);
    Page<Source> findById(Long id, Pageable pageable);
}
