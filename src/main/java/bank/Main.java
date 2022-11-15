package bank;

import bank.business.BankSystem;
import bank.client.CLI.CommandLineClient;
import bank.client.SwingGUI.SwingGuiClient;
import bank.core.Bank;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        BankSystem bankSystem = new BankSystem(bank);
        new SwingGuiClient(bankSystem);
        new CommandLineClient(bankSystem);
    }
}
