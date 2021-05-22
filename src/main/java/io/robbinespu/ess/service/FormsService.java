package io.robbinespu.ess.service;

import io.robbinespu.ess.model.Forms;
import io.robbinespu.ess.repo.FormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormsService implements IFormsService {
    public final FormRepo formRepo;

    @Autowired
    public FormsService(FormRepo formRepo) {
        this.formRepo = formRepo;
    }

    @Override
    public Forms save(Forms forms) {
        return formRepo.save(forms);
    }
}
