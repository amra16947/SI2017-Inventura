package com.timxyz.services;

import com.timxyz.models.AccessLog;
import com.timxyz.models.Account;
import com.timxyz.models.BaseModel;
import com.timxyz.repositories.AccountRepository;
import com.timxyz.services.TokenAuthenticationService;
import com.timxyz.repositories.AccessLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class LogHelperService {

    @Autowired
    private AccessLogRepository accessLogRepository;

    @Autowired
    private AccountRepository accountRepository;

    private final String descriptionTemplate = "Korisnik/ca %s je %s objekat sa ID-em %d u tabeli %s.";
    // Username, action, object id, table name

    private String getTableName(BaseModel model) {
        String className = model.getClass().toString();
        String entityName = className.substring(className.lastIndexOf('.') + 1);
        
        return entityName.isEmpty() ? className : entityName;
    }

    private void log(Account account, BaseModel model, String action) {
        String username = account.getUsername();
        Long objectId = model.getId();
        String tableName = getTableName(model);
        Date date = new Date();
        String description =
                String.format(descriptionTemplate, username, action, objectId, tableName);

        AccessLog accessLog = new AccessLog();
        accessLog.setAccount(account);
        accessLog.setDate(date);
        accessLog.setDescription(description);
        accessLog.setObjectId(objectId);
        accessLog.setType(action.toUpperCase());
        accessLog.setTableName(tableName);

        accessLogRepository.save(accessLog);
    }

    // svjesno narusavanje DRY principa zbog citljivosti

    public void logCreate(String accessToken, BaseModel model) {
        Account account = accountRepository.findByUsername(TokenAuthenticationService.parseJwt(accessToken));

        if (account != null && model != null) {
            log(account, model, "kreirao/la");
        }
    }

    public void logUpdate(String accessToken, BaseModel model) {
        Account account = accountRepository.findByUsername(TokenAuthenticationService.parseJwt(accessToken));

        if (account != null && model != null) {
            log(account, model, "promijenio/la");
        }
    }

    public void logDelete(String accessToken, BaseModel model) {
        Account account = accountRepository.findByUsername(TokenAuthenticationService.parseJwt(accessToken));

        if (account != null && model != null) {
            log(account, model, "obrisao/la");
        }
    }
}
