package com.sarayeva.cybercubebetask.repository;

import com.sarayeva.cybercubebetask.domain.Analysis;
import com.sarayeva.cybercubebetask.domain.Profile;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
  Optional<Analysis> findAnalysisByIdAndOwnerOrViewersContaining(Long analysisId, Profile owner,
      Profile viewer);

  List<Analysis> findAnalysesByOwnerOrViewersContainingOrderByUpdatedTimeDesc(Profile owner,
      Profile viewer, Pageable pageable);
}
