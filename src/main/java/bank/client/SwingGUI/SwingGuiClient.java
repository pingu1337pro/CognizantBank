package bank.client.SwingGUI;

import bank.presentation.Teller;
import bank.presentation.ClientBlueprint;

import javax.swing.*;
import java.awt.*;

public class SwingGuiClient extends ClientBlueprint {

    private BankPanel bankPanel;

    public SwingGuiClient(Teller teller) {
        super(teller);
        this.bankPanel = new BankPanel(teller);
        buildFrame();
    }

    private void buildFrame() {
        JFrame frame = new JFrame("Kessler Financial Ltd.");
        frame.add(bankPanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1_000,750);
        frame.setVisible(true);
    }
    @Override
    public void reportCreateCustomerAccount(boolean success) {}

    @Override
    public void reportRemoveCustomerAccount(boolean status) {

    }

    @Override
    public void reportCreateCheckingAccount(boolean status) {

    }

    @Override
    public void reportCreateSavingsAccount(boolean status) {

    }

    @Override
    public void reportRemoveSavingsAccount(boolean status) {

    }

    @Override
    public void reportRemoveCheckingAccount(boolean status) {

    }

    @Override
    public void reportDeposit(boolean status) {

    }

    @Override
    public void reportWithdraw(boolean status) {

    }

    @Override
    public void reportTransfer(boolean status) {

    }

}