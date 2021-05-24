package io.robbinespu.ess.repo;

import io.robbinespu.ess.model.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjetsRepo extends JpaRepository<Subjects, String> {
    @Query(value = "select nextval(SEQ_subject)", nativeQuery = true)
    public Long getNextValFromSeq();

    Optional<Subjects> findById(String id);
}
