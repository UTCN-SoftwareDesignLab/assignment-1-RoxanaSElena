package service.user;

import model.User;

import java.util.List;

public interface UserService {

    List <User> getAllUsers();
    boolean save(User user);
    boolean update(User user);
    User findByName(String name);
    boolean remove(User user);
    void removeAll();


}
