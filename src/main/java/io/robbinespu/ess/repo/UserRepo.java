package io.robbinespu.ess.repo;

import io.robbinespu.ess.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, String> {
    // Query method
    Optional<Users> findByEmail(String email);

    void deleteById(String id);

    @Query(value = "select nextval(SEQ_user)", nativeQuery = true)
    public Long getNextValFromSeq();
}
