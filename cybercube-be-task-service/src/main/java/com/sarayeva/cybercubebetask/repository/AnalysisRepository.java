package com.sarayeva.cybercubebetask.repository;

import com.sarayeva.cybercubebetask.domain.Analysis;
import com.sarayeva.cybercubebetask.domain.Profile;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {

  @Query(value = """
      select a.*, v.viewers_id from analyses a left join analyses_viewers v on a.id = v.analyses_id
      where a.id = ?1 and (a.owner_id = ?2 or v.viewers_id = ?2)
      """, nativeQuery = true)
  Optional<Analysis> findAnalysisByIdAndProfile(Long analysisId,
      Long profileId);

  List<Analysis> findAnalysesByOwnerOrViewersContainingOrderByUpdatedTimeDesc(Profile owner,
      Profile viewer, Pageable pageable);
}
