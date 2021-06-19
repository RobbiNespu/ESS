/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.service.IUserService
 * Last modified:  5/22/21, 1:30 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.service;

import io.robbinespu.ess.model.Level;

import java.util.Optional;

public interface ILevelService {
    Optional<Level> findById(Long id);

    Optional<Level> findByPower(String power);

    Level save(Level std);

    void deleteById(Long id);
}
