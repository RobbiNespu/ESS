/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.service.FormsService
 * Last modified:  5/22/21, 5:56 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.service;

import io.robbinespu.ess.model.Forms;
import io.robbinespu.ess.repo.FormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FormsService implements IFormsService {
    public final FormRepo formRepo;

    @Autowired
    public FormsService(FormRepo formRepo) {
        this.formRepo = formRepo;
    }

    @Override
    public Forms save(Forms forms) {
        return formRepo.save(forms);
    }

    @Override
    public Optional<Forms> findById(String id) {
        return formRepo.findById(id);
    }
}
