/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.service.IClassSubjectListService
 * Last modified:  5/22/21, 5:04 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.service;

import io.robbinespu.ess.model.ClassSubjectList;

public interface IClassSubjectListService {
    ClassSubjectList save(ClassSubjectList classSubjectList);
}
