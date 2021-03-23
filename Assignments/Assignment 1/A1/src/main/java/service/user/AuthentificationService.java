package service.user;

import model.User;
import model.validation.Notification;

public interface AuthentificationService {

    Notification<User> login(String username, String password);

    boolean logout(User user);

    Notification<Boolean> register(String username, String password);
}
