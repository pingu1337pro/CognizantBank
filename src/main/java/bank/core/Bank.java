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
    //TODO: Remove sout calls

    List<Customer> customers = new ArrayList<>();
    List<Account> accounts = new ArrayList<>();
    private List<Teller> tellers;


    public void addTeller(Teller teller) {
        tellers.add(teller);
        LogHandler.doEventLogging("Teller added: " + teller);
    }

    public void addCustomer(Customer customer) {
        if (customer != null) {
            customers.add(customer);
            LogHandler.doEventLogging("Customer added: " + customer);
        } else {
            System.out.println("ERROR: Customer can't be null");
            LogHandler.doErrorLogging("addCustomer called with null customer");
        }
    }

    public void createCustomerAccount(String name, String eMail,String userName, String password) {
        Customer customer = new Customer(name, eMail, userName);
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

    public void removeCustomerAccount(String name, String password) {
        String hashedPassword = PasswordHasher.hashPassword(password);
        for (Customer c : customers) {
            if (c.getName().equals(name) && c.getStoredPassword().equals(hashedPassword)) {
                customers.remove(c);
                LogHandler.doEventLogging("Customer removed: " + c);
                System.out.println("SYSTEM: Customer removed: " + c);
            }
        }
        System.out.println("ERROR: Name or password is incorrect");
        LogHandler.doErrorLogging("removeCustomerAccount called with incorrect name or password");
    }

    public void createCheckingAccount(Customer customer, double balance) {
        Integer accountNumber = Math.abs((int) (Math.random() * 1_000_000));
        Account account = new Checking(customer, accountNumber, balance);
        customer.addAccount(account);
        accounts.add(account);
        System.out.println("SYSTEM: Checking account created: " + account);
        LogHandler.doEventLogging("Checking account created: " + account);
    }

    public void createSavingsAccount(Customer customer, double balance) {
        Integer accountNumber = Math.abs((int) (Math.random() * 1_000_000));
        Account account = new Savings(customer, accountNumber, balance);
        customer.addAccount(account);
        accounts.add(account);
        System.out.println("SYSTEM: Savings account created: " + account);
        LogHandler.doEventLogging("Savings account created: " + account);
    }

    public void removeSavingsAccount(Customer customer, String password) {
        if(customer != null && password != null) {
            for (Account account : accounts) {
                if (account instanceof Savings && account.getCustomer().equals(customer)) {
                    accounts.remove(account);
                    customer.removeAccount(account, password);
                    System.out.println("SYSTEM: Savings account removed: " + account);
                    LogHandler.doEventLogging("Savings account removed: " + account);
                }
            }
        } else {
            LogHandler.doErrorLogging("removeSavingsAccount called with null parameter");
        }
    }

    public void removeCheckingAccount(Customer customer, String password) {
        if(customer != null && password != null) {
            for (Account account : accounts) {
                if (account instanceof Checking && account.getCustomer().equals(customer)) {
                    accounts.remove(account);
                    customer.removeAccount(account, password);
                    System.out.println("SYSTEM: Checking account removed: " + account);
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
            System.out.println("SYSTEM: Deposit successful");
            LogHandler.doEventLogging("Deposited " + amount + " to account: " + account);
        } else {
            System.out.println("ERROR: Account or amount can't be null");
            LogHandler.doErrorLogging("depositFunds called with null parameter");
        }
    }

    public void withdrawFunds(Account account, double amount) {
        if (account != null && amount >= 0) {
            account.withdraw(amount);
            System.out.println("SYSTEM: Withdrawal successful");
            LogHandler.doEventLogging("Withdrew " + amount + " from account: " + account);
        } else {
            System.out.println("ERROR: Account or amount can't be null");
            LogHandler.doErrorLogging("withdrawFunds called with null parameter");
        }
    }

    public double getTotalBalance(Customer customer) {
        double totalBalance = 0.0;
        for (Account account : accounts) {
            if (account.getCustomer().equals(customer)) {
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
    public Customer login(String userName, String password) {
        if(userName == null || Objects.equals(password, "")) {
            LogHandler.doErrorLogging("Login failed with invalid or empty fields: " + userName);
            System.out.println("ERROR: Invalid account number or password. Did fill in all fields?");
        }else {
            for (Customer customer : customers) {
                if (customer.getUserName().equals(userName) && PasswordHasher.hashPassword(password).equals(customer.getStoredPassword())) {
                    LogHandler.doEventLogging("Login successful: " + customer);
                    return customer;
                }
            }
        }
        LogHandler.doEventLogging("Login failed: " + userName);
        return null;
    }
}
