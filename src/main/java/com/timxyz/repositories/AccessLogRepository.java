package com.timxyz.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.timxyz.models.AccessLog;

public interface AccessLogRepository extends PagingAndSortingRepository<AccessLog, Long> {
    @Query("select a from AccessLog a where a.account.username like %:filter% or a.account.fullName like  %:filter% order by date desc")
    List<AccessLog> getAllByFilter( @Param("filter") String filter);
}
