package com.timxyz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import com.timxyz.repositories.AccountRepository;
import com.timxyz.models.Account;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    static Logger logger = Logger.getLogger(CustomUserDetailsService.class.getName());

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("CUSTOM USER DETAILS SERVICE");

        Account account = accountRepository.findByUsername(username);
        if(account == null) {
            throw new UsernameNotFoundException("Username " + username +" not found");
        }

        logger.info("LOAD USER BY USERNAME VIA CUSTOM USER DETAILS SERVICE\n" 
            + account.getUsername() + " " + account.getPassword() + " " +  getGrantedAuthorities(account)
            + "/" + account.getRole().getName());

        return new User(account.getUsername(), account.getPassword(), getGrantedAuthorities(account));
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(Account account) {

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if(account.getRole() != null) {
            grantedAuthorities.add(new SimpleGrantedAuthority(account.getRole().getName()));
        }
        return grantedAuthorities;
    }
}