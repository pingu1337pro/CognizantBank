package bank.business;

import lombok.Getter;
import lombok.Setter;
import resources.LogHandler;

@Getter
@Setter
public class Savings extends Account {
    private final String accountType = "savings";
    private Customer accountHolder;

    private Integer accountNumber;

    private Double interestRate;
    private Double interestEarned;
    private Double balance;
    private final Double defaultInterestRate = 0.0;

    public Savings(Customer accountHolder, Integer accountNumber, Double balance) {
        super(accountHolder, accountNumber, balance);
        Double calculatedInterestRate = calculateInterestRate(balance);
        this.interestRate = calculatedInterestRate;
        LogHandler.doEventLogging("Savings account created: " + this + "by " + accountHolder);
    }

    public Double calculateInterestRate(Double balance) {
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
