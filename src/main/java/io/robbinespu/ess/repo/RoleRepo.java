package io.robbinespu.ess.repo;

import io.robbinespu.ess.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Roles, String> {

    // Query method
    Optional<Roles> findByType(String type);

    @Query(value = "select nextval(SEQ_role)", nativeQuery = true)
    public Long getNextValFromSeq();

}
