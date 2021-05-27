/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.business.rest.ClassSubjectListController
 * Last modified:  5/28/21, 3:17 AM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.business.rest;

import io.robbinespu.ess.model.ClassSubjectList;
import io.robbinespu.ess.model.Forms;
import io.robbinespu.ess.service.ClassSubjectListService;
import io.robbinespu.ess.service.FormsService;
import io.robbinespu.ess.service.RoleService;
import io.robbinespu.ess.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClassSubjectListController {
  // User-defined SerialVersionUID
  private static final long serialVersionUID = 42L;
  private static final Logger logger = LoggerFactory.getLogger(ClassSubjectListController.class);
  ClassSubjectListService classSubjectListService;
  FormsService formsService;
  UserService userService;
  RoleService roleService;

  @Autowired
  public ClassSubjectListController(
      ClassSubjectListService classSubjectListService,
      FormsService formsService,
      UserService userService,
      RoleService roleService) {
    super();
    this.classSubjectListService = classSubjectListService;
    this.formsService = formsService;
    this.userService = userService;
  }

  @RequestMapping(value = "/subjectclass", method = RequestMethod.POST)
  public ClassSubjectList addStudent(@RequestBody ClassSubjectList classSubjectList) {
    Forms std =
        formsService
            .findById(classSubjectList.getId()) // TODO: this is fake pls edit
            .orElseThrow(() -> new CustomRestException("ERROR!"));
    return classSubjectListService.save(classSubjectList);
  }
}
