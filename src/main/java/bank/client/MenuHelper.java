package bank.client;

public class MenuHelper {

    static String seperator = "----------------------------------------";

    public static void initialMenu() {
        System.out.println(seperator);
        System.out.println("1. Login");
        System.out.println("2. Create Customer Account");
        System.out.println("3. Exit");
        System.out.println(seperator);
        System.out.println("Please enter the according number to proceed: ");
    }

    public static void innerMenuNoAccounts() {
        System.out.println("What can we do for you today?");
        System.out.println(seperator);
        System.out.println("1. Create a Savings Account");
        System.out.println("2. Create a Checking Account");
        System.out.println("3. Remove Customer Account");
        System.out.println("4. Exit");
        System.out.println(seperator);
        System.out.println("Please enter the according number to proceed: ");
    }

    public static void innerMenuWithSavingsAccount() {
        System.out.println("What can we do for you today?");
        System.out.println(seperator);
        System.out.println("1. Access Savings Account");
        System.out.println("2. Create a Checking Account");
        System.out.println("3. Remove Customer Account");
        System.out.println("4. Exit");
        System.out.println(seperator);
        System.out.println("Please enter the according number to proceed: ");
    }

    public static void innerMenuWithCheckingAccount() {
        System.out.println("What can we do for you today?");
        System.out.println(seperator);
        System.out.println("1. Create a Savings Account");
        System.out.println("2. Access Checking Account");
        System.out.println("3. Remove Customer Account");
        System.out.println("4. Exit");
        System.out.println(seperator);
        System.out.println("Please enter the according number to proceed: ");
    }

    public static void innerMenuWithBothAccounts() {
        System.out.println("What can we do for you today?");
        System.out.println(seperator);
        System.out.println("1. Access Savings Account");
        System.out.println("2. Access Checking Account");
        System.out.println("3. Remove Customer Account");
        System.out.println("4. Display overview of your accounts");
        System.out.println("5. Exit");
        System.out.println(seperator);
        System.out.println("Please enter the according number to proceed: ");
    }

    public static void savingsMenu() {
        System.out.println("What can we do for you today?");
        System.out.println(seperator);
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. Remove Account");
        System.out.println("5. Exit");
        System.out.println(seperator);
        System.out.println("Please enter the according number to proceed: ");
    }

    public static void checkingMenu() {
        System.out.println("What can we do for you today?");
        System.out.println(seperator);
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. Remove Account");
        System.out.println("5. Exit");
        System.out.println(seperator);
        System.out.println("Please enter the according number to proceed: ");
    }

    public static void accountCreationMenu() {
        System.out.println("Let's get you started!");
        System.out.println(seperator);
        System.out.println("We'll need some information from you: ");
    }
}
