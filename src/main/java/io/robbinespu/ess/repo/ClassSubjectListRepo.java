/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.repo.ClassSubjectListRepo
 * Last modified:  5/29/21, 10:27 AM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.repo;

import io.robbinespu.ess.model.ClassSubjectList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassSubjectListRepo extends JpaRepository<ClassSubjectList, String> {
  Optional<ClassSubjectList> findBySubjectIdAndFormYear(String subjectId, int FormYear);
}
