package controller;

import model.Account;
import service.account.AccountService;
import service.client.ClientService;
import view.TransferView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferController {

    private final TransferView transferView;
    private final AccountService accountService;
    private final ClientService clientService;

    public TransferController(TransferView transferView, AccountService accountService, ClientService clientService)
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

            int idToSend = clientService.findByName(sendTo).getId();
            int idFrom = clientService.findByName(sendFrom).getId();

            Account accountToSend = accountService.findByClientId(idToSend);
            Account accountFrom = accountService.findByClientId(idFrom);

            int amountInitToSent = accountToSend.getAmount();
            int amountInitFrom = accountFrom.getAmount();

            if (amount <= amountInitFrom)
            {
                accountToSend.setAmount(amountInitToSent + amount);
                accountService.update(accountToSend);

                accountFrom.setAmount(amountInitFrom - amount);
                accountService.update(accountFrom);
                JOptionPane.showMessageDialog(transferView.getContentPane(),"TransactionSuccess");
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
