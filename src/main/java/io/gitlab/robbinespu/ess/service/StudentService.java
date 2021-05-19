package io.gitlab.robbinespu.ess.service;

import io.gitlab.robbinespu.ess.model.Student;
import io.gitlab.robbinespu.ess.repo.StudentRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService{
    public final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public List<Student> getAllStudents() {
        return null;
    }

    @Override
    public Optional<Student> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Student save(Student std) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
