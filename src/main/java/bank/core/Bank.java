package bank.core;

import bank.business.BankInterface;
import bank.presentation.Teller;
import lombok.Getter;
import lombok.Setter;
import bank.business.LogHandler;
import resources.PasswordHasher;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Bank implements BankInterface {

    //TODO: Database/Table for multiple banks?

    List<Customer> customers = new ArrayList<>();
    List<Account> accounts = new ArrayList<>();


    public void addCustomer(Customer customer) {
        if (customer != null) {
            customers.add(customer);
            LogHandler.doEventLogging("Customer added: " + customer);
        } else {
            LogHandler.doErrorLogging("addCustomer called with null customer");
        }
    }

    public void createCustomerAccount(String firstName, String lastName, String birthDate, String eMail,String userName, String password) {
        Customer customer = new Customer(firstName, lastName, birthDate, eMail, userName, password);
        customer.setStoredPassword(PasswordHasher.hashPassword(password));
        addCustomer(customer);
    }

    public Customer getCustomerAccount(String userName, String password) {
        if (userName != null && password != null) {
            for (Customer customer : customers) {
                if (customer.getUserName().equals(userName) && PasswordHasher.hashPassword(password).equals(customer.getStoredPassword())) {
                    return customer;
                }
            }
        }
        LogHandler.doErrorLogging("getCustomerAccount called with null parameter");
        return null;
    }

    public void removeCustomerAccount(String userName, String password) {
        String hashedPassword = PasswordHasher.hashPassword(password);
        for (Customer c : customers) {
            if (c.getUserName().equals(userName) && c.getStoredPassword().equals(hashedPassword)) {
                customers.remove(c);
                LogHandler.doEventLogging("Customer removed: " + c);
            }
        }
        LogHandler.doErrorLogging("removeCustomerAccount called with incorrect name or password");
    }

    public void createCheckingAccount(Customer customer, double balance) {
        int accountNumber = Math.abs((int) (Math.random() * 1_000_000));
        Account account = new Checking(customer, accountNumber, balance);
        customer.addAccount(account);
        accounts.add(account);
        LogHandler.doEventLogging("Checking account created: " + account);
    }

    public void createSavingsAccount(Customer customer, double balance) {
        int accountNumber = Math.abs((int) (Math.random() * 1_000_000));
        Account account = new Savings(customer, accountNumber, balance);
        customer.addAccount(account);
        accounts.add(account);
        LogHandler.doEventLogging("Savings account created: " + account);
    }

    public void removeSavingsAccount(Customer customer, int accountNumber, String password) {
        if(customer != null && password != null) {
            for (Account account : accounts) {
                if (account instanceof Savings && account.getAccountNumber() == accountNumber) {
                    accounts.remove(account);
                    customer.removeAccount(account, password);
                    LogHandler.doEventLogging("Savings account removed: " + account);
                }
            }
        } else {
            LogHandler.doErrorLogging("removeSavingsAccount called with null parameter");
        }
    }

    public void removeCheckingAccount(Customer customer,int accountNumber , String password) {
        if(customer != null && password != null) {
            for (Account account : accounts) {
                if (account instanceof Checking && account.getAccountNumber() == accountNumber) {
                    accounts.remove(account);
                    customer.removeAccount(account, password);
                    LogHandler.doEventLogging("Checking account removed: " + account);
                }
            }
        } else {
            LogHandler.doErrorLogging("removeCheckingAccount called with null parameter");
        }
    }

    public void depositFunds(Account account, double amount) {
        if (account != null && amount >= 0) {
            account.deposit(amount);
            LogHandler.doEventLogging("Deposited " + amount + " to account: " + account);
        } else {
            LogHandler.doErrorLogging("depositFunds called with null parameter");
        }
    }

    public void withdrawFunds(Account account, double amount) {
        if (account != null && amount >= 0) {
            account.withdraw(amount);
            LogHandler.doEventLogging("Withdrew " + amount + " from account: " + account);
        } else {
            LogHandler.doErrorLogging("withdrawFunds called with null parameter");
        }
    }

    public double getTotalBalance(Customer customer) {
        double totalBalance = 0.0;
        for (Account account : accounts) {
            if (account.getAccountHolder().equals(customer)) {
                totalBalance += account.getBalance();
            }
        }
        return totalBalance;
    }

    public double getAccountBalance(Account account) {
        return account.getBalance();
    }

    public void transferFunds(Account from, Account to, double amount, String description) {
        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
        LogHandler.doTransactionLogging("Transfer: " + amount + " from " + from + " to " + to + " description: " + description);
    }

    @Override
    public Customer login(String userName, String hashedPassword) {
        if(userName == null || Objects.equals(hashedPassword, "")) {
            LogHandler.doErrorLogging("Login failed with invalid or empty fields: " + userName);
        }else {
            for (Customer customer : customers) {
                if (customer.getUserName().equals(userName) && hashedPassword.equals(customer.getStoredPassword())) {
                    LogHandler.doEventLogging("Login successful: " + customer);
                    return customer;
                }
            }
        }
        LogHandler.doEventLogging("Login failed: " + userName);
        return null;
    }
}
