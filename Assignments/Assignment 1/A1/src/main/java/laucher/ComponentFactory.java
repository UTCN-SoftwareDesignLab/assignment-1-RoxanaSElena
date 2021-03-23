package laucher;

import controller.*;
import database.DBConnectionFactory;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.report.ReportRepository;
import repository.report.ReportRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;

import service.account.AccountService;
import service.account.AccountServiceImpl;
import service.client.ClientService;
import service.client.ClientServiceImpl;
import service.report.ReportService;
import service.report.ReportServiceImpl;
import service.user.AuthentificationService;
import service.user.AuthentificationServiceMySQL;
import service.user.UserService;
import service.user.UserServiceImpl;
import view.AdminView;
import view.EmployeeView;
import view.LoginView;
import view.TransferView;

import java.sql.Connection;

/**
 * Created by Alex on 18/03/2017.
 */
public class ComponentFactory {

    private final LoginView loginView;
    private final AdminView adminView;
    private final EmployeeView employeeView;
    private final TransferView transferView;

    private final LoginController loginController;
    private final AdminController adminController;
    private final EmployeeController employeeController;
    private final TransferController transferController;
    private final BillController billController;

    private final AuthentificationService authentificationService;
    private final ReportService reportService;
    private final UserService userService;
    private final ClientService clientService;
    private final AccountService accountService;

    private final UserRepository userRepository;
    private final ReportRepository reportRepository;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final RightsRolesRepository rightsRolesRepository;


    private static ComponentFactory instance;
    private ClientRepositoryMySQL clientRepositoryMySQL;

    public static ComponentFactory instance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTests);
        }
        return instance;
    }

    private ComponentFactory(Boolean componentsForTests) {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(componentsForTests).getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        this.reportRepository = new ReportRepositoryMySQL(connection);
        this.clientRepository = new ClientRepositoryMySQL(connection);
        this.accountRepository = new AccountRepositoryMySQL(connection);
        this.authentificationService = new AuthentificationServiceMySQL(this.userRepository, this.rightsRolesRepository);
        this.userService = new UserServiceImpl(this.userRepository,this.rightsRolesRepository);
        this.reportService = new ReportServiceImpl(reportRepository);
        this.clientService = new ClientServiceImpl(clientRepository);
        this.accountService = new AccountServiceImpl(accountRepository);
        this.loginView = new LoginView();
        this.adminView = new AdminView();
        this.employeeView = new EmployeeView();
        this.transferView = new TransferView();
        this.adminController = new AdminController(adminView,userService,reportService,authentificationService);
        this.transferController = new TransferController(transferView,accountService,clientService);
        this.billController = new BillController(transferView,accountService,clientService);
        this.employeeController = new EmployeeController(employeeView,clientService,transferController,billController,accountService);
        this.loginController = new LoginController(loginView,authentificationService,adminController,employeeController);
        clientRepositoryMySQL = new ClientRepositoryMySQL(connection);
    }


    public UserRepository getUserRepository() {
        return userRepository;
    }
    public ReportRepository getReportRepository() {return reportRepository;}

    public AuthentificationService getAuthenticationService() {
        return authentificationService;
    }
    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }
    public ReportService getReportService() {return  reportService;}
    public UserService getUserService() {return userService; }
    public ClientService getClientService() {return clientService; }
    public AccountService getAccountService() {return  accountService;}

    public LoginView getLoginView() {
        return loginView;
    }
    public AdminView getAdminView() {return adminView;}
    public EmployeeView getEmployeeView() {return  employeeView;}


    public LoginController getLoginController() {
        return loginController;
    }
    public AdminController getAdminController() {return adminController;}
    public ClientRepository getClientRepository() {
        return clientRepository;
    }

}