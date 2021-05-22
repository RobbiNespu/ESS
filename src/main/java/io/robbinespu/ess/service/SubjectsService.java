package io.robbinespu.ess.service;

import io.robbinespu.ess.model.Subjects;
import io.robbinespu.ess.repo.SubjetsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class SubjectsService implements ISubjectsService {
    public final SubjetsRepo subjectRepo;

    @Autowired
    public SubjectsService(SubjetsRepo subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

    @Override
    public Subjects save(Subjects subjects) {
        return subjectRepo.save(subjects);
    }
}
