package io.robbinespu.ess.business.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.robbinespu.ess.model.Subjects;
import io.robbinespu.ess.service.SubjectsService;
import io.robbinespu.ess.util.ObjectToJsonObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SubjectRestController {
    private static final Logger logger = LoggerFactory.getLogger(SubjectRestController.class);

    SubjectsService subjectsService;


    @Autowired
    public SubjectRestController(SubjectsService subjectsService) {
        super();
        this.subjectsService = subjectsService;
    }

    @PostMapping(value = "/subjects")
    public ResponseEntity<Map> addSubject(@Valid @RequestBody Subjects subjects) throws JsonProcessingException {
        ObjectToJsonObjectNode objectToJsonObjectNode = new ObjectToJsonObjectNode();
        HashMap map = new HashMap<>();
        logger.debug("Parsed object: {}", subjects);
        if (subjects.getName() == null) {
            map.put("status", "FAILED");
            logger.error("ROB->> subjects.getName() are NULL");
            return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
        }
        Subjects subjectDB = subjectsService.save(subjects);
        String formJson = objectToJsonObjectNode.EntitiesToJsonParent(subjectDB);
        map = new ObjectMapper().readValue(formJson, HashMap.class);
        map.put("status", "OK");
        logger.info("ROB->> Registered {} and assigned role {}", subjectDB.getId(), formJson);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/subjects/{id}")
    public Optional<Subjects> getFormsbyId(@PathVariable String id) {
        return subjectsService.findById(id);
    }
}
