package io.robbinespu.ess.repo;

import io.robbinespu.ess.model.ClassSubjectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassSubjectListRepo extends JpaRepository<ClassSubjectList, String> {
}
