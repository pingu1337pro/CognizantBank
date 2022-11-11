package bank.business;

public interface Teller {
    void reportAccountBalance(Double balance);
    void reportTotalBalance(Double balance);

    void reportTransferStatus(String status);

    void reportLoanStatus(String status);
    void createCustomerAccount(String name, String eMail, String userName, String storedPassword);
    void removeCustomerAccount(String name, String password);
    void removeSavingsAccount(String password);
    void removeCheckingAccount(String password);
    Customer reportCustomerAccount(String userName, String password);

}
