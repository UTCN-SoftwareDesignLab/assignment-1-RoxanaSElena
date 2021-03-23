package service.account;

import model.Account;
import model.validation.Notification;

import java.sql.Date;
import java.util.List;

public interface AccountService {

    List<Account> findAll();
    Account findByClientId(int clientId);
    boolean  removeById(int id);
    boolean create(Account account);
    Notification <Boolean> update(Account account);
    void removeAll();

}
