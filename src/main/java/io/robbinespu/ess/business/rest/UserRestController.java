package io.robbinespu.ess.business.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.robbinespu.ess.model.Users;
import io.robbinespu.ess.service.RoleService;
import io.robbinespu.ess.service.UserService;
import io.robbinespu.ess.util.ObjectToJsonObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    UserService userService;
    RoleService roleService;

    @Autowired
    public UserRestController(UserService userService, RoleService roleService) {
        super();
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/users")
    public List<Users> getAllUser() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/users")
    public ResponseEntity<Map> addUser(@Valid @RequestBody Users user) throws JsonProcessingException {
        ObjectToJsonObjectNode objectToJsonObjectNode = new ObjectToJsonObjectNode();
        HashMap<String, String> map = new HashMap<>();
        logger.debug("Parsed object: {}", user);
        if (user.getRoles() == null) {
            map.put("status", "FAILED");
            logger.error("ROB->> user.getRoles are NULL");
            return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
        }
        Users userDB = userService.save(user);
        String userJson = objectToJsonObjectNode.EntitiesToJsonParent(userDB);
        map = new ObjectMapper().readValue(userJson, HashMap.class);
        map.put("status", "OK");
        logger.info("ROB->> Registered {} and assigned role {}", userDB.getId(), userDB.getRoles().getId());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}")
    public String deleteUser(@PathVariable("id") String id) {
        Users std = userService.findById(id)
                .orElseThrow(() -> new UserRestException("Student with " + id + " is Not Found!"));
        userService.deleteById(std.getId());
        return "Student with ID :" + id + " is deleted";
    }

    @PutMapping(value = "/users/{id}")
    public Users updateUser(@PathVariable("id") String id, @Valid @RequestBody Users usr) {
        Users std = userService.findById(id)
                .orElseThrow(() -> new UserRestException("Student with " + id + " is Not Found!"));
        std.setName(usr.getName());
        std.setDepartment(usr.getDepartment());
        std.setEmail(usr.getEmail());
        return userService.save(std);
    }
}
