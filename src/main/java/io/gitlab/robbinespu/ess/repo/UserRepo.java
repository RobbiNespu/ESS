package io.gitlab.robbinespu.ess.repo;

import io.gitlab.robbinespu.ess.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    // Query method
    Optional<Users> findByEmail(String email);

    void deleteById(Long id);
}
