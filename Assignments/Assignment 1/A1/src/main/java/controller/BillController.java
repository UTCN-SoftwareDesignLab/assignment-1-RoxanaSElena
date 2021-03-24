package controller;

import model.Account;
import model.DTO.TransferDTO;
import model.validation.Notification;
import service.account.AccountService;
import service.client.ClientService;
import view.TransferView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BillController {
    private final TransferView transferView;
    private final AccountService accountService;
    private final ClientService clientService;

    public BillController(TransferView transferView, AccountService accountService, ClientService clientService)
    {
        this.transferView = transferView;
        this.accountService = accountService;
        this.clientService = clientService;
        transferView.setTransferButtonListener(new TransferButtonListener());
    }

    public class TransferButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {


            TransferDTO transferDTO = transferView.getTransferDTO();
            Notification<Boolean> notification = accountService.payBill(transferDTO);
            if(notification.hasErrors())
            {
                JOptionPane.showMessageDialog(transferView.getContentPane(),"Transaction cannot be done");

            }
            else
            {
                JOptionPane.showMessageDialog(transferView.getContentPane(),"Transaction successfully done!");
            }
        }

    }


    public void setVisible()
    {
        transferView.setVisible();
    }
}

