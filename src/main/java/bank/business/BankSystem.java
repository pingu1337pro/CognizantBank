package bank.business;

import bank.core.Account;
import bank.core.Customer;
import bank.presentation.Teller;
import resources.PasswordHasher;

import java.util.List;

public class BankSystem implements Teller {

    private final BankInterface bank;

    public BankSystem(BankInterface bank) {
        if (bank == null) {
            throw new IllegalArgumentException("null parameter");
        }
        this.bank = bank;
    }

    protected BankInterface getBank() {
        return this.bank;
    }

    @Override
    public boolean createCustomerAccount(String firstName, String lastName, String birthDate, String eMail, String userName, String password) {
        if (validateInput(firstName, lastName, birthDate, eMail, userName, password)) {
            String hashedPassword = PasswordHasher.hashPassword(password);
            this.getBank().createCustomerAccount(firstName, lastName, birthDate, eMail, userName, hashedPassword);
            return true;
        }
        return false;
    }

    @Override
    public Customer getCustomerAccount(String userName, String password) {
        if (validateInput(userName, password)) {
            String hashedPassword = PasswordHasher.hashPassword(password);
            return this.getBank().getCustomerAccount(userName, hashedPassword);
        }
        return null;
    }

    @Override
    public boolean removeCustomerAccount(String name, String password) {
        if (validateInput(name, password)) {
            String hashedPassword = PasswordHasher.hashPassword(password);
            this.getBank().removeCustomerAccount(name, hashedPassword);
            return true;
        }
        return false;
    }

    @Override
    public boolean createCheckingAccount(Customer customer, double balance) {
        if (customer != null && balance >= 0) {
            this.getBank().createCheckingAccount(customer, balance);
            return true;
        }
        return false;
    }

    @Override
    public boolean createSavingsAccount(Customer customer, double balance) {
        if (customer != null && balance >= 0) {
            this.getBank().createSavingsAccount(customer, balance);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeSavingsAccount(Customer customer, String password) {
        List<Account> accounts = customer.getAccounts();
        int accountNumber = 0;
        for (Account account : accounts) {
            if (account.getAccountType().equals("saving")) {
                accountNumber = account.getAccountNumber();
            }
        }
        if (customer != null && validateInput(password)) {
            String hashedPassword = PasswordHasher.hashPassword(password);
            this.getBank().removeSavingsAccount(customer, accountNumber, hashedPassword);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeCheckingAccount(Customer customer, String password) {
        List<Account> accounts = customer.getAccounts();
        int accountNumber = 0;
        for (Account account : accounts) {
            if (account.getAccountType().equals("checking")) {
                accountNumber = account.getAccountNumber();
            }
        }
        if (customer != null && validateInput(password)) {
            String hashedPassword = PasswordHasher.hashPassword(password);
            this.getBank().removeCheckingAccount(customer, accountNumber, hashedPassword);
            return true;
        }
        return false;
    }

    @Override
    public boolean depositFunds(Account account, double amount) {
        if (account != null && amount >= 0) {
            this.getBank().depositFunds(account, amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean withdrawFunds(Account account, double amount) {
        if (account != null && amount >= 0) {
            this.getBank().withdrawFunds(account, amount);
            return true;
        }
        return false;
    }

    @Override
    public double getTotalBalance(Customer customer) {
        if (customer != null) {
            return this.getBank().getTotalBalance(customer);
        }
        throw new IllegalArgumentException("null parameter");
    }

    @Override
    public double getAccountBalance(Account account) {
        if (account != null) {
            return this.getBank().getAccountBalance(account);
        }
        throw new IllegalArgumentException("null parameter");
    }

    @Override
    public boolean transferFunds(Account from, Account to, double amount, String description) {
        if (from != null && to != null && amount >= 0 && description != null) {
            this.getBank().transferFunds(from, to, amount, description);
            return true;
        }
        return false;
    }

    @Override
    public Customer login(String userName, String password) {
        if (userName != null && password != null) {
            String hashedPassword = PasswordHasher.hashPassword(password);
            return this.getBank().login(userName, hashedPassword);
        }
        return null;
    }

    private boolean validateInput (String...strings){
        for (String string : strings) {
            return string != null && !string.isEmpty();
        }
        return false;
    }
}