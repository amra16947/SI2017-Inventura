package com.timxyz.services;

import com.timxyz.models.Audit;
import com.timxyz.repositories.AuditRepository;
import org.springframework.stereotype.Service;


@Service
public class AuditService extends BaseService<Audit, AuditRepository> {

        
    public Audit getByName(String name) {
        return repository.findFirstByName(name);
    }

    
}
