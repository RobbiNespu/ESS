/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.service.IClassSubjectListService
 * Last modified:  5/29/21, 10:41 AM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.service;

import io.robbinespu.ess.model.ClassSubjectList;
import java.util.Optional;

public interface IClassSubjectListService {
  ClassSubjectList save(ClassSubjectList classSubjectList);

  Optional<ClassSubjectList> findBySubjectId(String subjectId, int formYear);
}
