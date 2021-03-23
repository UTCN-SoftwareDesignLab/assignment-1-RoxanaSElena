package repository.user;

import model.Role;
import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import repository.security.RightsRolesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.CLIENT;
import static database.Constants.Tables.USER;

public class UserRepositoryMySQL implements UserRepository{
    private final Connection connection;
    private final RightsRolesRepository rightsRolesRepository;


    public UserRepositoryMySQL(Connection connection, RightsRolesRepository rightsRolesRepository){//RightsRolesRepository rightsRolesRepository) {
        this.connection = connection;
        this.rightsRolesRepository = rightsRolesRepository;

    }

    @Override
    public List<User> findAll() {
        try {
            List<User> userList = new ArrayList<>();
            Statement statement = connection.createStatement();
            String fetchUserSql = "Select * from `" + USER + "`";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if (userResultSet.next()) {
                User user = new UserBuilder()
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                        .build();
                userList.add(user);
            }
            return userList;
        }catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) {
        Notification<User> findByUsernameAndPasswordNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String fetchUserSql = "Select * from `" + USER + "` where `username`=\'" + username + "\' and `password`=\'" + password + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if (userResultSet.next()) {
                User user = new UserBuilder()
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                         //.setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                        .build();
                findByUsernameAndPasswordNotification.setResult(user);
                return findByUsernameAndPasswordNotification;
            } else {
                findByUsernameAndPasswordNotification.addError("Invalid email or password!");
                return findByUsernameAndPasswordNotification;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            findByUsernameAndPasswordNotification.addError("Something is wrong with the Database");
        }
        return findByUsernameAndPasswordNotification;
    }

/*    public boolean removeById (long id)
    {
        try {
            PreparedStatement removeByIdStatement = connection
                    .prepareStatement("DELETE`" + USER + "` WHERE id =" + id);
            removeByIdStatement.executeUpdate();
            return true;
        }
        catch (SQLException e)
        {
          e.printStackTrace();
          return false;
        }
    }*/

    public boolean remove(User user) {
        try{
            PreparedStatement deleteAccountStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?");
            deleteAccountStatement.setLong(1, user.getId());
            deleteAccountStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(User user) {

        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("UPDATE " + USER + " set username= ? , password=? WHERE username = ?;");
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean save(User user) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO `"+USER+"` values(null,?,?)");
            insertStatement.setString(1, user.getUsername());
            insertStatement.setString(2, user.getPassword());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

/*
    @Override
    public Notification<User> findById(Long userId) {
        Notification<User> userNotification = new Notification<>();
        try
        {
            Statement statement = connection.createStatement();
            String fetchUserSql = "Select * from`" + USER + "` where id=" + userId;
            ResultSet resultSet = statement.executeQuery(fetchUserSql);
            if(resultSet.next())
            {
                User user = new UserBuilder()
                        .setId(resultSet.getLong("id"))
                        .setUsername(resultSet.getString("username"))
                        .setPassword(resultSet.getString("password"))
                        .build();
                userNotification.setResult(user);
                return userNotification;
            }
        }catch(SQLException e)
        {
            e.printStackTrace();

        }
        return userNotification;
    }
*/

    @Override
    public User findByName(String name) {
        try {
            Statement statement = connection.createStatement();
            String fetchUserSql = "Select * from `" + USER + "` where username = \'" + name+"\'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if (userResultSet.next()) {
                return getUserFromResultSet(userResultSet);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException{
        UserBuilder builder = new UserBuilder();

        return builder
                .setId(resultSet.getLong("id"))
                .setUsername(resultSet.getString("username"))
                .setPassword(resultSet.getString("password"))
                //.setRoles((Role) rightsRolesRepository.findRolesForUser(resultSet.getLong("id")))
                .build();
    }
}
