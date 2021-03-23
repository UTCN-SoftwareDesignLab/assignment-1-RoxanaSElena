package services;

import laucher.ComponentFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.user.UserRepository;
import service.user.AuthentificationService;

public class AuthentificationServicesTest {

    public static final String TEST_USERNAME = "test@username.com";
    public static final String TEST_PASSWORD = "TestPassword1@";

    private static AuthentificationService authentificationService;
    private static UserRepository userRepository;

    @BeforeClass
    public static void setUpClass()
    {
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        userRepository = componentFactory.getUserRepository();
        authentificationService =componentFactory.getAuthenticationService();
    }

    @Before
    public void cleanUp()
    {
        userRepository.removeAll();
    }

    @Test
    public void register()
    {
        Assert.assertFalse(authentificationService.register(TEST_USERNAME, TEST_PASSWORD).hasErrors());
    }
    @Test
    public void login()
    {
        register();
        Assert.assertFalse(authentificationService.login(TEST_USERNAME,TEST_PASSWORD).hasErrors());
    }
}
