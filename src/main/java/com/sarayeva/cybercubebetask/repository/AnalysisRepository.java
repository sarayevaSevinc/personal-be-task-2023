package com.sarayeva.cybercubebetask.repository;

import com.sarayeva.cybercubebetask.domain.Analysis;
import com.sarayeva.cybercubebetask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
    Analysis findAnalysisById(Long id);
}
