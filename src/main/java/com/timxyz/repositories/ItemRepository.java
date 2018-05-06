package com.timxyz.repositories;

import com.timxyz.models.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;


public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
    Item findFirstBySkuNumber(String skuNumber);

    @Query("Select i from Item i where i.category.name like  %:name%")
    List<Item> getAllByCategoryName(@Param("name") String name );

    @Query("select i from Item i where i.location.name like  %:name%")
    List<Item> getAllByLocationName(@Param("name") String name );

    @Query("select i from Item i where i.location.type.name like  %:name%")
    List<Item> getAllByLocationTypeName(@Param("name") String name );

    @Query("select i from Item i where i.name like %:name% or   i.category.name like  %:name% or i.location.name like  %:name% or i.location.type.name like  %:name% or i.purchasedBy like %:name%")
    List<Item> getAllByFilter( @Param("name") String name );
  
    @Query("select i from Item i where i.dateOfPurchase = :date")
    List<Item> getAllByDate(@Param("date") Date date );

    @Query("select i from Item i where i.dateOfPurchase between :d1 and :d2")
    List<Item> getAllBetweenDates( @Param ("d1") Date date1, @Param("d2") Date date2 );

    @Query("select i from Item i where i.name like %:name%")
    List<Item> getAllByItemName( @Param("name") String name );

    @Query("select c from Item c where c.name like %:name%")
    Collection<Item> filterByName(@Param("name") String name);

    @Query("select i from Item i where i.location.id = :id")
    Collection<Item> getByLocation(@Param("id") Long id);
}
