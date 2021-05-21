package io.robbinespu.ess.service;

import io.robbinespu.ess.model.Users;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<Users> getAllUsers();

    Optional<Users> findById(String id);

    Optional<Users> findByEmail(String email);

    Users save(Users std);

    void deleteById(String id);

    long getNextValFromSeq();
}
