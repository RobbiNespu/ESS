package io.robbinespu.ess.repo;

import io.robbinespu.ess.model.Forms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepo extends JpaRepository<Forms, String> {
}
