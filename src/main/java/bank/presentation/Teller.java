package bank.presentation;

import bank.core.Account;
import bank.core.Customer;

public interface Teller {
   boolean createCustomerAccount(String firstName, String lastName, String birthDate, String eMail, String userName, String password);
   Customer getCustomerAccount(String userName, String password);
   boolean removeCustomerAccount(String name, String password);
   boolean createCheckingAccount(Customer customer, double balance);
   boolean createSavingsAccount(Customer customer, double balance);
    boolean removeSavingsAccount(Customer customer, String password);
    boolean removeCheckingAccount(Customer customer, String password);
    boolean depositFunds(Account account, double amount);
    boolean withdrawFunds(Account account, double amount);
    double getTotalBalance(Customer customer);
    double getAccountBalance(Account account);
    boolean transferFunds(Account from, Account to, double amount, String description);
    Customer login(String userName, String password);
}
