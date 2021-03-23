package service.client;

import model.Client;
import model.validation.Notification;

import java.util.List;

public interface ClientService {

    List<Client> findAll();
    Client findByName(String name);
    Notification <Boolean> update(Client client);
    boolean save (Client client);
    boolean remove(Client client);
    void removeAll();

}
