/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.business.rest.UserRestController
 * Last modified:  5/28/21, 2:38 AM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.business.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.robbinespu.ess.model.Nodes;
import io.robbinespu.ess.model.Users;
import io.robbinespu.ess.service.NodeService;
import io.robbinespu.ess.service.RoleService;
import io.robbinespu.ess.service.UserService;
import io.robbinespu.ess.util.RestControllerHelper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRestController extends RestControllerHelper {
  // User-defined SerialVersionUID
  private static final long SerialVersionUID = 1l;

  private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

  UserService userService;
  RoleService roleService;
  NodeService nodeService;

  @Autowired
  public UserRestController(
      UserService userService, RoleService roleService, NodeService nodeService) {
    super();
    this.userService = userService;
    this.roleService = roleService;
    this.nodeService = nodeService;
  }

  @GetMapping(value = "/users")
  public List<Users> getAllUser() {
    return userService.getAllUsers();
  }

  @PostMapping(value = "/users")
  public ResponseEntity<Map> addUser(@Valid @RequestBody Users user)
      throws JsonProcessingException {
    HashMap<String, String> map = new HashMap<>();
    logger.debug("Parsed object: {}", user);
    if (user.getRoles() == null) {
      map.put("status", "FAILED");
      logger.error("ROB->> user.getRoles are NULL");
      return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
    }
    // check student or teacher
    if (user.getRoles() == null) {
      map.put("status", "FAILED");
      map.put("reason", "No ROLES define");
      return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
    }

    if (user.getRoles().getType() == "student") {
      if (user.getRoles().getForms() == null) {
        map.put("status", "FAILED");
        map.put("reason", "ROLES define as STUDENT but no FORM details");
        return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
      }
    }

    if (user.getRoles().getType().length() <= 0) {
      logger.debug("Role: {} = {}", user.getRoles().getType(), user.getRoles().getType().length());
    } else {
      String[] roles = {"student", "teacher"};
      if (java.util.Arrays.binarySearch(roles, user.getRoles().getType()) < 0) {
        logger.error("Sorry that isn't a STUDENT or a TEACHER");
        SendStatusFailed(user.getRoles().getType() + " are unknown");
        return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
      }
    }

    Users userDB = userService.save(user);
    Nodes nodeDb = new Nodes();
    userDB.getRoles().setUserId(userDB.getId());
    String userJson = ConvertToJsonString(userDB);
    map = new ObjectMapper().readValue(userJson, HashMap.class);
    map.putAll(
        SendStatusSuccess(
            "Registered "
                + userDB.getId()
                + " and assigned role "
                + userDB.getRoles().getId()
                + ""));
    if (userDB.getRoles().getType().equalsIgnoreCase("student")) {
      nodeDb.setParent("T" + userDB.getRoles().getForms().getFormYear());
      nodeDb.setChild(userDB.getRoles().getForms().getName());
      nodeDb.setLevel(2);
      nodeService.save(nodeDb);
    }
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  @DeleteMapping(value = "/users/{id}")
  public String deleteUser(@PathVariable("id") String id) {
    Users std =
        userService
            .findById(id)
            .orElseThrow(() -> new CustomRestException("Student with " + id + " is Not Found!"));
    userService.deleteById(std.getId());
    return "Student with ID :" + id + " is deleted";
  }

  @PutMapping(value = "/users/{id}")
  public Users updateUser(@PathVariable("id") String id, @Valid @RequestBody Users usr) {
    Users std =
        userService
            .findById(id)
            .orElseThrow(() -> new CustomRestException("Student with " + id + " is Not Found!"));
    std.setName(usr.getName());
    std.setDepartment(usr.getDepartment());
    std.setEmail(usr.getEmail());
    return userService.save(std);
  }
}
