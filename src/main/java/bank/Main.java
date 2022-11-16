package bank;

import bank.business.BankSystem;
import bank.client.CLI.CommandLineClient;
import bank.client.SwingGUI.SwingGuiClient;
import bank.core.Bank;
import bank.core.Customer;
import bank.persistence.JDBCQueries;
import resources.PasswordHasher;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println(PasswordHasher.hashPassword("abcd"));
        Bank bank = new Bank();
        JDBCQueries jdbcQueries = new JDBCQueries();
        List<Customer> customerretrieve = jdbcQueries.createObjects();
        for (Customer c : customerretrieve) {
            bank.addCustomer(c);
        }
        BankSystem bankSystem = new BankSystem(bank);
        new SwingGuiClient(bankSystem);
        new CommandLineClient(bankSystem);
    }
}
