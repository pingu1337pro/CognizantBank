package bank.core;

import lombok.Getter;
import lombok.Setter;
import bank.business.LogHandler;

@Setter
@Getter
public class Checking extends Account {

    private final String accountType = "checking";
    private Customer accountHolder;
    private double balance;

    private int accountNumber;

    private double overdraftLimit;
    private final double defaultOverdraftLimit = 0.0;

    public Checking(Customer accountHolder, int accountNumber, double balance) {
        super(accountHolder, accountNumber, balance);
        this.overdraftLimit = defaultOverdraftLimit;
        LogHandler.doEventLogging("Checking account created: " + this + "by " + accountHolder);
    }
}

