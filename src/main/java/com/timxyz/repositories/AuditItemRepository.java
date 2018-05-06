package com.timxyz.repositories;

import com.timxyz.models.AuditItem;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by smusic on 5/27/17.
 */

@Repository
public interface AuditItemRepository extends PagingAndSortingRepository<AuditItem, Long> {
}
