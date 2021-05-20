package io.gitlab.robbinespu.ess.business.rest;

import io.gitlab.robbinespu.ess.model.Roles;
import io.gitlab.robbinespu.ess.model.Users;
import io.gitlab.robbinespu.ess.service.RoleService;
import io.gitlab.robbinespu.ess.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserRestController {

    UserService userService;
    RoleService roleService;

    @Autowired
    public UserRestController(UserService userService,RoleService roleService ) {
        super();
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/users")
    public List<Users> getAllUser() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/users")
    public Optional<Users> addUser(@Valid @RequestBody Users user) {
        Roles roles = new Roles();
        Users userDB = userService.save(user);
        Long userId = userDB.getId();

        roles.setUserId(userDB.getId());
        roles.setType("Student");
        roles.setUserId(userDB.getId());

        roles = roleService.save(roles);

        System.out.println("ROB->> :" + userId +"/"+roles.getId());

        return userService.findById(userId);
    }

    @DeleteMapping(value = "/users/{id}")
    public String deleteUser(@PathVariable("id") @Min(1) Long id) {
        Users std = userService.findById(id)
                .orElseThrow(() -> new UserRestException("Student with " + id + " is Not Found!"));
        userService.deleteById(std.getId());
        return "Student with ID :" + id + " is deleted";
    }

    @PutMapping(value = "/users/{id}")
    public Users updateUser(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody Users usr) {
        Users std = userService.findById(id)
                .orElseThrow(() -> new UserRestException("Student with " + id + " is Not Found!"));
        std.setName(usr.getName());
        std.setDepartment(usr.getDepartment());
        std.setEmail(usr.getEmail());
        return userService.save(std);
    }
}
