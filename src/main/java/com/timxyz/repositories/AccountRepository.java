package com.timxyz.repositories;

import com.timxyz.models.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
    Account findFirstByEmail(String email);
    Account findFirstByEmailOrUsername(String email, String username);
    Account findByUsername(String username);

    @Query("select a from Account a where a.email like %:email%")
    Collection<Account> filterByEmail(@Param("email") String email);
}