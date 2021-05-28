/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.service.ISlotsService
 * Last modified:  5/28/21, 4:48 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.service;

import io.robbinespu.ess.model.Slots;
import java.util.Optional;

public interface ISlotsService {
  Optional<Slots> findByClassSubjectListIds(String subjectId);
}
