package view;

import model.User;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import static javax.swing.BoxLayout.Y_AXIS;


public class AdminView extends JFrame {
    private JButton btnCreate;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnReport;

    private JTextField userTxt;
    private JTextField passTxt;




    public AdminView() throws HeadlessException
    {
        setSize(500,500);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(btnCreate);
        add(btnUpdate);
        add(btnDelete);
        add(btnReport);
        add(userTxt);
        add(passTxt);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }

    public void initializeFields()
    {
        btnCreate = new JButton("Create");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnReport = new JButton("Generate Report");
        userTxt = new JTextField("username");
        passTxt = new JTextField("password");

    }

    public void setCreateButtonListener(ActionListener createButtonListener)
    {
        btnCreate.addActionListener(createButtonListener);
    }

    public void setUpdateButtonListener (ActionListener updateButtonListener)
    {
        btnUpdate.addActionListener(updateButtonListener);
    }

    public void setDeleteButtonListener (ActionListener deleteButtonListener)
    {
        btnDelete.addActionListener(deleteButtonListener);
    }

    public void setReportButtonListener (ActionListener reportButtonListener)
    {
        btnReport.addActionListener(reportButtonListener);
    }

    public long getId()
    {
        long id = Long.parseLong((this.userTxt.getText()));
        return id;

    }

    public void setVisible()
    {
        this.setVisible(true);
    }

    public void setNotVisible()
    {
        this.setVisible(false);
    }

    public String getUsername()
    {
        String username = this.userTxt.getText();
        return username;
    }

    public String getPassword()
    {
        String password = this.passTxt.getText();
        return password;
    }






}
