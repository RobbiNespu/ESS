/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.service.UserService
 * Last modified:  5/22/21, 1:30 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.service;

import io.robbinespu.ess.model.Level;
import io.robbinespu.ess.repo.LevelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class LevelService implements ILevelService {
    public final LevelRepo levelRepo;

    @Autowired
    public LevelService(LevelRepo levelRepo) {
        this.levelRepo = levelRepo;
    }

    @Override
    public Optional<Level> findById(Long id) {
        return levelRepo.findById(id);
    }

    @Override
    public Optional<Level> findByPower(String power) {
        return levelRepo.findByPower(power);
    }

    @Override
    public Level save(Level lvl) {
        return levelRepo.save(lvl);
    }

    @Override
    public void deleteById(Long id) {
        levelRepo.deleteById(id);
    }
}
