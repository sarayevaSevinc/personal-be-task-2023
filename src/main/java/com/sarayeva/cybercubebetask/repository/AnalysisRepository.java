package com.sarayeva.cybercubebetask.repository;

import com.sarayeva.cybercubebetask.domain.Analysis;
import com.sarayeva.cybercubebetask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
    Analysis findAnalysisByIdAndOwner(Long id, User ownerId);
    List<Analysis> findAnalysesByOwner(User ownerId);
    List<Analysis> findAnalysesByViewersContaining(User viewer);
    Analysis findAnalysisByIdAndViewersIsContaining(Long id, User viewer);
}
