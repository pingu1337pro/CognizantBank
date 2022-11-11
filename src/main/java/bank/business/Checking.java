package bank.business;

import lombok.Getter;
import lombok.Setter;
import resources.LogHandler;

@Setter
@Getter
public class Checking extends Account {

    private final String accountType = "checking";
    private Customer accountHolder;
    private Double balance;

    private Integer accountNumber;

    private Double overdraftLimit;
    private final Double defaultOverdraftLimit = 0.0;

    public Checking(Customer accountHolder, Integer accountNumber, Double balance) {
        super(accountHolder, accountNumber, balance);
        this.overdraftLimit = defaultOverdraftLimit;
        LogHandler.doEventLogging("Checking account created: " + this + "by " + accountHolder);
    }
}

