/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.service.ISubjectsService
 * Last modified:  5/27/21, 6:30 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.service;

import io.robbinespu.ess.model.Subjects;

import java.util.Optional;

public interface ISubjectsService {
    Subjects save(Subjects subjects);
    Optional<Subjects> findById(String id);

    Optional<Subjects> findByFormAndName(Integer form, String subjectName);
}
