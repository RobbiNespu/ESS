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
        return studentRepo.findAll();
    }

    @Override
    public Optional<Student> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        return studentRepo.findByEmail(email);
    }

    @Override
    public Student save(Student std) {
        return studentRepo.save(std);
    }

    @Override
    public void deleteById(int id) {
        studentRepo.deleteById(id);
    }
}
