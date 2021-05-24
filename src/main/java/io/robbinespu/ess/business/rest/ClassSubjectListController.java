/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.business.rest.ClassSubjectListController
 * Last modified:  5/22/21, 7:25 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.business.rest;

import io.robbinespu.ess.model.ClassSubjectList;
import io.robbinespu.ess.model.Forms;
import io.robbinespu.ess.service.ClassSubjectListService;
import io.robbinespu.ess.service.FormsService;
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
    private static final Logger logger = LoggerFactory.getLogger(ClassSubjectListController.class);
    ClassSubjectListService classSubjectListService;
    FormsService formsService;

    @Autowired
    public ClassSubjectListController(ClassSubjectListService classSubjectListService, FormsService formsService) {
        super();
        this.classSubjectListService = classSubjectListService;
        this.formsService = formsService;
    }

    @RequestMapping(value = "/subjectclass", method = RequestMethod.POST)
    public ClassSubjectList addStudent(@RequestBody ClassSubjectList classSubjectList) {
        logger.debug(">>>>>>>>> {}", classSubjectList.getFormId());
        logger.debug(">>>>>>>>> {}", formsService.findById(classSubjectList.getFormId()).isPresent());
        Forms std = formsService.findById(classSubjectList.getFormId()).orElseThrow(() -> new CustomRestException("ERROR!"));
        return classSubjectListService.save(classSubjectList);
    }
}
