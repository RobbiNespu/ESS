/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.service.INodeServices
 * Last modified:  5/29/21, 4:35 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.service;

import io.robbinespu.ess.model.Nodes;

public interface INodeServices {
  Nodes save(Nodes nodes);

  int sizeParentSubject(String subjectId);
}
