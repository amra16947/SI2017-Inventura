package com.timxyz.repositories;

import com.timxyz.models.LocationType;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface LocationTypeRepository extends PagingAndSortingRepository<LocationType, Long> {
    LocationType findFirstByName(String name);
    LocationType findFirstByDescription(String description);
    
    @Query("select lt from LocationType lt where lt.name like %:name%")
	public Collection<LocationType> filterByName(@Param("name") String name);
}
