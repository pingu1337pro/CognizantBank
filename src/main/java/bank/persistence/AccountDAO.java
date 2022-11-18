package bank.persistence;

import bank.core.Account;
import bank.core.Checking;
import bank.core.Customer;
import bank.core.Savings;

import java.sql.*;
import java.util.List;


public class AccountDAO {

    private static AccountDAO instance = null;

    private AccountDAO() {
    }

    public static AccountDAO getInstance() {
        if (instance == null) {
            instance = new AccountDAO();
        }
        return instance;
    }

    public Account getAccount(Customer customer, int accountNumber) throws SQLException{
        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/bank", "bank", "12345")) {
            PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM ACCOUNTS WHERE ACCOUNTNUMBER = " + accountNumber);
            ResultSet rs = selectStatement.executeQuery();
            String accountType = rs.getString("ACCOUNTTYPE");
            double balance = rs.getDouble("BALANCE");
            double overdraftLimit = rs.getDouble("OVERDRAFTLIMIT");
            double interestRate = rs.getDouble("INTERESTRATE");
            if (accountType.equals("checking")) {
                return new Checking(customer, accountNumber, balance, overdraftLimit);
            } else if (accountType.equals("savings")) {
                return new Savings(customer, accountNumber, balance, interestRate);
            } else {
                return null;
            }
        }
    }

    public boolean writeAccount(Account account) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/bank", "bank", "12345")) {
            PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO ACCOUNTS (ACCOUNTNUMBER, ACCOUNTTYPE, BALANCE, OVERDRAFTLIMIT, INTERESTRATE) VALUES (?, ?, ?, ?, ?)");
            insertStatement.setInt(1, account.getAccountNumber());
            insertStatement.setString(2, account.getAccountType());
            insertStatement.setDouble(3, account.getBalance());
            insertStatement.setDouble(4, account.getOverdraftLimit());
            insertStatement.setDouble(5, account.getInterestRate());
            insertStatement.executeUpdate();
            return true;
        }
    }

}
