/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.repo.UserRepo
 * Last modified:  5/22/21, 3:14 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.repo;

import io.robbinespu.ess.model.Level;
import io.robbinespu.ess.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepo extends JpaRepository<Level, String> {
    // Query method
    Optional<Level> findById(Long id);

    Optional<Level> findByPower(String power);

    void deleteById(Long id);

    @Query("SELECT lvl FROM Level lvl WHERE lvl.power = :power")
    public Users getLevelByPower(@Param("power") String power);
}
