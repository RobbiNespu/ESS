package io.gitlab.robbinespu.ess.service;

import io.gitlab.robbinespu.ess.model.Users;
import io.gitlab.robbinespu.ess.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserService implements IUserService {
    public final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<Users> findById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public Users save(Users std) {
        return userRepo.save(std);
    }

    @Override
    public void deleteById(String id) {
        userRepo.deleteById(id);
    }

    @Override
    public long getNextValFromSeq() {
        return userRepo.getNextValFromSeq();
    }
}
