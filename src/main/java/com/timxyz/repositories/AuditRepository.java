package com.timxyz.repositories;

import com.timxyz.models.Audit;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuditRepository extends PagingAndSortingRepository<Audit, Long> {

    Audit findFirstByName(String name);
}
