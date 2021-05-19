package io.gitlab.robbinespu.ess.service;

import io.gitlab.robbinespu.ess.model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<Student> getAllStudents();
    Optional<Student> findById(int id);
    Optional<Student> findByEmail(String email);
    Student save(Student std);
    void deleteById(int id);
}
