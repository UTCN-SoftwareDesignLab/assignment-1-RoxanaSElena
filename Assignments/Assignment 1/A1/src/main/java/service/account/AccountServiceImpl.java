package service.account;

import model.Account;
import model.validation.Notification;
import repository.account.AccountRepository;

import java.util.List;


public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    public AccountServiceImpl (AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }


    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findByClientId (int clientId)
    {
        return accountRepository.findByClientId(clientId);
    }

    @Override
    public boolean removeById(int id) {
        return accountRepository.removeById(id);
    }

    @Override
    public boolean create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Notification<Boolean> update(Account account) {
        Notification<Boolean> updateNotification = new Notification<>();
        if (accountRepository.update(account)){
            updateNotification.setResult(true);
        }
        else{
            updateNotification.addError("User couldn't be updated");
        }
        return updateNotification;
    }

    @Override
    public void removeAll() {
        accountRepository.removeAll();
    }
}
