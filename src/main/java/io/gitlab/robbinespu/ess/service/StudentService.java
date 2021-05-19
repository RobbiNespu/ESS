package io.gitlab.robbinespu.ess.service;

import io.gitlab.robbinespu.ess.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    public StudentRepo studentRepo;
}
