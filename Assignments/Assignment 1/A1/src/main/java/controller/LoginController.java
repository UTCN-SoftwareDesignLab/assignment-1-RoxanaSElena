package controller;

import model.User;
import model.validation.Notification;
import service.user.AuthentificationService;
import view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static database.Constants.Roles.ADMINISTRATOR;

/**
 * Created by Alex on 18/03/2017.
 */
public class LoginController {
    private final LoginView loginView;
    private final AuthentificationService authentificationService;
    private final AdminController adminController;
    private final EmployeeController employeeController;

    public LoginController(LoginView loginView, AuthentificationService authentificationService, AdminController adminController, EmployeeController employeeController) {
        this.loginView = loginView;
        this.authentificationService = authentificationService;
        this.adminController = adminController;
        this.employeeController = employeeController;
        loginView.setLoginButtonListener(new LoginButtonListener());
        loginView.setRegisterButtonListener(new RegisterButtonListener());
    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = authentificationService.login(username, password);

            if (loginNotification.hasErrors()) {
                JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
            } else {
                JOptionPane.showMessageDialog(loginView.getContentPane(), "Login successful!");
               /* if(loginNotification.getResult().getRoles().get(0).getRole().equals(ADMINISTRATOR))
                {
                    adminController.visible();
                }
                else
                {*/
                    employeeController.visible();
               // }
            }
        }
    }

    private class RegisterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Boolean> registerNotification = authentificationService.register(username, password);

            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(loginView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration successful!");
                }
            }
        }
    }


}
