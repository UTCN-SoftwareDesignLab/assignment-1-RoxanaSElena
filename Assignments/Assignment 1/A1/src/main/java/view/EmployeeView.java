package view;

import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;

import static javax.swing.BoxLayout.Y_AXIS;

public class EmployeeView extends JFrame {



    private JButton btnAddClient;
    private JButton btnUpdateClient;
    private JButton btnViewClient;
    private JButton btnDeleteClient;

    private JButton btnAddAccount;
    private JButton btnUpdateAccount;
    private JButton btnDeleteAccount;
    private JButton btnViewAccount;

    private JButton btnTransferMoney;
    private JButton btnPayBill;

    private JTextField nameTxt;
    private JTextField cardNbTxt;
    private JTextField addressTxt;
    private JTextField CNPTxt;

    private JTextField idNbTxt;
    private JTextField typeTxt;
    private JTextField amountTxt;
    private JTextField dateTxt;
    private JTextField idClientTxt;


    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;

    private JTable tableClients;
    private JTable tableAccounts;

    private DefaultTableModel defaultTableModel1;
    private DefaultTableModel defaultTableModel2;


    public EmployeeView() throws HeadlessException {
        setSize(900, 900);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        initializeFields();
        add(btnAddClient);
        add(btnUpdateClient);
        add(btnViewClient);
        add(btnDeleteClient);
        add(btnAddAccount);
        add(btnUpdateAccount);
        add(btnDeleteAccount);
        add(btnViewAccount);
        add(btnTransferMoney);
        add(btnPayBill);
        add(nameTxt);
        add(cardNbTxt);
        add(addressTxt);
        add(CNPTxt);
        add(idNbTxt);
        add(typeTxt);
        add(amountTxt);
        add(dateTxt);
        add(idClientTxt);
        add(scrollPane1);
        add(scrollPane2);
        scrollPane1.setViewportView(tableClients);
        scrollPane2.setViewportView(tableAccounts);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }

    private void initializeFields() {
        btnAddClient = new JButton("Add client");
        btnUpdateClient = new JButton("Update client");
        btnViewClient = new JButton("View client");
        btnDeleteClient = new JButton("Delete client");

        btnAddAccount = new JButton("Add account");
        btnViewAccount = new JButton("View account");
        btnUpdateAccount = new JButton("Update account");
        btnDeleteAccount = new JButton("Delete account");

        btnPayBill = new JButton("Pay Bill");
        btnTransferMoney = new JButton("Transfer money");

        nameTxt = new JTextField("name");
        cardNbTxt = new JTextField("idNb");
        addressTxt = new JTextField("address");
        CNPTxt = new JTextField("CNP");

        idNbTxt = new JTextField("idNb");
        typeTxt = new JTextField("type");
        amountTxt = new JTextField("amount");
        dateTxt = new JTextField("date");
        idClientTxt = new JTextField("idClient");

        scrollPane1 = new JScrollPane();
        scrollPane2 = new JScrollPane();
        defaultTableModel1 = new DefaultTableModel();
        defaultTableModel2 = new DefaultTableModel();
        tableClients = new JTable(defaultTableModel1);
        tableAccounts = new JTable(defaultTableModel2);

        defaultTableModel1.addColumn("id");
        defaultTableModel1.addColumn("name");
        defaultTableModel1.addColumn("cardNb");
        defaultTableModel1.addColumn("address");
        defaultTableModel1.addColumn("CNP");

        defaultTableModel2.addColumn("id");
        defaultTableModel2.addColumn("idNb");
        defaultTableModel2.addColumn("type");
        defaultTableModel2.addColumn("amount");
        defaultTableModel2.addColumn("dateOfCreation");
        defaultTableModel2.addColumn("idClient");

        tableClients.setVisible(true);
        tableAccounts.setVisible(true);
    }

    public void setBtnAddClient(ActionListener createButtonListener) {
        btnAddClient.addActionListener(createButtonListener);
    }

    public void setBtnAddAccount(ActionListener createButtonListener) {
        btnAddAccount.addActionListener(createButtonListener);
    }

    public void setBtnViewClient(ActionListener createButtonListener) {
        btnViewClient.addActionListener(createButtonListener);
    }

    public void setBtnViewAccount(ActionListener createButtonListener) {
        btnViewAccount.addActionListener(createButtonListener);
    }

    public void setBtnUpdateClient(ActionListener createButtonListener) {
        btnUpdateClient.addActionListener(createButtonListener);
    }

    public void setBtnUpdateAccount(ActionListener createButtonListener) {
        btnUpdateAccount.addActionListener(createButtonListener);
    }

    public void setBtnDeleteClient(ActionListener createButtonListener) {
        btnDeleteClient.addActionListener(createButtonListener);
    }

    public void setBtnDeleteAccount(ActionListener createButtonListener) {
        btnDeleteAccount.addActionListener(createButtonListener);
    }

    public void setBtnPayBill(ActionListener createButtonListener) {
        btnPayBill.addActionListener(createButtonListener);
    }

    public void setBtnTransferMoney(ActionListener createButtonListener) {
        btnTransferMoney.addActionListener(createButtonListener);
    }

    public void setVisible() {
        this.setVisible(true);
    }

    public void setNotVisible() {
        this.setVisible(false);
    }


}
