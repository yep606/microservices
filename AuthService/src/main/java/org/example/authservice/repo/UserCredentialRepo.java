package org.example.authservice.repo;

import org.example.authservice.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepo extends JpaRepository<UserDetails, Long> {

    Optional<UserDetails> findByUsername(String username);
}
