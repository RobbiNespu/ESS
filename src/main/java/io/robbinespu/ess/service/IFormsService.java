/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.service.IFormsService
 * Last modified:  5/22/21, 6:20 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.service;

import io.robbinespu.ess.model.Forms;
import java.util.Optional;

public interface IFormsService {
  Forms save(Forms forms);

  Optional<Forms> findById(String id);
}
