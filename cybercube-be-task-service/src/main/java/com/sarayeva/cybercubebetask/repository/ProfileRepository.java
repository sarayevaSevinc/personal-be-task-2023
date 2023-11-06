package com.sarayeva.cybercubebetask.repository;

import com.sarayeva.cybercubebetask.domain.Profile;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

  Optional<Profile> findProfileById(Long id);

  @Query("select p from profiles p where p.id in :ids")
  List<Profile> findAllProfileByIds(@Param("ids") List<Long> ids);
}
