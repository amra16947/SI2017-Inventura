package com.timxyz.repositories;

import com.timxyz.models.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

    Category findFirstByName(String name);

    @Query("select c from Category c where c.name like %:name%")
    Collection<Category> filterByName(@Param("name") String name);
}