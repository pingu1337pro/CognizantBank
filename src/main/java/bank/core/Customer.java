package bank.core;

import lombok.Getter;
import lombok.Setter;
import bank.business.LogHandler;
import resources.PasswordHasher;

import java.util.ArrayList;
import java.util.List;
//Generate documentation for this class


@Setter
@Getter
public class Customer {
    //TODO: seperate lists for savings and checkings account
    String firstName;
    String lastName;
    String birthDate;
    String userName;
    String eMail;
    String storedPassword;
    List<Account> accounts = new ArrayList<>();


    public Customer(String firstName, String lastName, String birthDate, String eMail, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.eMail = eMail;
        this.userName = userName;
        LogHandler.doEventLogging("Customer created: " + this);
    }

    public Customer(String firstName, String lastName, String birthDate, String eMail, String userName, String storedPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.eMail = eMail;
        this.userName = userName;
        this.storedPassword = storedPassword;
        LogHandler.doEventLogging("Customers instantiated: " + this);
    }

    public void addAccount(Account account) {
        accounts.add(account);
        LogHandler.doEventLogging("Account: " + account + "; added to customer: " + this);
    }


    public void removeAccount(Account account, String password) {
        if (PasswordHasher.hashPassword(password).equals(storedPassword)) {
            accounts.remove(account);
            LogHandler.doEventLogging("Account: " + account + "; removed from customer: " + this);
        } else {
            LogHandler.doErrorLogging("removeAccount called with wrong password");
        }
    }

    public boolean hasCheckingAccount() {
        for (Account account : accounts) {
            if (account instanceof Checking) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSavingsAccount() {
        for (Account account : accounts) {
            if (account instanceof Savings) {
                return true;
            }
        }
        return false;
    }

    public Account getAccountByNumber(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public Account getAccountByType(String accountType) {
        for (Account account : accounts) {
            if (account.getAccountType().equals(accountType)) {
                return account;
            }
        }
        return null;
    }

    public String toString() {
        return "Customer: " + firstName + " " + lastName + "" + birthDate + "" + eMail + " " + userName + " " + storedPassword;
    }
}
