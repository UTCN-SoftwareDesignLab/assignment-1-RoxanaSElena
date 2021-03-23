package repository;

import laucher.ComponentFactory;

import model.User;
import model.builder.UserBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.security.RightsRolesRepository;
import repository.user.UserRepository;

import java.util.List;

import static org.junit.Assert.*;


public class UserTest {

    private static UserRepository userRepository;
    private static RightsRolesRepository rightsRolesRepository;

   @BeforeClass
    public static void setupClass() {
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        userRepository = componentFactory.getUserRepository();
        rightsRolesRepository = componentFactory.getRightsRolesRepository();

    }

    @Before
    public void cleanUp()
    {
        userRepository.removeAll();
    }

    @Test
    public void findAll()
    {
        List<User> userList = userRepository.findAll();
        assertEquals(userList.size(),0);
    }

    @Test
    public void findAll2()
    {
        User user1 = new UserBuilder()
                .setUsername("user1")
                .setPassword("Password1!")
                .build();
        userRepository.save(user1);
        List <User> userList = userRepository.findAll();
        assertEquals(1,userList.size());
    }

    @Test
    public void save()
    {
        assertTrue(userRepository.save(
                new UserBuilder()
                        .setUsername("user")
                        .setPassword("User9*")
                        .build()));
    }

    @Test
    public void findByUsernameAndPassword()
    {
        String username = "user";
        String password = "User9*";
        User user = new UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();
        userRepository.save(user);
        User findUser = userRepository.findByUsernameAndPassword(username,password).getResult();
        assertEquals(user.getUsername(),findUser.getUsername());
    }

    @Test
    public void update()
    {
        String username = "user";
        String password = "User9*";
        String newPassword = "User99*";
        User user = new UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();
        userRepository.save(user);
        user.setPassword(newPassword);
        userRepository.update(user);
        assertFalse( userRepository.findByUsernameAndPassword(username,password).hasErrors());
    }

}
