package controller;

import service.account.AccountService;
import service.client.ClientService;
import view.EmployeeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeController {

    private final EmployeeView employeeView;
    private final ClientService clientService;
    private final AccountService accountService;
    private final TransferController transferController;
    private final BillController billController;

    public EmployeeController(EmployeeView employeeView, ClientService clientService, TransferController transferController, BillController billController,AccountService accountService) {
        this.employeeView = employeeView;
        this.accountService = accountService;
        this.clientService = clientService;
        this.transferController =transferController;
        this.billController = billController;
        employeeView.setBtnAddClient(new AddClientButtonListener());
        employeeView.setBtnAddAccount(new AddAccountButtonListener());
        employeeView.setBtnUpdateClient(new UpdateClientButtonListener());
        employeeView.setBtnUpdateAccount(new UpdateAccountButtonListener());
        employeeView.setBtnDeleteClient(new DeleteClientButtonListener());
        employeeView.setBtnDeleteAccount(new DeleteAccountButtonListener());
        employeeView.setBtnViewClient(new ViewClientButtonListener());
        employeeView.setBtnViewAccount(new ViewAccountButtonListener());
        employeeView.setBtnPayBill(new PayBillButtonListener());
        employeeView.setBtnTransferMoney(new TransferMoneyButtonListener());
    }

    private class AddClientButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class AddAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class UpdateClientButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class UpdateAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class DeleteClientButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class DeleteAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class ViewClientButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class ViewAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class PayBillButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            billController.setVisible();
        }
    }

    private class TransferMoneyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            transferController.setVisible();
        }
    }

    public void visible() {
        employeeView.setVisible();
    }
}

