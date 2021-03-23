package repository;

import database.DBConnectionFactory;
import model.Client;
import model.builder.ClientBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import java.util.List;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;

public class ClientTest {

    public static ClientRepository clientRepository;

    @BeforeClass
    public static void setupClass()
    {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        clientRepository= new ClientRepositoryMySQL(connection);
    }

    @Before
    public void cleanup()
    {
        clientRepository.removeAll();
    }

    @Test
    public void findAll()
    {
        List<Client> clientList = clientRepository.findAll();
        assertEquals(clientList.size(),0);
    }


    @Test
    public void save()
    {
        Client client = new ClientBuilder()
                .setName("Ana")
                .setAddress("Bucovina")
                .setCNP("60001")
                .build();
        clientRepository.save(client);
        List <Client> clientList = clientRepository.findAll();
        assertEquals(1,clientList.size());
    }

    @Test
    public void findByName()
    {
        Client client1 = new ClientBuilder()
                .setName("RoxanaClient")
                .setAddress("Fagaras")
                .setCNP("2991109")
                .build();
        clientRepository.save(client1);
        Client client2 = new ClientBuilder()
                .setName("Vladut")
                .setAddress("Campia-Turzii")
                .setCNP("19809")
                .build();
        clientRepository.save(client2);
        Client client3 = new ClientBuilder()
                .setName("Jules")
                .setAddress("Cluj-Napoca")
                .setCNP("2991111")
                .build();
        clientRepository.save(client3);
        Client findClient = clientRepository.findByName("Vladut");
        assertEquals(client2.getName(),findClient.getName());
    }

    @Test
    public void update()
    {
        String name = "Cristina";
        String address = "Severin";
        String newAddress = "Cluj";
        Client client = new ClientBuilder()
                .setName(name)
                .setAddress(address)
                .build();
        clientRepository.save(client);
        client.setAddress(newAddress);
        clientRepository.update(client);
        assertEquals(newAddress,client.getAddress());
    }

    @Test
    public void remove()
    {
        Client client1 = new ClientBuilder()
                .setName("RoxanaClient")
                .setAddress("Fagaras")
                .setCNP("2991109")
                .build();
        clientRepository.save(client1);
        Client client2 = new ClientBuilder()
                .setName("Vladut")
                .setAddress("Campia-Turzii")
                .setCNP("19809")
                .build();
        clientRepository.save(client2);
        clientRepository.remove(client1);
        List<Client> clientList = clientRepository.findAll();
        assertEquals(1,clientList.size());
    }

    @Test
    public void removeAll()
    {
        Client client1 = new ClientBuilder()
                .setName("RoxanaClient")
                .setAddress("Fagaras")
                .setCNP("2991109")
                .build();
        clientRepository.save(client1);
        Client client2 = new ClientBuilder()
                .setName("Vladut")
                .setAddress("Campia-Turzii")
                .setCNP("19809")
                .build();
        clientRepository.save(client2);
        clientRepository.removeAll();
        List <Client> clientList = clientRepository.findAll();
        assertEquals(0,clientList.size());
    }
}
