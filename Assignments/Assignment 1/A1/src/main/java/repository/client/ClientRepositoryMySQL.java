package repository.client;
import model.Client;

import model.builder.ClientBuilder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.CLIENT;

public class ClientRepositoryMySQL implements ClientRepository {

    private final Connection connection;
    public ClientRepositoryMySQL (Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public List<Client> findAll() {
        List <Client> clientList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String fetchClientSql = "SELECT * FROM `" + CLIENT + "`";
            ResultSet clientResultSet = statement.executeQuery(fetchClientSql);
            if (clientResultSet.next())
            {
                Client client = new ClientBuilder()
                        .setName(clientResultSet.getString("name"))
                        .setCardNb(clientResultSet.getInt("cardNb"))
                        .setAddress(clientResultSet.getString("address"))
                        .setCNP(clientResultSet.getString("CNP"))
                        .build();
                clientList.add(client);
            }
            return clientList;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client findByName(String name) {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `" + CLIENT + "` WHERE name = \'" + name +"\'");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                return getClientFromResultSet(resultSet);
            }
        } catch (SQLException e){

        }
        return null;
    }

    @Override
    public boolean remove(Client client) {
        try{
            PreparedStatement deleteAccountStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?");
            deleteAccountStatement.setLong(1, client.getId());
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
            String sql = "DELETE from client where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(Client client) {

        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE `" + CLIENT + "` SET name = ?, cardNb = ?, address = ?, CNP = ? WHERE id = ?");
            statement.setString(1,client.getName());
            statement.setInt(2,client.getCardNb());
            statement.setString(3,client.getAddress());
            statement.setString(4,client.getCNP());
            statement.setInt(5,client.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e){

        }
        return false;
    }

    @Override
    public boolean save(Client client) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO `"+CLIENT+"` values(null,?,?,?,?)");
            insertStatement.setString(1, client.getName());
            insertStatement.setInt(2,client.getCardNb());
            insertStatement.setString(3, client.getAddress());
            insertStatement.setString(4, client.getCNP());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Client getClientFromResultSet(ResultSet rs) throws SQLException{
        return new ClientBuilder()
                .setId(rs.getInt("id"))
                .setName(rs.getString("name"))
                .setCardNb(rs.getInt("cardNb"))
                .setAddress(rs.getString("address"))
                .setCNP(rs.getString("CNP"))
                .build();
    }
}
