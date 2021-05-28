/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.business.rest.SlotRestController
 * Last modified:  5/28/21, 4:48 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.business.rest;

import io.robbinespu.ess.service.SlotService;
import io.robbinespu.ess.util.RestControllerHelper;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SlotRestController extends RestControllerHelper {
  // User-defined SerialVersionUID
  private static final long serialVersionUID = 42L;
  private static final Logger logger = LoggerFactory.getLogger(SlotRestController.class);

  SlotService slotService;

  @Autowired
  public SlotRestController(SlotService slotService) {
    super();
    this.slotService = slotService;
  }

  @GetMapping(value = "/slots/{formYear}/{subjectId}")
  public Map showSlotForFormYearSubjectId(
      @PathVariable("formYear") String id, @PathVariable("subjectId") String subjectId) {
    return SendStatusSuccess("OK IT WORKING");
  }
}
