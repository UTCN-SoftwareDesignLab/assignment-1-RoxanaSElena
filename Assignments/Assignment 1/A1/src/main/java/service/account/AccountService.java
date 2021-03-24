package service.account;

import model.Account;
import model.DTO.TransferDTO;
import model.validation.Notification;

import java.sql.Date;
import java.util.List;

public interface AccountService {

    List<Account> findAll();
    Account findByClientId(int clientId);
    Account findByClientCNP(String cnp);
    Notification<Account>getAccountById(int id);
    boolean  removeById(int id);
    boolean create(Account account);
    Notification <Boolean> update(Account account);
    void removeAll();
    Notification<Boolean> transfer(TransferDTO transferDTO);
    Notification<Boolean> payBill(TransferDTO transferDTO);

}
