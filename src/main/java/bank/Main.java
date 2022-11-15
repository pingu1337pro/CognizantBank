package bank;

import bank.business.BankSystem;
import bank.client.CLI.CommandLineClient;
import bank.client.SwingGUI.SwingGuiClient;
import bank.core.Bank;
import bank.persistence.JDBCQueries;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Bank bank = new Bank();
        BankSystem bankSystem = new BankSystem(bank);
        JDBCQueries jdbcQueries = new JDBCQueries();
        bank.setCustomers(jdbcQueries.createObjects());
        new SwingGuiClient(bankSystem);
        new CommandLineClient(bankSystem);
    }
}
