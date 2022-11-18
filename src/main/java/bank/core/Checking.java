package bank.core;

import lombok.Getter;
import lombok.Setter;
import bank.business.LogHandler;

@Setter
@Getter
public class Checking extends Account {

    private Customer accountHolder;
    private final String accountType = "checking";
    private double balance;

    private int accountNumber;

    private double overdraftLimit;
    private final double defaultOverdraftLimit = 0.0;

    public Checking(Customer customer, int accountNumber, double balance) {
        super(customer, accountNumber, balance);
        this.overdraftLimit = defaultOverdraftLimit;
        LogHandler.doEventLogging("Checking account created: " + this);
    }
    public Checking(Customer customer, int accountNumber, double balance, double overdraftLimit) {
        super(customer, accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
        LogHandler.doEventLogging("Checking account created: " + this);
    }
}

