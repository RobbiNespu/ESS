/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.service.SlotService
 * Last modified:  5/29/21, 2:46 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.service;

import io.robbinespu.ess.model.ClassSubjectList;
import io.robbinespu.ess.model.Slots;
import io.robbinespu.ess.repo.SlotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SlotService implements ISlotsService {
  public final SlotRepo slotRepo;

  @Autowired
  public SlotService(SlotRepo slotRepo) {
    this.slotRepo = slotRepo;
  }

  @Override
  public Slots save(Slots slots) {
    return this.slotRepo.save(slots);
  }

  public List<Slots> findByClassSubjectList(Optional<ClassSubjectList> classSubjectList) {
    return this.slotRepo.ClassSubjectList(classSubjectList);
  }
}
