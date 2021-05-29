/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.business.rest.SlotRestController
 * Last modified:  5/29/21, 3:49 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.business.rest;

import io.robbinespu.ess.model.ClassSubjectList;
import io.robbinespu.ess.model.Slots;
import io.robbinespu.ess.service.ClassSubjectListService;
import io.robbinespu.ess.service.SlotService;
import io.robbinespu.ess.service.SubjectsService;
import io.robbinespu.ess.util.RestControllerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SlotRestController extends RestControllerHelper {
  // User-defined SerialVersionUID
  private static final long serialVersionUID = 42L;
  private static final Logger logger = LoggerFactory.getLogger(SlotRestController.class);

  SlotService slotService;
  SubjectsService subjectsService;
  ClassSubjectListService classSubjectListService;

  @Autowired
  public SlotRestController(
          SlotService slotService,
          SubjectsService subjectsService,
          ClassSubjectListService classSubjectListService) {
    super();
    this.slotService = slotService;
    this.subjectsService = subjectsService;
    this.classSubjectListService = classSubjectListService;
  }

  /*
  View all status of SLOTS
   */
  @GetMapping(value = "/slots/{formYear}/{subjectId}")
  public ResponseEntity<Map> showSlotForFormYearSubjectId(
          @PathVariable("formYear") int formYear, @PathVariable("subjectId") String subjectId) {
    HashMap<String, Slots> map = new HashMap<>();
    logger.debug(
            "ROB ==> findByFormAndName = {}", subjectsService.findByFormAndName(formYear, subjectId));

    subjectsService
            .findByFormAndName(formYear, subjectId)
            .orElseThrow(() -> new CustomRestException("form and subject is not exist on system"));

    classSubjectListService
            .classSubjectListRepo
            .findBySubjectIdAndFormYear(subjectId, formYear)
            .orElseThrow(() -> new CustomRestException("classSubjectList is not exist on system"));

    Optional<ClassSubjectList> classSubjectListDb =
            classSubjectListService.classSubjectListRepo.findBySubjectIdAndFormYear(
                    subjectId, formYear);
    List<Slots> slotsDb = slotService.findByClassSubjectList(classSubjectListDb);
    for (Slots i : slotsDb) {
      map.put(
              i.getName(), i); // honestly, I dont understand my code here, but the JSON out as I wanted
    }
    map.putAll(SendStatusSuccess("Slot available, please check"));
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  /*
  View the best SLOTS to pick with DFS (if available) just 1 slot
   */
  @GetMapping(value = "/slots/{formYear}/{subjectId}/DFS")
  public ResponseEntity<Map> showSlotForFormYearSubjectIdViaDFS(
          @PathVariable("formYear") int formYear, @PathVariable("subjectId") String subjectId) {
    HashMap<String, String> map = new HashMap<>();
    map.putAll(SendStatusSuccess("Slot available (Picked by DFS), don't forget to book"));
    return new ResponseEntity<>(map, HttpStatus.OK);
  }
}
