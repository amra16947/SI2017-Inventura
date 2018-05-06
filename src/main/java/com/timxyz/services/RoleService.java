package com.timxyz.services;

import com.timxyz.models.Role;
import com.timxyz.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends ReadOnlyService<Role, RoleRepository> {
}
