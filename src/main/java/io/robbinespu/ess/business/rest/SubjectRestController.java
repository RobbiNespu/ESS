/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.business.rest.SubjectRestController
 * Last modified:  5/29/21, 12:33 AM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.business.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.robbinespu.ess.model.*;
import io.robbinespu.ess.service.*;
import io.robbinespu.ess.util.ObjectToJsonObjectNode;
import io.robbinespu.ess.util.RestControllerHelper;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SubjectRestController extends RestControllerHelper {
  // User-defined SerialVersionUID
  private static final long serialVersionUID = 42L;
  private static final Logger logger = LoggerFactory.getLogger(SubjectRestController.class);

  SubjectsService subjectsService;
  ClassSubjectListService classSubjectListService;
  NodeService nodeService;
  FormsService formsService;
  UserService userService;
  SlotService slotService;

  @Autowired
  public SubjectRestController(
      SubjectsService subjectsService,
      ClassSubjectListService classSubjectListService,
      NodeService nodeService,
      FormsService formsService,
      UserService userService,
      SlotService slotService) {
    super();
    this.subjectsService = subjectsService;
    this.classSubjectListService = classSubjectListService;
    this.nodeService = nodeService;
    this.formsService = formsService;
    this.userService = userService;
    this.slotService = slotService;
  }

  @PostMapping(value = "/subjects")
  public ResponseEntity<Map> addSubject(@Valid @RequestBody Subjects subjects)
      throws JsonProcessingException {
    ObjectToJsonObjectNode objectToJsonObjectNode = new ObjectToJsonObjectNode();
    HashMap map = new HashMap<>();
    logger.debug("Parsed object: {}", subjects);
    if (subjects.getName() == null) {
      SendStatusFailed("subjects.getName() null");
      return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
    }

    if (subjects.getForm() == null) {
      SendStatusFailed("Form are null");
      return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
    }

    Subjects subjectDB = subjectsService.save(subjects);
    String subjectJson = ConvertToJsonString(subjectDB);
    map = new ObjectMapper().readValue(subjectJson, HashMap.class);
    map.put("status", "OK");
    logger.info("ROB->> Subject registered");
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  @RequestMapping(value = "/subjects/{id}")
  public Optional<Subjects> getFormsbyId(@PathVariable String id) {
    return subjectsService.findById(id);
  }

  @RequestMapping(value = "/subjects/class", method = RequestMethod.POST)
  public ResponseEntity<Map> process(
      @RequestBody Map<String, Object> payload, HttpServletRequest request) throws Exception {
    logger.debug(
        "Receiving payload {} from {}",
        payload,
        request.getLocalAddr()); // Just for fun, get sender IP

    HashMap<String, String> map = new HashMap<>();
    Subjects subjects = new Subjects();
    Nodes nodeFormSubject = new Nodes();
    Nodes nodeSubjectGroup = new Nodes();
    ClassSubjectList classSubjectList = new ClassSubjectList();
    Optional<Forms> formsDb = Optional.of(new Forms());
    Optional<Users> usersDb = Optional.of(new Users());
    Optional<Subjects> subjectDb = Optional.of(new Subjects());

    String _subjectName = (String) ((Map) payload.get("course")).get("subject_name");
    int hour = (int) ((Map) payload.get("course")).get("hour");
    // payload.forEach((k,v) -> logger.debug("key: " + k + ", value: " + v));

    usersDb =
        Optional.ofNullable(
            userService
                .findById((String) ((Map) payload.get("course")).get("teacherId"))
                .orElseThrow(
                    () ->
                        new CustomRestException(
                            "teacherId "
                                + (String) ((Map) payload.get("course")).get("teacherId")
                                + " is not exist on system")));
    formsDb =
        Optional.ofNullable(
            formsService
                .findById((String) ((Map) payload.get("course")).get("formId"))
                .orElseThrow(
                    () ->
                        new CustomRestException(
                            "formId "
                                + (String) ((Map) payload.get("course")).get("formId")
                                + " is not exist on system")));

    subjectsService
        .findByFormAndName(formsDb.get().getFormYear(), _subjectName)
        .ifPresent(
            s -> {
              throw new CustomRestException("form and subject is already on system");
            });

    logger.error("Hour --> {}", hour);
    if (hour <= 0 || hour > 3) {
      throw new CustomRestException("Course hour not between 1-3 hour");
    }

    subjects.setName(_subjectName);
    subjects.setForm(formsDb.get().getFormYear());
    subjects = subjectsService.save(subjects);

    classSubjectList.setSubjectId(subjects.getId());
    classSubjectList.setFormYear(formsDb.get().getFormYear());
    classSubjectList.setTeacherRoleId(usersDb.get().getRoles().getId());
    classSubjectList.setGroupSlot(hour);
    classSubjectListService.save(classSubjectList);

    nodeFormSubject.setParent(formsDb.get().getName());
    nodeFormSubject.setChild(subjects.getId());
    nodeFormSubject.setLevel(3);
    nodeService.save(nodeFormSubject);

    nodeSubjectGroup.setParent(subjects.getId());
    nodeSubjectGroup.setChild("G" + String.valueOf(classSubjectList.getGroupSlot()));
    nodeSubjectGroup.setLevel(4);
    nodeService.save(nodeSubjectGroup);

    // START => UGLIEST code - It maybe blow up! - Slots node edges | handle it with care
    if (hour == 1) {
      Nodes slots7 = new Nodes(nodeSubjectGroup.getChild().toString(), "SLOT7", 5); // SL7
      Slots sl7 = new Slots();
      sl7.setActive(true);
      sl7.setClassSubjectList(classSubjectList);
      sl7.setName("SLOT7");
      slotService.save(sl7);
      nodeService.save(slots7);
    } else if (hour == 2) {
      Nodes slots1 = new Nodes(nodeSubjectGroup.getChild().toString(), "SLOT1", 5); // SL1
      Slots sl1 = new Slots();
      sl1.setActive(true);
      sl1.setClassSubjectList(classSubjectList);
      sl1.setName("SLOT1");
      slotService.save(sl1);
      nodeService.save(slots1);
      Nodes slots3 = new Nodes(nodeSubjectGroup.getChild().toString(), "SLOT3", 5); // SL3
      Slots sl3 = new Slots();
      sl3.setActive(true);
      sl3.setClassSubjectList(classSubjectList);
      sl3.setName("SLOT3");
      slotService.save(sl3);
      nodeService.save(slots3);
      Nodes slots6 = new Nodes(nodeSubjectGroup.getChild().toString(), "SLOT6", 5); // SL6
      Slots sl6 = new Slots();
      sl6.setActive(true);
      sl6.setClassSubjectList(classSubjectList);
      sl6.setName("SLOT6");
      slotService.save(sl6);
      nodeService.save(slots6);
    } else if (hour == 3) {
      Nodes slots2 = new Nodes(nodeSubjectGroup.getChild().toString(), "SLOT2", 5); // SL2
      Slots sl2 = new Slots();
      sl2.setActive(true);
      sl2.setClassSubjectList(classSubjectList);
      sl2.setName("SLOT2");
      slotService.save(sl2);
      nodeService.save(slots2);
      Nodes slots4 = new Nodes(nodeSubjectGroup.getChild().toString(), "SLOT4", 5); // SL4
      Slots sl4 = new Slots();
      sl4.setActive(true);
      sl4.setClassSubjectList(classSubjectList);
      sl4.setName("SLOT4");
      slotService.save(sl4);
      nodeService.save(slots4);
      Nodes slots5 = new Nodes(nodeSubjectGroup.getChild().toString(), "SLOT5", 5); // SL5
      Slots sl5 = new Slots();
      sl5.setActive(true);
      sl5.setClassSubjectList(classSubjectList);
      sl5.setName("SLOT5");
      slotService.save(sl5);
      nodeService.save(slots5);
    } else {
      // heck!
      throw new CustomRestException("What happen? Pls check");
    }
    // END => UGLIEST code - It maybe blow up! - Slots node edges | handle it with care

    String subjectsJson = ConvertToJsonString(subjects);
    map = new ObjectMapper().readValue(subjectsJson, HashMap.class);
    map.putAll(SendStatusSuccess("subject registered"));
    return new ResponseEntity<>(map, HttpStatus.OK);
  }
}
