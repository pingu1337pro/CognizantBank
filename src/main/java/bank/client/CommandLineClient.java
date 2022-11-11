package bank.client;

import bank.business.BankInterface;
import bank.business.Customer;
import bank.business.Teller;
import lombok.Getter;
import lombok.Setter;
import resources.Helper;
import resources.LogHandler;
import resources.PasswordHasher;

@Getter
@Setter
public class CommandLineClient extends BankAbstractClient implements Teller {

    private Customer customer;


    public CommandLineClient(BankInterface bank) {
        super(bank);
    }

    public void runBank() {
        boolean exitBank = false;
        boolean loggedIn = false;
        do {
            BankAbstractClient.initialMenu();
            int choice = Helper.readInt();
            switch (choice) {
                case 1:
                    System.out.println("Please enter your username: ");
                    String loginUserName = Helper.readLine();
                    System.out.println("Please enter your password: ");
                    String loginPassword = Helper.readLine();
                    setCustomer(this.login(loginUserName, loginPassword));
                    loggedIn = true;
                    break;
                case 2:
                    BankAbstractClient.accountCreationMenu();
                    System.out.print("Please enter your name: ");
                    String name = Helper.readLine();
                    System.out.print("Please enter your email: ");
                    String email = Helper.readLine();
                    System.out.println("Please enter your desired Username: ");
                    String newUserName = Helper.readLine();
                    System.out.print("Please enter your password: ");
                    String newPassword = Helper.readLine();
                    this.createCustomerAccount(name, email, newUserName, newPassword);
                    break;
                case 3:
                    exitBank = true;
                    LogHandler.doEventLogging("CLI exited." + this);
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
                    break;
            }
        } while (!loggedIn && !exitBank);
        if (loggedIn && !this.customer.hasCheckingAccount() && !this.customer.hasSavingsAccount()) {
            boolean exitInnerMenu = false;
            do {
                BankAbstractClient.innerMenuNoAccounts();
                int choice = Helper.readInt();
                switch (choice) {
                    case 1:
                        System.out.println("Please enter the initial deposit amount: ");
                        Double savingsAmount = Helper.readDouble();
                        this.createSavingsAccount(savingsAmount);
                        exitInnerMenu = true;
                        break;
                    case 2:
                        System.out.println("Please enter the initial deposit amount: ");
                        Double depositAmount = Helper.readDouble();
                        this.createCheckingAccount(depositAmount);
                        exitInnerMenu = true;
                        break;
                    case 3:
                        System.out.println("Please enter your password: ");
                        String password = Helper.readLine();
                        this.removeCustomerAccount(this.customer.getName(), password);
                        break;
                    case 4:
                        exitInnerMenu = true;
                        LogHandler.doEventLogging("Customer logged out: " + this.customer);
                        LogHandler.doEventLogging("CLI exit");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again!");
                        break;
                }
            } while (!exitInnerMenu);
        }
        if (loggedIn && !this.customer.hasCheckingAccount() && this.customer.hasSavingsAccount()) {
            boolean exitInnerSavingsMenu = false;
            do {
                BankAbstractClient.innerMenuWithSavingsAccount();
                int choice = Helper.readInt();
                switch (choice) {
                    case 1:
                        boolean exitSavingsAccountMenu = false;
                        do {
                            System.out.println("Hello " + this.customer.getName() + "!");
                            System.out.println("Your savings account overview: " + this.customer.getAccountByType("savings"));
                            BankAbstractClient.savingsMenu();
                            int savingsChoice = Helper.readInt();
                            switch (savingsChoice) {
                                case 1:
                                    System.out.println("Please enter the amount to deposit: ");
                                    Double savingsDepositAmount = Helper.readDouble();
                                    this.getBank().depositFunds(this.customer.getAccountByType("savings"), savingsDepositAmount);
                                    break;
                                case 2:
                                    System.out.println("Please enter the amount to withdraw: ");
                                    Double savingsWithdrawAmount = Helper.readDouble();
                                    this.getBank().withdrawFunds(this.customer.getAccountByType("savings"), savingsWithdrawAmount);
                                    break;
                                case 3:
                                    System.out.println("Please enter the account number to transfer to: ");
                                    Integer nrToTransferTo = Helper.readInt();
                                    System.out.println("Please enter the amount to transfer: ");
                                    Double savingsTransferAmount = Helper.readDouble();
                                    System.out.println("Please enter a description for the transfer: ");
                                    String savingsTransferDescription = Helper.readLine();
                                    this.getBank().transferFunds(this.customer.getAccountByType("savings"), this.customer.getAccountByNumber(nrToTransferTo), savingsTransferAmount, savingsTransferDescription);
                                    break;
                                case 4:
                                    System.out.println("Please enter your password: ");
                                    String enteredPassword = Helper.readLine();
                                    removeSavingsAccount(enteredPassword);
                                    break;
                                case 5:
                                    exitSavingsAccountMenu = true;
                                    break;
                                default:
                                    System.out.println("Invalid choice. Try again!");
                                    break;
                            }
                        } while (!exitSavingsAccountMenu);
                    case 2:
                        System.out.println("Please enter the initial deposit amount: ");
                        Double depositAmount = Helper.readDouble();
                        this.createCheckingAccount(depositAmount);
                        break;
                    case 3:
                        System.out.println("Please enter your password: ");
                        String password = Helper.readLine();
                        this.removeCustomerAccount(this.customer.getName(), password);
                        break;
                    case 4:
                        exitInnerSavingsMenu = true;
                        LogHandler.doEventLogging("Customer logged out: " + this.customer);
                        LogHandler.doEventLogging("CLI exit");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again!");
                        break;
                }
            } while (!exitInnerSavingsMenu);
        }

        if (loggedIn && this.customer.hasCheckingAccount() && !this.customer.hasSavingsAccount()) {
            boolean exitInnerCheckingMenu = false;
            do {
                BankAbstractClient.innerMenuWithCheckingAccount();
                int choice = Helper.readInt();
                switch (choice) {
                    case 1:
                        boolean exitCheckingAccountMenu = false;
                        do {
                            System.out.println("Hello " + this.customer.getName() + "!");
                            System.out.println("Your checking account overview: " + this.customer.getAccountByType("checking"));
                            BankAbstractClient.checkingMenu();
                            int checkingChoice = Helper.readInt();
                            switch (checkingChoice) {
                                case 1:
                                    System.out.println("Please enter the amount to deposit: ");
                                    Double checkingDepositAmount = Helper.readDouble();
                                    this.getBank().depositFunds(this.customer.getAccountByType("checking"), checkingDepositAmount);
                                    break;
                                case 2:
                                    System.out.println("Please enter the amount to withdraw: ");
                                    Double checkingWithdrawAmount = Helper.readDouble();
                                    this.getBank().withdrawFunds(this.customer.getAccountByType("checking"), checkingWithdrawAmount);
                                    break;
                                case 3:
                                    System.out.println("Please enter the account number to transfer to: ");
                                    Integer nrToTransferTo = Helper.readInt();
                                    System.out.println("Please enter the amount to transfer: ");
                                    Double checkingTransferAmount = Helper.readDouble();
                                    System.out.println("Please enter a description for the transfer: ");
                                    String checkingTransferDescription = Helper.readLine();
                                    this.getBank().transferFunds(this.customer.getAccountByType("checking"), this.customer.getAccountByNumber(nrToTransferTo), checkingTransferAmount, checkingTransferDescription);
                                    break;
                                case 4:
                                    System.out.println("Please enter your password: ");
                                    String enteredPassword = Helper.readLine();
                                    removeCheckingAccount(enteredPassword);
                                    break;
                                case 5:
                                    exitCheckingAccountMenu = true;
                                    break;
                                default:
                                    System.out.println("Invalid choice. Try again!");
                                    break;
                            }
                        } while (!exitCheckingAccountMenu);
                    case 2:
                        System.out.println("Please enter the initial deposit amount: ");
                        Double depositAmount = Helper.readDouble();
                        this.createSavingsAccount(depositAmount);
                        exitInnerCheckingMenu = true;
                        break;
                    case 3:
                        System.out.println("Please enter your password: ");
                        String password = Helper.readLine();
                        this.removeCustomerAccount(this.customer.getName(), password);
                        break;
                    case 4:
                        exitInnerCheckingMenu = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again!");
                        break;
                }
            } while (!exitInnerCheckingMenu);

        }

        if (loggedIn && this.customer.hasCheckingAccount() && this.customer.hasSavingsAccount()) {
            boolean exitInnerMenu = false;
            do {
                BankAbstractClient.innerMenuWithBothAccounts();
                int choice = Helper.readInt();
                switch (choice) {
                    case 1:
                        boolean exitSavingsAccountMenu = false;
                        do {
                            System.out.println("Hello " + this.customer.getName() + "!");
                            System.out.println("Your savings account overview: " + this.customer.getAccountByType("savings"));
                            BankAbstractClient.savingsMenu();
                            int savingsChoice = Helper.readInt();
                            switch (savingsChoice) {
                                case 1:
                                    System.out.println("Please enter the amount to deposit: ");
                                    Double savingsDepositAmount = Helper.readDouble();
                                    this.getBank().depositFunds(this.customer.getAccountByType("savings"), savingsDepositAmount);
                                    break;
                                case 2:
                                    System.out.println("Please enter the amount to withdraw: ");
                                    Double savingsWithdrawAmount = Helper.readDouble();
                                    this.getBank().withdrawFunds(this.customer.getAccountByType("savings"), savingsWithdrawAmount);
                                    break;
                                case 3:
                                    System.out.println("Please enter the account number to transfer to: ");
                                    Integer nrToTransferTo = Helper.readInt();
                                    System.out.println("Please enter the amount to transfer: ");
                                    Double savingsTransferAmount = Helper.readDouble();
                                    System.out.println("Please enter a description for the transfer: ");
                                    String savingsTransferDescription = Helper.readLine();
                                    this.getBank().transferFunds(this.customer.getAccountByType("savings"), this.customer.getAccountByNumber(nrToTransferTo), savingsTransferAmount, savingsTransferDescription);
                                    break;
                                case 4:
                                    System.out.println("Please enter your password: ");
                                    String enteredPassword = Helper.readLine();
                                    removeSavingsAccount(enteredPassword);
                                    break;
                                case 5:
                                    exitSavingsAccountMenu = true;
                                    break;
                                default:
                                    System.out.println("Invalid choice. Try again!");
                                    break;
                            }
                        } while (!exitSavingsAccountMenu);
                    case 2:
                        boolean exitCheckingAccountMenu = false;
                        do {
                            System.out.println("Hello " + this.customer.getName() + "!");
                            System.out.println("Your checking account overview: " + this.customer.getAccountByType("checking"));
                            BankAbstractClient.checkingMenu();
                            int checkingChoice = Helper.readInt();
                            switch (checkingChoice) {
                                case 1:
                                    System.out.println("Please enter the amount to deposit: ");
                                    Double checkingDepositAmount = Helper.readDouble();
                                    this.getBank().depositFunds(this.customer.getAccountByType("checking"), checkingDepositAmount);
                                    break;
                                case 2:
                                    System.out.println("Please enter the amount to withdraw: ");
                                    Double checkingWithdrawAmount = Helper.readDouble();
                                    this.getBank().withdrawFunds(this.customer.getAccountByType("checking"), checkingWithdrawAmount);
                                    break;
                                case 3:
                                    System.out.println("Please enter the account number to transfer to: ");
                                    Integer nrToTransferTo = Helper.readInt();
                                    System.out.println("Please enter the amount to transfer: ");
                                    Double checkingTransferAmount = Helper.readDouble();
                                    System.out.println("Please enter a description for the transfer: ");
                                    String checkingTransferDescription = Helper.readLine();
                                    this.getBank().transferFunds(this.customer.getAccountByType("checking"), this.customer.getAccountByNumber(nrToTransferTo), checkingTransferAmount, checkingTransferDescription);
                                    break;
                                case 4:
                                    System.out.println("Please enter your password: ");
                                    String enteredPassword = Helper.readLine();
                                    this.removeCheckingAccount(enteredPassword);
                                    break;
                                case 5:
                                    exitCheckingAccountMenu = true;
                                    break;
                                default:
                                    System.out.println("Invalid choice. Try again!");
                                    break;
                            }
                        } while (!exitCheckingAccountMenu);
                    case 3:
                        System.out.println("Please enter your password: ");
                        String password = Helper.readLine();
                        this.removeCustomerAccount(this.customer.getName(), password);
                        break;
                    case 4:
                        exitInnerMenu = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again!");
                        break;
                }
            } while (!exitInnerMenu);
        }
    }

