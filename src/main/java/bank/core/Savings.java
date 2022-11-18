package bank.core;

import lombok.Getter;
import lombok.Setter;
import bank.business.LogHandler;

@Getter
@Setter
public class Savings extends Account {
    private Customer accountHolder;
    private final String accountType = "savings";

    private int accountNumber;

    private double interestRate;
    private double interestEarned;
    private double balance;
    private final double defaultInterestRate = 0.0;

    public Savings(Customer customer, int accountNumber, double balance) {
        super(customer, accountNumber, balance);
        Double calculatedInterestRate = calculateInterestRate(balance);
        this.interestRate = calculatedInterestRate;
        LogHandler.doEventLogging("Savings account created: " + this);
    }
    public Savings(Customer customer, int accountNumber, double balance, double interestRate) {
        super(customer, accountNumber, balance);
        this.interestRate = interestRate;
        LogHandler.doEventLogging("Savings account created: " + this);
    }

    public double calculateInterestRate(double balance) {
        if (balance < 250) {
            interestRate = 0.01;
        } else if (balance < 500) {
            interestRate = 0.02;
        } else if (balance < 1000) {
            interestRate = 0.03;
        } else {
            interestRate = 0.04;
        }
        return interestRate;
    }
}
