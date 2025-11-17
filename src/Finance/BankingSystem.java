package Finance;// Finance.BankingSystem.java
// Handles user interaction and manages all banking operations

import java.util.Scanner;

public class BankingSystem {

    // Array to store up to 10 accounts
    private static final int MAX_ACCOUNTS = 10;
    private BankAccount[] accounts = new BankAccount[MAX_ACCOUNTS];
    private int accountCount = 0;

    private Scanner input = new Scanner(System.in);

    // Starts the banking system
    public void start() {
        int choice;

        do {
            System.out.println("\n==== Banking System Menu ====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Display Account Info");
            System.out.println("5. Apply Interest (Savings Only)");
            System.out.println("6. View Total Accounts");
            System.out.println("0. Exit Banking System");
            System.out.print("Enter your choice: ");

            // check for input type mismatch
            while (!input.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                input.next();
            }
            choice = input.nextInt();
            input.nextLine(); // consume newline

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> depositMoney();
                case 3 -> withdrawMoney();
                case 4 -> displayAccountInfo();
                case 5 -> applyInterest();
                case 6 -> System.out.println("Total accounts created: " + BankAccount.getTotalAccounts());
                case 0 -> System.out.println("Exiting Banking System...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    // Create a new account
    private void createAccount() {
        if (accountCount >= MAX_ACCOUNTS) {
            System.out.println("Cannot create more accounts. Limit reached.");
            return;
        }

        System.out.print("Enter account holder name: ");
        String name = input.nextLine();

        System.out.print("Enter account number: ");
        String number = input.nextLine();

        System.out.print("Enter initial deposit: ");
        while (!input.hasNextDouble()) {
            System.out.print("Invalid input. Enter a number: ");
            input.next();
        }
        double deposit = input.nextDouble();
        input.nextLine(); // consume newline

        System.out.print("Is this a savings account? (yes/no): ");
        String type = input.nextLine().trim().toLowerCase();

        // Conditional operator to decide type of account
        BankAccount account;
        if (type.equals("yes")) {
            account = new SavingAccount(name, number, deposit);
        } else {
            account = new BankAccount(name, number, deposit);
        }
        accounts[accountCount++] = account;
        System.out.println("✅ Account created successfully!");
    }

    // Find account by account number
    private BankAccount findAccount(String number) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber().equalsIgnoreCase(number)) {
                return accounts[i];
            }
        }
        return null;
    }

    // Deposit money
    private void depositMoney() {
        System.out.print("Enter account number: ");
        String number = input.nextLine();

        BankAccount account = findAccount(number);
        if (account == null) {
            System.out.println("❌ Account not found.");
            return;
        }

        System.out.print("Enter amount to deposit: ");
        while (!input.hasNextDouble()) {
            System.out.print("Invalid input. Enter a number: ");
            input.next();
        }
        double amount = input.nextDouble();
        input.nextLine();

        account.deposit(amount);
    }

    // Withdraw money
    private void withdrawMoney() {
        System.out.print("Enter account number: ");
        String number = input.nextLine();

        BankAccount account = findAccount(number);
        if (account == null) {
            System.out.println("❌ Account not found.");
            return;
        }

        System.out.print("Enter amount to withdraw: ");
        while (!input.hasNextDouble()) {
            System.out.print("Invalid input. Enter a number: ");
            input.next();
        }
        double amount = input.nextDouble();
        input.nextLine();

        account.withdraw(amount);
    }

    // Display account info
    private void displayAccountInfo() {
        System.out.print("Enter account number: ");
        String number = input.nextLine();

        BankAccount account = findAccount(number);
        if (account != null) {
            System.out.println();
            account.displayInfo();
        } else {
            System.out.println("❌ Account not found.");
        }
    }

    // Apply interest to savings accounts only
    private void applyInterest() {
        System.out.print("Enter account number: ");
        String number = input.nextLine();

        BankAccount account = findAccount(number);
        if (account == null) {
            System.out.println("❌ Account not found.");
            return;
        }

        if (account instanceof SavingAccount savings) {
            System.out.print("Enter number of years: ");
            while (!input.hasNextInt()) {
                System.out.print("Invalid input. Enter a whole number: ");
                input.next();
            }
            int years = input.nextInt();
            input.nextLine();
            savings.applyInterest(years);
        } else {
            System.out.println("⚠️ Interest applies only to savings accounts.");
        }
    }
}
