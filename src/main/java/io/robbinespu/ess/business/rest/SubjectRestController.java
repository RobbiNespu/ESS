/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.business.rest.SubjectRestController
 * Last modified:  5/26/21, 10:57 PM
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SubjectRestController extends RestControllerHelper {
    private static final Logger logger = LoggerFactory.getLogger(SubjectRestController.class);

    SubjectsService subjectsService;
    ClassSubjectListService classSubjectListService;
    NodeService nodeService;
    FormsService formsService;
    UserService userService;


    @Autowired
    public SubjectRestController(
            SubjectsService subjectsService,
            ClassSubjectListService classSubjectListService,
            NodeService nodeService,
            FormsService formsService,
            UserService userService) {
        super();
        this.subjectsService = subjectsService;
        this.classSubjectListService = classSubjectListService;
        this.nodeService = nodeService;
        this.formsService = formsService;
        this.userService = userService;
    }

    @PostMapping(value = "/subjects")
    public ResponseEntity<Map> addSubject(@Valid @RequestBody Subjects subjects) throws JsonProcessingException {
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
    public ResponseEntity<Map> process(@RequestBody Map<String, Object> payload, HttpServletRequest request) throws Exception {
        logger.debug("Receiving payload {} from {}", payload, request.getLocalAddr()); // Just for fun, get sender IP

        HashMap<String, String> map = new HashMap<>();
        Subjects subjects = new Subjects();
        Nodes nodes = new Nodes();
        ClassSubjectList classSubjectList = new ClassSubjectList();
        Optional<Forms> formsDb = Optional.of(new Forms());
        Optional<Users> usersDb = Optional.of(new Users());

        String _subjectName = (String) payload.get("name");
        Integer _form = (Integer) payload.get("form");
        //payload.forEach((k,v) -> logger.debug("key: " + k + ", value: " + v));

        usersDb = Optional.ofNullable(userService.findById((String) ((Map) payload.get("subject_class")).get("teacherId"))
                .orElseThrow(() -> new CustomRestException("teacherId " + (String) ((Map) payload.get("subject_class")).get("teacherId") + " is not exist on system")));
        formsDb = Optional.ofNullable(formsService.findById((String) ((Map) payload.get("subject_class")).get("formId"))
                .orElseThrow(() -> new CustomRestException("formId " + (String) ((Map) payload.get("subject_class")).get("formId") + " is not exist on system")));

        subjects.setName(_subjectName);
        subjects.setForm(_form);
        subjects = subjectsService.save(subjects);

        classSubjectList.setSubjectId(subjects.getId());
        classSubjectList.setFormId(formsDb.get().getId());
        classSubjectList.setTeacherRoleId(usersDb.get().getRoles().getId());
        classSubjectListService.save(classSubjectList);

        nodes.setParent(formsDb.get().getId());
        nodes.setChild(subjects.getId());
        nodeService.save(nodes);

        String subjectsJson = ConvertToJsonString(subjects);
        map = new ObjectMapper().readValue(subjectsJson, HashMap.class);
        map.putAll(SendStatusSuccess("subject registered"));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
