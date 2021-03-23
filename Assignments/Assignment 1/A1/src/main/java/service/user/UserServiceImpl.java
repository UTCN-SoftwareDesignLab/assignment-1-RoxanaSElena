package service.user;

import model.User;
import model.validation.Notification;
import repository.security.RightsRolesRepository;
import repository.user.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;

    public UserServiceImpl (UserRepository userRepository, RightsRolesRepository rightsRolesRepository)
    {
        this.userRepository = userRepository;
        this.rightsRolesRepository = rightsRolesRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean update(User user) {
        return userRepository.update(user);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public boolean remove(User user) {
        return userRepository.remove(user);
    }

    @Override
    public void removeAll() { userRepository.removeAll(); }
}
