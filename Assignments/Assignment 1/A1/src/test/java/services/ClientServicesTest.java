package services;

import java.util.List;

import laucher.ComponentFactory;
import model.Client;
import model.builder.ClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import service.client.ClientService;

import static org.junit.Assert.assertEquals;

public class ClientServicesTest {

    private static ClientService clientService;
    @BeforeClass
    public static void setUpClass()
    {
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        clientService = componentFactory.getClientService();
    }

    @Before
    public void cleanUp()
    {
        clientService.removeAll();
    }

    @Test
    public void findAll()
    {
        List<Client> clientList = clientService.findAll();
        assertEquals(0,clientList.size());
    }

    @Test
    public void save()
    {
        Client client1 = new ClientBuilder()
                .setName("Vladut")
                .setAddress("Campia-Turzii")
                .setCNP("19809")
                .build();
        clientService.save(client1);
        List<Client> clientList = clientService.findAll();
        assertEquals(1,clientList.size());
    }

    @Test
    public void findByName()
    {
        Client client1 = new ClientBuilder()
                .setId(1)
                .setName("Vladut")
                .setAddress("Campia-Turzii")
                .setCNP("19809")
                .build();
        clientService.save(client1);
        Client client2 = new ClientBuilder()
                .setId(2)
                .setName("Jules")
                .setAddress("Cluj-Napoca")
                .setCNP("2991111")
                .build();
        clientService.save(client2);
        Client client3 = new ClientBuilder()
                .setId(3)
                .setName("Bianca")
                .setAddress("Blaj")
                .setCNP("29908")
                .build();
        clientService.save(client3);
        String nameToBeFound  = "Jules";
        Client clientToBeFound = clientService.findByName(nameToBeFound);
        assertEquals(nameToBeFound, clientToBeFound.getName());
    }

    @Test
    public void remove()
    {
        Client client1 = new ClientBuilder()
                .setId(1)
                .setName("Vladut")
                .setAddress("Campia-Turzii")
                .setCNP("19809")
                .build();
        clientService.save(client1);
        Client client2 = new ClientBuilder()
                .setId(2)
                .setName("Jules")
                .setAddress("Cluj-Napoca")
                .setCNP("2991111")
                .build();
        clientService.save(client2);

        clientService.remove(client2);
        List<Client> clientList = clientService.findAll();
        assertEquals(1,clientList.size());
    }

    @Test
    public void removeAll()
    {
        Client client1 = new ClientBuilder()
                .setId(1)
                .setName("Vladut")
                .setAddress("Campia-Turzii")
                .setCNP("19809")
                .build();
        clientService.save(client1);
        Client client2 = new ClientBuilder()
                .setId(2)
                .setName("Jules")
                .setAddress("Cluj-Napoca")
                .setCNP("2991111")
                .build();
        clientService.save(client2);
        Client client3 = new ClientBuilder()
                .setId(3)
                .setName("Bianca")
                .setAddress("Blaj")
                .setCNP("29908")
                .build();
        clientService.save(client3);
        clientService.removeAll();
        List<Client> clientList = clientService.findAll();
        assertEquals(0,clientList.size());
    }

    @Test
    public void update()
    {
        Client client1 = new ClientBuilder()
                .setId(1)
                .setName("Vladut")
                .setAddress("Campia-Turzii")
                .setCNP("19809")
                .build();
        clientService.save(client1);
        String addressToUpdate = "Cluj-Napoca";
        client1.setAddress(addressToUpdate);
        Assert.assertTrue(clientService.update(client1).getResult());
    }
}
