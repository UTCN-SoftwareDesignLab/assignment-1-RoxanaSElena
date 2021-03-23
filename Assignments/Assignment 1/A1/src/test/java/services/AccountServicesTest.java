package services;

import laucher.ComponentFactory;
import model.Account;
import model.builder.AccountBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import service.account.AccountService;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AccountServicesTest {

    private static AccountService accountService;

    @BeforeClass
    public static void setUpClass ()
    {
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        accountService = componentFactory.getAccountService();
    }

    @Before
    public void cleanUp()
    {
        accountService.removeAll();
    }

    @Test
    public void findAll()
    {
        List<Account> accountList = accountService.findAll();
        assertEquals(0,accountList.size());
    }

    @Test
    public void save()
    {
        Account account1 = new AccountBuilder()
                .setId(1)
                .setAmount(100)
                .setType("debit")
                .build();
        accountService.create(account1);
        List<Account> accountList = accountService.findAll();
        assertEquals(1,accountList.size());
    }

    @Test
    public void removeAll()
    {
        Account account1 = new AccountBuilder().build();
        Account account2 = new AccountBuilder().build();
        accountService.create(account1);
        accountService.create(account2);
        accountService.removeAll();
        List<Account> accountList = accountService.findAll();
        assertEquals(0,accountList.size());
    }

}
