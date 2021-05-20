package io.gitlab.robbinespu.ess.service;

import io.gitlab.robbinespu.ess.model.Roles;
import io.gitlab.robbinespu.ess.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRolesService {
    public final RoleRepo roleRepo;

    @Autowired
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Roles save(Roles roles) {
        return roleRepo.save(roles);
    }
}
