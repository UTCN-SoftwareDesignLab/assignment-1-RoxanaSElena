package controller;

import model.Account;
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

            String sendTo = transferView.getSendTo();
            String sendFrom = transferView.getSendFrom();
            int amount = transferView.getAmount();

            int idFrom = clientService.findByName(sendFrom).getId();

            Account accountFrom = accountService.findByClientId(idFrom);

            int amountInitFrom = accountFrom.getAmount();

            if (amount <= amountInitFrom)
            {

                accountFrom.setAmount(amountInitFrom - amount);
                accountService.update(accountFrom);
                JOptionPane.showMessageDialog(transferView.getContentPane(),"Your" + sendTo +"bill was processed");
            }
            else
            {
                JOptionPane.showMessageDialog(transferView.getContentPane(),"Not enough account balance for the transfer");
            }

        }
    }


    public void setVisible()
    {
        transferView.setVisible();
    }
}

