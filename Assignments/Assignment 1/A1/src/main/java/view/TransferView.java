package view;

import model.DTO.TransferDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class TransferView extends JFrame {

    private JTextField amount;
    private JTextField sendTo;
    private JTextField sendFrom;
    private JButton transfer;

    public  TransferView() throws HeadlessException
    {
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(amount);
        add(sendTo);
        add(sendFrom);
        add(transfer);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }


    public void initializeFields()
    {
        amount = new JTextField("amount to tranfer");
        sendTo = new JTextField("the one to receive");
        sendFrom = new JTextField("the one to tranfer");
        transfer = new JButton("Transfer");
    }

    public int getAmount()
    {
        return Integer.parseInt(amount.getText());
    }

    public String getSendTo()
    {
        return sendTo.getText();
    }

    public String getSendFrom()
    {
        return sendFrom.getText();
    }

    public void setTransferButtonListener(ActionListener transferButtonListener)
    {
        transfer.addActionListener(transferButtonListener);
    }

    public void setVisible() {
        this.setVisible(true);
    }

    public void setNotVisible()
    {
        this.setVisible(false);
    }

    public TransferDTO getTransferDTO()
    {
        TransferDTO transferDTO = new TransferDTO(sendTo.getText(), sendFrom.getText(), Integer.parseInt(amount.getText()));
        return transferDTO;

    }
}
