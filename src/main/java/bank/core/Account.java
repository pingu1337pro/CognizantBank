package bank.core;

import lombok.Getter;
import lombok.Setter;
import bank.business.LogHandler;

import java.util.ArrayList;
@Getter
@Setter
public abstract class Account {

    private Customer accountHolder;

    private String accountType;
    private int accountNumber;

    double balance;
    double overdraftLimit;
    double interestRate;
    private ArrayList<String> transactions;

    public Account(Customer customer, int accountNumber, double balance) {
        this.accountHolder = customer;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = new ArrayList<>();
        LogHandler.doEventLogging("Account created: " + this);
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposit: " + amount);
        LogHandler.doTransactionLogging("Deposit: " + amount + " to account: " + this);
    }

    public void withdraw(double amount) {
        balance -= amount;
        transactions.add("Withdraw: " + amount);
        LogHandler.doTransactionLogging("Withdraw: " + amount + " from account: " + this);
    }


    public String toString() {
        return "Account: " + accountType + "Nr." + accountNumber + "Balance: " + balance + "Overdraft Limit: " + overdraftLimit + "Interest Rate: " + interestRate;
    }
}
