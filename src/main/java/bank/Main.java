package bank;

import bank.business.Bank;
import bank.client.CommandLineClient;
import resources.PasswordHasher;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        CommandLineClient client = new CommandLineClient(bank);
        client.runBank();
    }
}
