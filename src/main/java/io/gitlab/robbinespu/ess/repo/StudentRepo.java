package io.gitlab.robbinespu.ess.repo;

import io.gitlab.robbinespu.ess.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student, String> {
}
