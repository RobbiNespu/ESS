package io.gitlab.robbinespu.ess.business.rest;

import io.gitlab.robbinespu.ess.model.Users;
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

    @Autowired
    public UserRestController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public List<Users> getAllStudents() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/users")
    public Users addStudent(@Valid @RequestBody Users std) {
        return userService.save(std);
    }

    @DeleteMapping(value = "/users/{id}")
    public String deleteStudent(@PathVariable("id") @Min(1) int id) {
        Users std = userService.findById(id)
                .orElseThrow(() -> new UserRestException("Student with " + id + " is Not Found!"));
        userService.deleteById(std.getId());
        return "Student with ID :" + id + " is deleted";
    }

    @PutMapping(value = "/users/{id}")
    public Users updateStudent(@PathVariable("id") @Min(1) int id, @Valid @RequestBody Users newstd) {
        Users std = userService.findById(id)
                .orElseThrow(() -> new UserRestException("Student with " + id + " is Not Found!"));
        std.setName(newstd.getName());
        std.setDepartment(newstd.getDepartment());
        std.setEmail(newstd.getEmail());
        return userService.save(std);
    }
}
