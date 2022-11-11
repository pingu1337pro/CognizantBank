package bank.business;

public interface BankInterface {

    void addTeller(Teller teller);
    void addCustomer(Customer customer);
    void createCustomerAccount(String name, String eMail, String userName, String password);
    Customer getCustomerAccount(String userName, String password);
    void removeCustomerAccount(String name, String password);
    void createCheckingAccount(Customer customer, Double balance);
    void createSavingsAccount(Customer customer, Double balance);
    void removeSavingsAccount(Customer customer, String password);
    void removeCheckingAccount(Customer customer, String password);
    void depositFunds(Account account, Double amount);
    void withdrawFunds(Account account, Double amount);
    Double getTotalBalance(Customer customer);
    Double getAccountBalance(Account account);
    void transferFunds(Account from, Account to, Double amount, String description);
    Customer login(String userName, String password);
}
