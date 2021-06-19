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

import io.robbinespu.ess.model.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, String> {
  // Query method
  Optional<Users> findByEmail(String email);

  void deleteById(String id);

  @Query(value = "select nextval(SEQ_user)", nativeQuery = true)
  public Long getNextValFromSeq();

  @Query("SELECT u FROM Users u WHERE u.username = :username")
  public Users getUserByUsername(@Param("username") String username);
}
