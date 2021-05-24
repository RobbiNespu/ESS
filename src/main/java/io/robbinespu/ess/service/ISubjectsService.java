package io.robbinespu.ess.service;

import io.robbinespu.ess.model.Subjects;

import java.util.Optional;

public interface ISubjectsService {
    Subjects save(Subjects subjects);

    Optional<Subjects> findById(String id);
}
