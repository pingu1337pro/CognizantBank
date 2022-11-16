package bank.client.CLI;

import bank.business.BankLogger;
import bank.presentation.Teller;
import bank.client.MenuHelper;
import bank.core.Customer;
import bank.presentation.ClientBlueprint;
import lombok.Getter;
import lombok.Setter;
import resources.Helper;
import bank.business.LogHandler;
import resources.PasswordHasher;

@Getter
@Setter
public class CommandLineClient extends ClientBlueprint {



    public CommandLineClient(Teller teller) {
        super(teller);
        initialize();
        runBank();
    }

    public void runBank() {
        Customer thisCustomer = null;
        boolean exitBank = false;
        boolean loggedIn = false;
        do {
            MenuHelper.initialMenu();
            int choice = Helper.readInt();
            switch (choice) {
                case 1:
                    System.out.println("Please enter your username: ");
                    String loginUserName = Helper.readLine();
                    System.out.println("Please enter your password: ");
                    String loginPassword = Helper.readLine();
                    Customer tempCustomer = this.getTeller().login(loginUserName, loginPassword);
                    if(tempCustomer != null){
                        thisCustomer = tempCustomer;
                        loggedIn = true;
                    } else {
                        System.out.println("Login failed");
                        System.out.println("Please try again");
                    }
                    break;
                case 2:
                    MenuHelper.accountCreationMenu();
                    System.out.print("Please enter your first name: ");
                    String firstName = Helper.readLine();
                    System.out.print("Please enter your last name: ");
                    String lastName = Helper.readLine();
                    System.out.print("Please enter your last name: ");
                    String birthDate = Helper.readLine();
                    System.out.print("Please enter your date of birth: ");
                    String email = Helper.readLine();
                    System.out.println("Please enter your desired Username: ");
                    String newUserName = Helper.readLine();
                    System.out.print("Please enter your password: ");
                    String newPassword = Helper.readLine();
                    this.getTeller().createCustomerAccount(firstName, lastName, birthDate, email, newUserName, newPassword);
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
        if (loggedIn && !thisCustomer.hasCheckingAccount() && !thisCustomer.hasSavingsAccount()) {
            boolean exitInnerMenu = false;
            do {
                MenuHelper.innerMenuNoAccounts();
                int choice = Helper.readInt();
                switch (choice) {
                    case 1:
                        System.out.println("Please enter the initial deposit amount: ");
                        double savingsAmount = Helper.readDouble();
                        this.getTeller().createSavingsAccount(thisCustomer, savingsAmount);
                        exitInnerMenu = true;
                        break;
                    case 2:
                        System.out.println("Please enter the initial deposit amount: ");
                        double depositAmount = Helper.readDouble();
                        this.getTeller().createCheckingAccount(thisCustomer, depositAmount);
                        exitInnerMenu = true;
                        break;
                    case 3:
                        System.out.println("Please enter your password: ");
                        String password = Helper.readLine();
                        this.getTeller().removeCustomerAccount(thisCustomer.getUserName(), password);
                        break;
                    case 4:
                        exitInnerMenu = true;
                        LogHandler.doEventLogging("Customer logged out: " + thisCustomer);
                        LogHandler.doEventLogging("CLI exit");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again!");
                        break;
                }
            } while (!exitInnerMenu);
        }
        if (loggedIn && !thisCustomer.hasCheckingAccount() && thisCustomer.hasSavingsAccount()) {
            boolean exitInnerSavingsMenu = false;
            do {
                MenuHelper.innerMenuWithSavingsAccount();
                int choice = Helper.readInt();
                switch (choice) {
                    case 1:
                        boolean exitSavingsAccountMenu = false;
                        do {
                            System.out.println("Hello " + thisCustomer.getUserName() + "!");
                            System.out.println("Your savings account overview: " + thisCustomer.getAccountByType("savings"));
                            MenuHelper.savingsMenu();
                            int savingsChoice = Helper.readInt();
                            switch (savingsChoice) {
                                case 1:
                                    System.out.println("Please enter the amount to deposit: ");
                                    double savingsDepositAmount = Helper.readDouble();
                                    this.getTeller().depositFunds(thisCustomer.getAccountByType("savings"), savingsDepositAmount);
                                    break;
                                case 2:
                                    System.out.println("Please enter the amount to withdraw: ");
                                    double savingsWithdrawAmount = Helper.readDouble();
                                    this.getTeller().withdrawFunds(thisCustomer.getAccountByType("savings"), savingsWithdrawAmount);
                                    break;
                                case 3:
                                    System.out.println("Please enter the account number to transfer to: ");
                                    int nrToTransferTo = Helper.readInt();
                                    System.out.println("Please enter the amount to transfer: ");
                                    double savingsTransferAmount = Helper.readDouble();
                                    System.out.println("Please enter a description for the transfer: ");
                                    String savingsTransferDescription = Helper.readLine();
                                    this.getTeller().transferFunds(thisCustomer.getAccountByType("savings"), thisCustomer.getAccountByNumber(nrToTransferTo), savingsTransferAmount, savingsTransferDescription);
                                    break;
                                case 4:
                                    System.out.println("Please enter your password: ");
                                    String enteredPassword = Helper.readLine();
                                    this.getTeller().removeSavingsAccount(thisCustomer, enteredPassword);
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
                        double depositAmount = Helper.readDouble();
                        this.getTeller().createCheckingAccount(thisCustomer, depositAmount);
                        break;
                    case 3:
                        System.out.println("Please enter your password: ");
                        String password = Helper.readLine();
                        this.getTeller().removeCustomerAccount(thisCustomer.getUserName(), password);
                        break;
                    case 4:
                        exitInnerSavingsMenu = true;
                        LogHandler.doEventLogging("Customer logged out: " + thisCustomer);
                        LogHandler.doEventLogging("CLI exit");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again!");
                        break;
                }
            } while (!exitInnerSavingsMenu);
        }

        if (loggedIn && thisCustomer.hasCheckingAccount() && !thisCustomer.hasSavingsAccount()) {
            boolean exitInnerCheckingMenu = false;
            do {
                MenuHelper.innerMenuWithCheckingAccount();
                int choice = Helper.readInt();
                switch (choice) {
                    case 1:
                        boolean exitCheckingAccountMenu = false;
                        do {
                            System.out.println("Hello " + thisCustomer.getUserName() + "!");
                            System.out.println("Your checking account overview: " + thisCustomer.getAccountByType("checking"));
                            MenuHelper.checkingMenu();
                            int checkingChoice = Helper.readInt();
                            switch (checkingChoice) {
                                case 1:
                                    System.out.println("Please enter the amount to deposit: ");
                                    double checkingDepositAmount = Helper.readDouble();
                                    this.getTeller().depositFunds(thisCustomer.getAccountByType("checking"), checkingDepositAmount);
                                    break;
                                case 2:
                                    System.out.println("Please enter the amount to withdraw: ");
                                    double checkingWithdrawAmount = Helper.readDouble();
                                    this.getTeller().withdrawFunds(thisCustomer.getAccountByType("checking"), checkingWithdrawAmount);
                                    break;
                                case 3:
                                    System.out.println("Please enter the account number to transfer to: ");
                                    int nrToTransferTo = Helper.readInt();
                                    System.out.println("Please enter the amount to transfer: ");
                                    double checkingTransferAmount = Helper.readDouble();
                                    System.out.println("Please enter a description for the transfer: ");
                                    String checkingTransferDescription = Helper.readLine();
                                    this.getTeller().transferFunds(thisCustomer.getAccountByType("checking"), thisCustomer.getAccountByNumber(nrToTransferTo), checkingTransferAmount, checkingTransferDescription);
                                    break;
                                case 4:
                                    System.out.println("Please enter your password: ");
                                    String enteredPassword = Helper.readLine();
                                    this.getTeller().removeCheckingAccount(thisCustomer, enteredPassword);
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
                        double depositAmount = Helper.readDouble();
                        this.getTeller().createSavingsAccount(thisCustomer, depositAmount);
                        exitInnerCheckingMenu = true;
                        break;
                    case 3:
                        System.out.println("Please enter your password: ");
                        String password = Helper.readLine();
                        this.getTeller().removeCustomerAccount(thisCustomer.getUserName(), password);
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

        if (loggedIn && thisCustomer.hasCheckingAccount() && thisCustomer.hasSavingsAccount()) {
            boolean exitInnerMenu = false;
            do {
                MenuHelper.innerMenuWithBothAccounts();
                int choice = Helper.readInt();
                switch (choice) {
                    case 1:
                        boolean exitSavingsAccountMenu = false;
                        do {
                            System.out.println("Hello " + thisCustomer.getUserName() + "!");
                            System.out.println("Your savings account overview: " + thisCustomer.getAccountByType("savings"));
                            MenuHelper.savingsMenu();
                            int savingsChoice = Helper.readInt();
                            switch (savingsChoice) {
                                case 1:
                                    System.out.println("Please enter the amount to deposit: ");
                                    double savingsDepositAmount = Helper.readDouble();
                                    this.getTeller().depositFunds(thisCustomer.getAccountByType("savings"), savingsDepositAmount);
                                    break;
                                case 2:
                                    System.out.println("Please enter the amount to withdraw: ");
                                    double savingsWithdrawAmount = Helper.readDouble();
                                    this.getTeller().withdrawFunds(thisCustomer.getAccountByType("savings"), savingsWithdrawAmount);
                                    break;
                                case 3:
                                    System.out.println("Please enter the account number to transfer to: ");
                                    int nrToTransferTo = Helper.readInt();
                                    System.out.println("Please enter the amount to transfer: ");
                                    double savingsTransferAmount = Helper.readDouble();
                                    System.out.println("Please enter a description for the transfer: ");
                                    String savingsTransferDescription = Helper.readLine();
                                    this.getTeller().transferFunds(thisCustomer.getAccountByType("savings"), thisCustomer.getAccountByNumber(nrToTransferTo), savingsTransferAmount, savingsTransferDescription);
                                    break;
                                case 4:
                                    System.out.println("Please enter your password: ");
                                    String enteredPassword = Helper.readLine();
                                    this.getTeller().removeSavingsAccount(thisCustomer, enteredPassword);
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
                            System.out.println("Hello " + thisCustomer.getUserName() + "!");
                            System.out.println("Your checking account overview: " + thisCustomer.getAccountByType("checking"));
                            MenuHelper.checkingMenu();
                            int checkingChoice = Helper.readInt();
                            switch (checkingChoice) {
                                case 1:
                                    System.out.println("Please enter the amount to deposit: ");
                                    double checkingDepositAmount = Helper.readDouble();
                                    this.getTeller().depositFunds(thisCustomer.getAccountByType("checking"), checkingDepositAmount);
                                    break;
                                case 2:
                                    System.out.println("Please enter the amount to withdraw: ");
                                    double checkingWithdrawAmount = Helper.readDouble();
                                    this.getTeller().withdrawFunds(thisCustomer.getAccountByType("checking"), checkingWithdrawAmount);
                                    break;
                                case 3:
                                    System.out.println("Please enter the account number to transfer to: ");
                                    int nrToTransferTo = Helper.readInt();
                                    System.out.println("Please enter the amount to transfer: ");
                                    double checkingTransferAmount = Helper.readDouble();
                                    System.out.println("Please enter a description for the transfer: ");
                                    String checkingTransferDescription = Helper.readLine();
                                    this.getTeller().transferFunds(thisCustomer.getAccountByType("checking"), thisCustomer.getAccountByNumber(nrToTransferTo), checkingTransferAmount, checkingTransferDescription);
                                    break;
                                case 4:
                                    System.out.println("Please enter your password: ");
                                    String enteredPassword = Helper.readLine();
                                    this.getTeller().removeCheckingAccount(thisCustomer, enteredPassword);
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
                        this.getTeller().removeCustomerAccount(thisCustomer.getUserName(), password);
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


    public Customer login(String userName, String password) {
        String hashedPassword = PasswordHasher.hashPassword(password);
        return this.getTeller().login(userName, hashedPassword);
    }

    protected void initialize() {
        System.out.println("Welcome to Kessler Financial Ltd.");
        Helper.sleep(1000);
        LogHandler.doOutputLogging("CLI initialized");
    }

    @Override
    public void reportCreateCustomerAccount(boolean success) {}

    @Override
    public void reportRemoveCustomerAccount(boolean status) {

    }

    @Override
    public void reportCreateCheckingAccount(boolean status) {

    }

    @Override
    public void reportCreateSavingsAccount(boolean status) {

    }

    @Override
    public void reportRemoveSavingsAccount(boolean status) {

    }

    @Override
    public void reportRemoveCheckingAccount(boolean status) {

    }

    @Override
    public void reportDeposit(boolean status) {

    }

    @Override
    public void reportWithdraw(boolean status) {

    }

    @Override
    public void reportTransfer(boolean status) {

    }


}
