package io.gitlab.robbinespu.ess.repo;

import io.gitlab.robbinespu.ess.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, String> {
    // Query method
    Optional<Student> findByEmail(String email);

}
