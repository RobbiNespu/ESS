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

@RestController
@RequestMapping("/api")
public class UserRestController {

    UserService userService;
    RoleService roleService;

    @Autowired
    public UserRestController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public List<Users> getAllUser() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/users")
    public Users addUser(@Valid @RequestBody Users std) {
        if (std.getRolesID() == null) {
            Roles roles = new Roles();
            roles.setUsername(std.getUsername());
            std.setRolesID(roles.getId());
            roleService.save(roles);
            System.out.println("ROB->> :" + roles.getUsername());

        }
        return userService.save(std);
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
