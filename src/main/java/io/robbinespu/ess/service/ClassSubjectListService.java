/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.service.ClassSubjectListService
 * Last modified:  5/29/21, 10:41 AM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.service;

import io.robbinespu.ess.model.ClassSubjectList;
import io.robbinespu.ess.repo.ClassSubjectListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassSubjectListService implements IClassSubjectListService {
    public final ClassSubjectListRepo classSubjectListRepo;

    @Autowired
    public ClassSubjectListService(ClassSubjectListRepo classSubjectListRepo) {
        this.classSubjectListRepo = classSubjectListRepo;
    }

    @Override
    public ClassSubjectList save(ClassSubjectList classSubjectList) {
        return classSubjectListRepo.save(classSubjectList);
    }

    @Override
    public Optional<ClassSubjectList> findBySubjectId(String subjectId, int formYear) {
        return this.classSubjectListRepo.findBySubjectIdAndFormYear(subjectId, formYear);
    }


}
