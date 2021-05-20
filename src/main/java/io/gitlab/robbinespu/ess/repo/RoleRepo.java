package io.gitlab.robbinespu.ess.repo;

import io.gitlab.robbinespu.ess.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Roles, Long> {

    // Query method
    Optional<Roles> findByType(String type);

}
