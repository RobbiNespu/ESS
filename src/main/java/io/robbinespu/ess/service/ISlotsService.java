/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.service.ISlotsService
 * Last modified:  5/29/21, 2:46 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.service;

import io.robbinespu.ess.model.Slots;

public interface ISlotsService {
  Slots save(Slots slots);

  // List<Slots> findByClassSubjectList(String class_subject_list_id); BUGGY
}
