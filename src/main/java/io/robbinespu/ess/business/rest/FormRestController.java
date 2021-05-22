package io.robbinespu.ess.business.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.robbinespu.ess.model.Forms;
import io.robbinespu.ess.service.FormsService;
import io.robbinespu.ess.util.ObjectToJsonObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FormRestController {
    private static final Logger logger = LoggerFactory.getLogger(FormRestController.class);
    FormsService formsService;

    @Autowired
    public FormRestController(FormsService formsService) {
        super();
        this.formsService = formsService;
    }

    @PostMapping(value = "/forms")
    public ResponseEntity<Map> addUser(@Valid @RequestBody Forms forms) throws JsonProcessingException {
        ObjectToJsonObjectNode objectToJsonObjectNode = new ObjectToJsonObjectNode();
        HashMap map = new HashMap<>();
        logger.debug("Parsed object: {}", forms);
        if (forms.getName() == null) {
            map.put("status", "FAILED");
            logger.error("ROB->> forms.getName() are NULL");
            return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
        }
        Forms formsDB = formsService.save(forms);
        String formJson = objectToJsonObjectNode.EntitiesToJsonParent(formsDB);
        map = new ObjectMapper().readValue(formJson, HashMap.class);
        map.put("status", "OK");
        logger.info("ROB->> Registered {} and assigned role {}", formsDB.getId(), formJson);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
