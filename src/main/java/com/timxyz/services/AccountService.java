package com.timxyz.services;

import com.timxyz.models.Account;
import com.timxyz.repositories.AccountRepository;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AccountService extends BaseService<Account, AccountRepository> {

    @Autowired
    private RoleService roleService;

    public Account save(Account model) throws ServiceException {
        Account sameEmail = getByEmail(model.getEmail());
        Account sameUsername = getByUsername(model.getUsername());

        if (sameEmail != null && model.getId() != sameEmail.getId()) {
            throw new ServiceException("Korisnik s navedenom e-mail adresom već postoji.");
        } else if (sameUsername != null && model.getId() != sameUsername.getId()) {
            throw new ServiceException("Korisnik s navedenim korisničkim imenom već postoji.");
        }

        return super.save(model);
    }

    public Account getByEmail(String email) {
        return repository.findFirstByEmail(email);
    }

    public Collection<Account> filterByEmail(String email) {
        return repository.filterByEmail(email);
    }

    public Account getByUsername(String username) {
        return repository.findByUsername(username);
    }
}
