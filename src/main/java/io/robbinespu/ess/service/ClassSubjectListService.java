package io.robbinespu.ess.service;

import io.robbinespu.ess.model.ClassSubjectList;
import io.robbinespu.ess.repo.ClassSubjectListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassSubjectListService implements IClassSubjectListService {
    public final ClassSubjectListRepo classSubjectListRepo;

    @Autowired
    public ClassSubjectListService(ClassSubjectListRepo classSubjectListRepo) {
        this.classSubjectListRepo = classSubjectListRepo;
    }

    @Override
    public ClassSubjectList save(ClassSubjectList classSubjectList) {
        return classSubjectListRepo.save(classSubjectList);
    }
}
