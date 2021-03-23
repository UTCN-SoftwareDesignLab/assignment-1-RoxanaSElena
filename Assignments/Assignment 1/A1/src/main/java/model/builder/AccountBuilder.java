package model.builder;

import model.Account;

import java.time.LocalDate;
import java.util.Date;


public class AccountBuilder {

    private Account account;

    public AccountBuilder ()
    {
        account = new Account();
    }

    public AccountBuilder setId(int id)
    {
        account.setId(id);
        return this;
    }

    public AccountBuilder setIdNb (int idNb)
    {
        account.setIdNb(idNb);
        return this;
    }

    public AccountBuilder setAmount (int amount)
    {
        account.setAmount(amount);
        return this;
    }

    public AccountBuilder setType (String type)
    {
        account.setType(type);
        return this;
    }

    public AccountBuilder setDateOfCreation (Date dateOfCreation)
    {
        account.setDataOfCreation(dateOfCreation);
        return this;
    }

    public AccountBuilder setIdClient (int idClient)
    {
        account.setIdClient(idClient);
        return this;
    }

    public Account build()
    {
        return  account;
    }

}
