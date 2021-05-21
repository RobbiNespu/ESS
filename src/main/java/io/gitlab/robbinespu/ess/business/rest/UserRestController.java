package io.gitlab.robbinespu.ess.business.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gitlab.robbinespu.ess.model.Roles;
import io.gitlab.robbinespu.ess.model.Users;
import io.gitlab.robbinespu.ess.service.RoleService;
import io.gitlab.robbinespu.ess.service.UserService;
import io.gitlab.robbinespu.ess.util.ObjectToJsonObjectNode;
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
        logger.debug("Parsed object: {}", user);
        Roles roles = new Roles();
        Users userDB = userService.save(user);
        String userId = userDB.getId();
        roles.setUserId(userDB.getId());
        roles.setType("Student");
        roles.setUserId(userDB.getId());
        roles = roleService.save(roles);
        ObjectToJsonObjectNode objectToJsonObjectNode = new ObjectToJsonObjectNode();
        String userJson = objectToJsonObjectNode.EntitiesToJsonParent(userDB);
        String roleJson = objectToJsonObjectNode.EntitiesToJsonParent(roles);
        logger.info("ROB->> Registered {} and assigned role {}", userId, roles.getId());

        Map<String, Object> parent = new ObjectMapper().readValue(userJson, HashMap.class);
        Map<String, Object> child = new ObjectMapper().readValue(roleJson, HashMap.class);

        parent.put("role", child);
        String jsonString2 = objectToJsonObjectNode.EntitiesToJsonParent(parent);
        logger.info("ROB->> json {}", jsonString2);
        return new ResponseEntity<Map>(parent, HttpStatus.OK);
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
