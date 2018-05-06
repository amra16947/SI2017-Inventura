package com.timxyz.repositories;

import com.timxyz.models.Location;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by ramic on 04.05.2017..
 */
public interface LocationRepository extends PagingAndSortingRepository <Location, Long> {
    
    Location findFirstByName(String name);

    @Query("select l from Location l where l.name like %:name%")
    public Collection<Location> filterByName(@Param("name") String name);

}
