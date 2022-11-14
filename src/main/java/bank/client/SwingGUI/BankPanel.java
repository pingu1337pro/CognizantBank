package bank.client.SwingGUI;

import bank.presentation.Teller;

import javax.swing.*;
import java.awt.*;

public class BankPanel extends JPanel {

    private final Teller teller;
    private final JPanel INFORMATION_PANEL = new JPanel();
    private final JPanel MENU_PANEL = new JPanel();

    public BankPanel(Teller teller) throws IllegalArgumentException {
        this.teller = teller;
        this.buildLayout();
    }

    private void buildLayout() {
        this.setLayout(new BorderLayout());

        this.add(this.getInformationPanel(), BorderLayout.NORTH);
        this.add(this.getMenuPanel(), BorderLayout.CENTER);
    }

    private JPanel getInformationPanel() {
        //TODO: infoPanel displays current information i.e. CustomerAccount logged into, Account selected, etc.

        JPanel informationPanel = new JPanel();
        informationPanel.add(new JLabel("Informations-Panel"), CENTER_ALIGNMENT);


        return informationPanel;
    }

    private JPanel getMenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = getWelcomeMessage();
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        menuPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonsPanel = getButtonsPanel(5);
        menuPanel.add(buttonsPanel, BorderLayout.CENTER);

        return menuPanel;
    }

    private JLabel getWelcomeMessage() {
        JLabel welcomeLabel = new JLabel();

        //TODO: delete "false" and replace with actual check
        if(false/*initialMenu*/) {
            welcomeLabel.setText("Hello, please sign in or create a new Account.");
        } else if(false/*accountCreationMenu*/) {
            welcomeLabel.setText("Let's get you started!");
        } else {
            welcomeLabel.setText("What can we do for you today?");
        }

        return welcomeLabel;
    }

    private JPanel getButtonsPanel(int amountButtons) {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());

        int amountRows = (amountButtons + 1) / 2;
        JPanel buttonsTable = new JPanel();
        buttonsTable.setLayout(new GridLayout(amountRows, 2, 20, 20));

        for (int i=0; i<amountButtons; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setSize(100, 50);
            buttonsTable.add(button);
        }

        buttonsPanel.add(buttonsTable);
        buttonsPanel.add(new JButton("Exit"), BorderLayout.SOUTH);

        return buttonsPanel;
    }

}