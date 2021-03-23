package service.client;

import model.Client;
import model.validation.Notification;
import repository.client.ClientRepository;

import java.util.List;


public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository)
    {
        this.clientRepository = clientRepository;
    }


    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findByName(String name) {
        return clientRepository.findByName(name);
    }
    @Override
    public void removeAll() {
        clientRepository.removeAll();
    }

    @Override
    public boolean remove(Client client) {
        return clientRepository.remove(client);
    }

    @Override
    public boolean save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Notification<Boolean> update(Client client) {

        Notification<Boolean> updateNotification = new Notification<>();
        if (clientRepository.update(client)){
            updateNotification.setResult(true);
        }
        else{
            updateNotification.addError("User couldn't be updated");
        }
        return updateNotification;
    }
}
