package controller;

import jdk.nashorn.internal.runtime.ParserException;
import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import service.report.ReportService;
import service.user.AuthentificationService;
import service.user.UserService;
import view.AdminView;
import view.LoginView;

import javax.swing.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController {

    private final AdminView adminView;
    private final AuthentificationService authentificationService;
    private final UserService userService;
    private final ReportService reportService;

    public AdminController (AdminView adminView, UserService userService, ReportService reportService, AuthentificationService authentificationService)
    {
        this.adminView = adminView;
        this.userService = userService;
        this.reportService = reportService;
        this.authentificationService = authentificationService;
        adminView.setCreateButtonListener(new createButtonListener());
        adminView.setDeleteButtonListener(new deleteButtonListener());
        adminView.setUpdateButtonListener(new updateButtonListener());
        adminView.setReportButtonListener(new reportButtonListener());
    }

    public void visible()
    {
        adminView.setVisible();
    }

    public void updateUserDatabase()
    {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("id");
        defaultTableModel.addColumn("username");
        defaultTableModel.addColumn("password");

        for(User user : userService.getAllUsers())
        {
            Object[] object = {user.getId(),user.getUsername(), user.getPassword()};
            defaultTableModel.addRow(object);
        }
    }

    private class createButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            String username = adminView.getUsername();
            String password = adminView.getPassword();

            Notification<Boolean> notification = authentificationService.register(username,password);
            if(notification.hasErrors())
            {
                JOptionPane.showMessageDialog(adminView.getContentPane(),"Cannot add");
            }
            else
            {
                updateUserDatabase();
                JOptionPane.showMessageDialog(adminView.getContentPane(),"Your new employee is added into the database");
            }

        }
    }

    private class deleteButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            String username = adminView.getUsername();
            User user = userService.findByName(username);
            userService.remove(user);
            JOptionPane.showMessageDialog(adminView.getContentPane(),"User deleted successfully!");

        }
    }

    private class updateButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class reportButtonListener implements  ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}
