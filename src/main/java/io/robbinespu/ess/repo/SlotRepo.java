/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.repo.SlotRepo
 * Last modified:  5/29/21, 1:35 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.repo;

import io.robbinespu.ess.model.ClassSubjectList;
import io.robbinespu.ess.model.Slots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepo extends JpaRepository<Slots, String> {
    List<Slots> ClassSubjectList(Optional<ClassSubjectList> classSubjectList);
    // List<Slots> findByClassSubjectList(String class_subject_list_id); - BUGGY!
}
