package services;

import java.util.List;

import laucher.ComponentFactory;
import model.User;
import model.builder.UserBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import service.user.UserService;

import static org.junit.Assert.assertEquals;

public class UserServicesTest {

    private static UserService userService;

    @BeforeClass
    public static void setUpClass()
    {
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        userService = componentFactory.getUserService();
    }

    @Before
    public void cleadUp()
    {
        userService.removeAll();
    }

    @Test
    public void getAllUsers()
    {
        List<User> userList = userService.getAllUsers();
        assertEquals(0,userList.size());
    }

    @Test
    public void save()
    {
        User user = new UserBuilder()
                .setUsername("roxana@bank.com")
                .setPassword("Roxana9*")
                .build();
        userService.save(user);
        List<User> userList = userService.getAllUsers();
        assertEquals(1,userList.size());
    }

    @Test
    public void findByName()
    {
        User user1 = new UserBuilder()
                .setId(1L)
                .setUsername("roxana@bank.com")
                .setPassword("Roxana9*")
                .build();
        userService.save(user1);
        User user2 = new UserBuilder()
                .setId(2L)
                .setUsername("e1@bank.com")
                .setPassword("Employee1!")
                .build();
        userService.save(user2);
        User user3 = new UserBuilder()
                .setId(3L)
                .setUsername("e2@bank.com")
                .setPassword("Employee2@")
                .build();
        String usernameToBeFound = "e1@bank.com";
        User userToBeFound = userService.findByName(usernameToBeFound);
        assertEquals(usernameToBeFound,userToBeFound.getUsername());


    }
    @Test
    public void remove()
    {
        User user1 = new UserBuilder()
                .setId(1L)
                .setUsername("roxana@bank.com")
                .setPassword("Roxana9*")
                .build();
        userService.save(user1);
        User user2 = new UserBuilder()
                .setId(2L)
                .setUsername("e1@bank.com")
                .setPassword("Employee1!")
                .build();
        userService.save(user2);
        userService.remove(user2);
        List<User> userList = userService.getAllUsers();
        assertEquals(1,userList.size());
    }

    @Test
    public void removeAll()
    {
        User user1 = new UserBuilder()
                .setUsername("roxana@bank.com")
                .setPassword("Roxana9*")
                .build();
        userService.save(user1);
        User user2 = new UserBuilder()
                .setUsername("e1@bank.com")
                .setPassword("Employee1!")
                .build();
        userService.save(user2);
        userService.removeAll();
        List<User> userList = userService.getAllUsers();
        assertEquals(0,userList.size());
    }

    @Test
    public void update()
    {
        String newPassword = "Administrator1!";
        User user1 = new UserBuilder()
                .setUsername("roxana@bank.com")
                .setPassword("Roxana9*")
                .build();
        userService.save(user1);
        user1.setPassword(newPassword);
        userService.update(user1);
        assertEquals(newPassword,user1.getPassword());
    }

}
