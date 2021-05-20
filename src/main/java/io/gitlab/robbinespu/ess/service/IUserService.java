package io.gitlab.robbinespu.ess.service;

import io.gitlab.robbinespu.ess.model.Users;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<Users> getAllUsers();

    Optional<Users> findById(Long id);

    Optional<Users> findByEmail(String email);

    Users save(Users std);

    void deleteById(Long id);
}
