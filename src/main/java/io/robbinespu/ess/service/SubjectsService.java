/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.service.SubjectsService
 * Last modified:  5/27/21, 6:30 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.service;

import io.robbinespu.ess.model.Subjects;
import io.robbinespu.ess.repo.SubjetsRepo;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Exception.class)
public class SubjectsService implements ISubjectsService {
  public final SubjetsRepo subjectRepo;

  @Autowired
  public SubjectsService(SubjetsRepo subjectRepo) {
    this.subjectRepo = subjectRepo;
  }

  @Override
  public Subjects save(Subjects subjects) {
    return subjectRepo.save(subjects);
  }

  @Override
  public Optional<Subjects> findById(String id) {
    return subjectRepo.findById(id);
  }

  @Override
  public Optional<Subjects> findByFormAndName(Integer form, String subjectName) {
    return subjectRepo.findByFormAndName(form, subjectName);
  }
}
