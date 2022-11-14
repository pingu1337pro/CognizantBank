package bank.business;

import bank.core.Account;
import bank.core.Customer;

public interface BankInterface {

    void addCustomer(Customer customer);
    void createCustomerAccount(String name, String eMail, String userName, String password);
    Customer getCustomerAccount(String userName, String password);
    void removeCustomerAccount(String name, String password);
    void createCheckingAccount(Customer customer, double balance);
    void createSavingsAccount(Customer customer, double balance);
    void removeSavingsAccount(Customer customer, String password);
    void removeCheckingAccount(Customer customer, String password);
    void depositFunds(Account account, double amount);
    void withdrawFunds(Account account, double amount);
    double getTotalBalance(Customer customer);
    double getAccountBalance(Account account);
    void transferFunds(Account from, Account to, double amount, String description);
    Customer login(String userName, String password);
}