    @Override
    public void reportAccountBalance(Double balance) {
        System.out.println("Account balance: " + balance);
    }

    public Customer login(String userName, String password) {
        String hashedPassword = PasswordHasher.hashPassword(password);
        return this.getBank().login(userName, hashedPassword);
    }

    @Override
    public void reportTotalBalance(Double balance) {
        System.out.println("Total balance: " + balance);
    }

    @Override
    public void reportTransferStatus(String status) {
        System.out.println("Transfer status: " + status);
    }

    @Override
    public void reportLoanStatus(String status) {
        System.out.println("Loan status: " + status);
    }

    @Override
    public void createCustomerAccount(String name, String eMail, String userName, String storedPassword) {
        String hashedPassword = PasswordHasher.hashPassword(storedPassword);
        this.getBank().createCustomerAccount(name, eMail, userName, hashedPassword);
    }

    @Override
    public void removeCustomerAccount(String name, String password) {
        this.getBank().removeCustomerAccount(name, password);
    }

    @Override
    public Customer reportCustomerAccount(String userName, String password) {
        return this.getBank().getCustomerAccount(userName, password);
    }

    public void createSavingsAccount(Double amount) {
        this.getBank().createSavingsAccount(this.customer, amount);
    }

    public void createCheckingAccount(Double amount) {
        this.getBank().createCheckingAccount(this.customer, amount);
    }

    public void removeSavingsAccount(String password) {
        this.getBank().removeSavingsAccount(this.customer, password);
    }

    public void removeCheckingAccount(String password) {
        this.getBank().removeCheckingAccount(this.customer, password);
    }

    @Override
    protected void initialize() {
        System.out.println("Welcome to Kessler Financial Ltd.");
        Helper.sleep(1000);
        LogHandler.doOutputLogging("CLI initialized");
    }

    @Override
    public String toString() {
        return "CLI customer: " + customer;
    }
}
