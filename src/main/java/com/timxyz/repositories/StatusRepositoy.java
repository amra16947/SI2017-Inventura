package com.timxyz.repositories;

import com.timxyz.models.Status;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Dario on 5/6/2017.
 */
@Repository
public interface StatusRepositoy extends PagingAndSortingRepository<Status, Long> {
    
    Status findFirstByName(String name);
    
    @Query("select s from Status s where s.name like %:name%")
    Collection<Status> filterByName(@Param("name") String name);
}

