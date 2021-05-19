package io.gitlab.robbinespu.ess.business.rest;

import io.gitlab.robbinespu.ess.model.Student;
import io.gitlab.robbinespu.ess.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    StudentService studentservice;

    @Autowired
    public StudentRestController(StudentService studentservice) {
        super();
        this.studentservice = studentservice;
    }

    @GetMapping(value = "/students")
    public List<Student> getAllStudents() {
        return studentservice.getAllStudents();
    }

    @PostMapping(value = "/students")
    public Student addStudent(@Valid @RequestBody Student std) {
        return studentservice.save(std);
    }

    @DeleteMapping(value = "/students/{id}")
    public String deleteStudent(@PathVariable("id") @Min(1) int id) {
        Student std = studentservice.findById(id)
                .orElseThrow(() -> new StudentRestException("Student with " + id + " is Not Found!"));
        studentservice.deleteById(std.getId());
        return "Student with ID :" + id + " is deleted";
    }
}
