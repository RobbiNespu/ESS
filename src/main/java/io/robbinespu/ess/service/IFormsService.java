package io.robbinespu.ess.service;

import io.robbinespu.ess.model.Forms;

import java.util.Optional;

public interface IFormsService {
    Forms save(Forms forms);

    Optional<Forms> findById(String id);
}
