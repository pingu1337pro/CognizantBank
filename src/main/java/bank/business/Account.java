package bank.business;

import lombok.Getter;
import lombok.Setter;
import resources.LogHandler;

import java.util.ArrayList;
@Getter
@Setter
public abstract class Account {

    private String accountType;
    private Customer customer;
    private Integer accountNumber;

    Double balance;
    Double overdraftLimit;
    Double interestRate;
    private ArrayList<String> transactions;

    public Account(Customer customer, Integer accountNumber, Double balance) {
        this.customer = customer;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = new ArrayList<>();
        LogHandler.doEventLogging("Account created: " + this);
    }

    public void deposit(Double amount) {
        balance += amount;
        transactions.add("Deposit: " + amount);
        LogHandler.doTransactionLogging("Deposit: " + amount + " to account: " + this);
    }

    public void withdraw(Double amount) {
        balance -= amount;
        transactions.add("Withdraw: " + amount);
        LogHandler.doTransactionLogging("Withdraw: " + amount + " from account: " + this);
    }


    public String toString() {
        return "Account: " + accountType + "Nr." + accountNumber + "Balance: " + balance + "Overdraft Limit: " + overdraftLimit + "Interest Rate: " + interestRate + "Account Holder: " + customer;
    }
}
