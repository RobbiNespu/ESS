/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.business.rest.SubjectRestController
 * Last modified:  5/26/21, 2:05 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.business.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.robbinespu.ess.model.ClassSubjectList;
import io.robbinespu.ess.model.Nodes;
import io.robbinespu.ess.model.Subjects;
import io.robbinespu.ess.service.ClassSubjectListService;
import io.robbinespu.ess.service.NodeService;
import io.robbinespu.ess.service.SubjectsService;
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


    @Autowired
    public SubjectRestController(
            SubjectsService subjectsService,
            ClassSubjectListService classSubjectListService,
            NodeService nodeService) {
        super();
        this.subjectsService = subjectsService;
        this.classSubjectListService = classSubjectListService;
        this.nodeService = nodeService;
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
        HashMap<String, String> map = new HashMap<>();
        logger.debug("Receiving payload {} from {}", payload, request.getLocalAddr()); // Just for fun, get sender IP
        String className = (String) payload.get("name");
        Integer form = (Integer) payload.get("form");
        //payload.forEach((k,v) -> logger.debug("key: " + k + ", value: " + v));
        String teacherId = (String) ((Map) payload.get("subject_class")).get("teacherId");
        String formId = (String) ((Map) payload.get("subject_class")).get("formId");

        Subjects subjects = new Subjects();
        ClassSubjectList classSubjectList = new ClassSubjectList();
        Nodes nodes = new Nodes();

        subjects.setName(className);
        subjects.setForm(form);
        subjects = subjectsService.save(subjects);

        classSubjectList.setSubjectId(subjects.getId());
        classSubjectList.setFormId(formId);
        classSubjectList.setTeacherRoleId(teacherId);
        classSubjectListService.save(classSubjectList);

        nodes.setParent(formId);
        nodes.setChild(subjects.getId());
        nodeService.save(nodes);

        String subjectsJson = ConvertToJsonString(subjects);
        map = new ObjectMapper().readValue(subjectsJson, HashMap.class);
        map.putAll(SendStatusSuccess("subject registered"));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
