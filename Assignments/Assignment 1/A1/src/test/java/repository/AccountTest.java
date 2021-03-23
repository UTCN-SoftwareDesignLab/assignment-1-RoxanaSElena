package repository;

import database.DBConnectionFactory;
import model.Account;
import model.builder.AccountBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.client.ClientRepository;

import java.util.List;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    public static AccountRepository accountRepository;
    public static ClientRepository clientRepository;

    @BeforeClass
    public static void setUpClass()
    {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        accountRepository = new AccountRepositoryMySQL(connection);
    }

    @Before
    public void cleanUp()
    {
        accountRepository.removeAll();
    }

    @Test
    public void findAll(){
        List<Account> accountList = accountRepository.findAll();
        assertEquals(0,accountList.size());
    }

    @Test
    public void save()
    {
        Account account = new AccountBuilder()
                .setId(1)
                .setType("debit")
                .setAmount(100)
                .build();
        accountRepository.save(account);
        List<Account> accountList = accountRepository.findAll();
        assertEquals(1,accountList.size());
    }

    @Test
    public void update () {
        Account account = new AccountBuilder()
                .setId(1)
                .setType("debit")
                .setAmount(150)
                .setIdClient(2)
                .build();
        int newAmount = 50;
        accountRepository.save(account);
        account.setAmount(newAmount);
        accountRepository.update(account);
        assertEquals(newAmount,account.getAmount());
    }

    @Test
    public void remove()
    {
        int idToBeDeleted = 1;
        Account account = new AccountBuilder()
                .setId(1)
                .build();
        accountRepository.save(account);
        accountRepository.removeById(idToBeDeleted);
        List<Account> accountList = accountRepository.findAll();
        assertEquals(0,accountList.size());
    }

    @Test
    public void removeAll()
    {
        Account account1 = new AccountBuilder().build();
        Account account2 = new AccountBuilder().build();
        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.removeAll();
        List<Account> accountList = accountRepository.findAll();
        assertEquals(0,accountList.size());
    }

}
