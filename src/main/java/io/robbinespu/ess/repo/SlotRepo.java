/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.repo.SlotRepo
 * Last modified:  5/28/21, 4:48 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.repo;

import io.robbinespu.ess.model.Slots;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepo extends JpaRepository<Slots, String> {
  @Query(
      value =
          "SELECT * FROM `slots` WHERE `classSubjectList_Id` = :searchclassSubjectList_Id LIMIT 1",
      nativeQuery = true)
  Optional<Slots> findByClassSubjectListIds(@Param("searchclassSubjectList_Id") String subjectName);
}
