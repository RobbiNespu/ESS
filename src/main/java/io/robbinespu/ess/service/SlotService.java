/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.service.SlotService
 * Last modified:  5/28/21, 4:31 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.service;

import io.robbinespu.ess.model.Slots;
import io.robbinespu.ess.repo.SlotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SlotService implements ISlotsService {
  public final SlotRepo slotRepo;

  @Autowired
  public SlotService(SlotRepo slotRepo) {
    this.slotRepo = slotRepo;
  }


  @Override
  public Optional<Slots> findByClassSubjectListIds(String subjectId) {
    return Optional.empty();
  }
}
