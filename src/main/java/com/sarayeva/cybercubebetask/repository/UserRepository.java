package com.sarayeva.cybercubebetask.repository;

import com.sarayeva.cybercubebetask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
}
