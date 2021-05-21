package io.gitlab.robbinespu.ess.business.rest;

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
import java.util.List;

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
    public ResponseEntity<Users> addUser(@Valid @RequestBody Users user) {
        logger.debug("Parsed object: {}", user);
        Roles roles = new Roles();
        Users userDB = userService.save(user);
        String userId = userDB.getId();
        roles.setUserId(userDB.getId());
        roles.setType("Student");
        roles.setUserId(userDB.getId());
        roles = roleService.save(roles);
        ObjectToJsonObjectNode objectToJsonObjectNode = new ObjectToJsonObjectNode();
        String jsonString = objectToJsonObjectNode.EntitiesToJson(userDB);
        logger.info("ROB->> Registered {} and assigned role {} and {}", userId, roles.getId(), jsonString);
        //return userService.findById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
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
