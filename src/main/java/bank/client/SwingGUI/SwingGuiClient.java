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

}