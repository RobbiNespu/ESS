package io.robbinespu.ess.repo;

import io.robbinespu.ess.model.Forms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepo extends JpaRepository<Forms, String> {
}
