package service.account;

import model.Account;
import model.DTO.TransferDTO;
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

    @Override
    public Notification<Account> getAccountById(int id) {
        Notification<Account> notification = new Notification<>();
        Account accountToFind = accountRepository.findById(id);
        if(accountToFind == null)
        {
            notification.setResult(null);
        }
        else
        {
            notification.setResult(accountToFind);
        }
        return notification;
    }

    @Override
    public Account findByClientCNP(String cnp) {
        return findByClientCNP(cnp);
    }

    @Override
    public Notification<Boolean> transfer(TransferDTO transferDTO) {
        Notification<Boolean> notification = new Notification<>();
        try
        {
            int amount = transferDTO.getAmount();

            int idTo = findByClientCNP(transferDTO.getTo()).getId();
            int idFrom = findByClientCNP(transferDTO.getTo()).getId();

            Notification<Account> accountNotificationTo = getAccountById(idTo);
            Notification<Account> accountNotificationFrom = getAccountById(idFrom);
            if(accountNotificationTo.hasErrors() || accountNotificationFrom.hasErrors() )
            {
                notification.setResult(false);
            }
            else
            {
                Account accountTo = accountNotificationTo.getResult();
                int accountToAmount = accountTo.getAmount();
                Account accountFrom = accountNotificationFrom.getResult();
                int accountFromAmount = accountFrom.getAmount();

                if(accountFrom.getAmount() < amount) {
                    notification.addError("Not emough money for the transfer");
                    notification.setResult(false);
                }

                else
                {
                    accountTo.setAmount(accountToAmount + amount);
                    accountFrom.setAmount(accountFromAmount - amount);
                    notification.setResult(true);
                }
            }
        }catch(Exception e)
        {
            notification.addError("Account balance not right for transaction");
            notification.setResult(false);
        }
        return  notification;
    }

    @Override
    public Notification<Boolean> payBill(TransferDTO transferDTO) {
        Notification<Boolean> notification = new Notification<>();
        try
        {
            int amount = transferDTO.getAmount();

            int idFrom = findByClientCNP(transferDTO.getTo()).getId();

            Notification<Account> accountNotificationFrom = getAccountById(idFrom);
            if( accountNotificationFrom.hasErrors() )
            {
                notification.setResult(false);
            }
            else
            {
                Account accountFrom = accountNotificationFrom.getResult();
                int accountFromAmount = accountFrom.getAmount();

                if(accountFrom.getAmount() < amount) {
                    notification.addError("Not emough money for the transfer");
                    notification.setResult(false);
                }

                else
                {
                    accountFrom.setAmount(accountFromAmount - amount);
                    notification.setResult(true);
                }
            }
        }catch(Exception e)
        {
            notification.addError("Account balance not right for transaction");
            notification.setResult(false);
        }
        return  notification;
    }
}
