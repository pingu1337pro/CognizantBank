package bank;

import bank.business.BankSystem;
import bank.client.CLI.CommandLineClient;
import bank.client.SwingGUI.SwingGuiClient;
import bank.core.Bank;
import bank.core.Customer;
import bank.persistence.CustomerDAO;
import resources.PasswordHasher;

import java.sql.SQLException;
import java.util.List;

public class Main {
    //TODO: SQL Exception handling
    public static void main(String[] args) throws SQLException {

        Bank bank = new Bank();
        List<Customer> customerretrieve = CustomerDAO.getInstance().getAllCustomers();
        for (Customer c : customerretrieve) {
            bank.addCustomer(c);
        }
        BankSystem bankSystem = new BankSystem(bank);
        new SwingGuiClient(bankSystem);
        new CommandLineClient(bankSystem);
    }
}
