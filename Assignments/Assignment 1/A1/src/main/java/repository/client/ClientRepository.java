package repository.client;

import model.Client;
import model.validation.Notification;

import  java.util.List;

public interface ClientRepository {
    List <Client> findAll();
    Client findByName(String name);
    boolean save(Client client);
    boolean update (Client client);
    boolean remove(Client client);
    void removeAll();

}
