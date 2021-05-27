/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.service.RoleService
 * Last modified:  5/22/21, 1:30 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.service;

import io.robbinespu.ess.model.Roles;
import io.robbinespu.ess.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRolesService {
  public final RoleRepo roleRepo;

  @Autowired
  public RoleService(RoleRepo roleRepo) {
    this.roleRepo = roleRepo;
  }

  @Override
  public Roles save(Roles roles) {
    return roleRepo.save(roles);
  }
}
