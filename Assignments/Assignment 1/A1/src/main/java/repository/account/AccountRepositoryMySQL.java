package repository.account;

import model.Account;
import model.builder.AccountBuilder;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.ACCOUNT;
import static database.Constants.Tables.CLIENT;

public class AccountRepositoryMySQL implements  AccountRepository{

    private final Connection connection;

    public AccountRepositoryMySQL (Connection connection)
    {
        this.connection = connection;
    }


    @Override
    public List<Account> findAll() {
        try {
            List<Account> accountList = new ArrayList<>();
            Statement statement = connection.createStatement();
            String fetchAccountSql = "SELECT * FROM `" + ACCOUNT + "`";
            ResultSet accountResultSet = statement.executeQuery(fetchAccountSql);
            if (accountResultSet.next())
            {
                Account account = new AccountBuilder()
                        .setIdNb(accountResultSet.getInt("idNb"))
                        .setAmount(accountResultSet.getInt("amount"))
                        .setType(accountResultSet.getString("type"))
                        .setDateOfCreation(accountResultSet.getDate("dateOfCreation"))
                        .setIdClient(accountResultSet.getInt("idClient"))
                        .build();
                accountList.add(account);
            }
            return accountList;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public boolean removeById (int id)
    {
        try {
        PreparedStatement removeByIdStatement = connection
                .prepareStatement("DELETE`" + ACCOUNT + "` WHERE id =" + id);
        removeByIdStatement.executeUpdate();
        return true;
    }
    catch (SQLException e)
    {
        e.printStackTrace();
        return false;
    }
    }

    @Override
    public Account findByClientId(int idClient) {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `" + ACCOUNT + "` WHERE idClient = \'" + idClient +"\'");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                return getAccount(resultSet);
            }
        } catch (SQLException e){

        }
        return null;
    }

    @Override
    public boolean save(Account account) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO`" + ACCOUNT + "` values (null,?,?,?,?,?)");
            insertStatement.setInt(1,account.getIdNb());
            insertStatement.setString(2, account.getType());
            insertStatement.setInt(3,account.getAmount());
            insertStatement.setDate(4, (Date) account.getDataOfCreation());
            insertStatement.setInt(5,account.getIdClient());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Account account) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `"+ACCOUNT +"` SET idClient = ?, idNb = ?, type = ?, amount = ?, dateOfCreation = ? WHERE id =?");
            statement.setLong(1,account.getIdClient());
            statement.setInt(2,account.getIdNb());
            statement.setString(3,account.getType());
            statement.setInt(4,account.getAmount());
            statement.setDate(5, (Date) account.getDataOfCreation());
            statement.setInt(6,account.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();

        }
        return false;
    }

    private Account getAccount (ResultSet rs) throws SQLException
    {
        return new AccountBuilder()
                .setId(rs.getInt("id"))
                .setIdNb(rs.getInt("idNb"))
                .setType(rs.getString("type"))
                .setAmount(rs.getInt("amount"))
                .setDateOfCreation(rs.getDate("date"))
                .setIdClient(rs.getInt("id"))
                .build();
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
