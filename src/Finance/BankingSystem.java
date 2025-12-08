// BankingSystem.java
// Banking system with inheritance, abstraction, arrays, loops, Math class, etc.
package Finance;

import java.util.Scanner;
import java.util.Date;
import java.util.Random;

public class BankingSystem {

    private Scanner input = new Scanner(System.in);

    private final int MAX_ACCOUNTS = 20;
    private BankAccount[] accounts = new BankAccount[MAX_ACCOUNTS];
    private int accountCount = 0;

    public void start() {
        int choice;

        do {
            System.out.println("\n===== BANKING SYSTEM MENU =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display Account Info");
            System.out.println("5. Apply Interest (Savings Only)");
            System.out.println("6. Show Total Accounts");
            System.out.println("0. Go Back to ERP Main Menu");
            System.out.print("Enter choice: ");

            while (!input.hasNextInt()) {
                System.out.print("Enter a number: ");
                input.next();
            }
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    displayAccount();
                    break;
                case 5:
                    applyInterest();
                    break;
                case 6:
                    System.out.println("Total accounts created: " + BankAccount.getTotalAccounts());
                    break;
                case 0:
                    System.out.println("Returning to ERP Main Menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 0);
    }

    private void createAccount() {

        if (accountCount >= MAX_ACCOUNTS) {
            System.out.println("Maximum accounts reached.");
            return;
        }

        System.out.print("Enter account holder name: ");
        String name = input.nextLine();

        Random rand = new Random();
        String accNum = "ACC" + (1000 + rand.nextInt(9000));

        System.out.print("Enter initial deposit: ");
        while (!input.hasNextDouble()) {
            System.out.print("Enter a number: ");
            input.next();
        }
        double deposit = input.nextDouble();
        input.nextLine();

        System.out.print("Is this a Savings Account? (yes/no): ");
        String type = input.nextLine().toLowerCase();

        BankAccount acc;

        if (type.startsWith("y")) {
            acc = new SavingsAccount(name, accNum, deposit);
        } else {
            acc = new BasicAccount(name, accNum, deposit);
        }

        accounts[accountCount] = acc;
        accountCount++;

        System.out.println("Account Created Successfully!");
        System.out.println("Account Number: " + accNum);
        System.out.println("Created On: " + new Date());
    }

    private BankAccount findAccount(String accNum) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber().equalsIgnoreCase(accNum)) {
                return accounts[i];
            }
        }
        return null;
    }

    private void deposit() {
        System.out.print("Enter account number: ");
        String accNum = input.nextLine();

        BankAccount acc = findAccount(accNum);

        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter deposit amount: ");
        while (!input.hasNextDouble()) {
            System.out.print("Enter a number: ");
            input.next();
        }
        double amt = input.nextDouble();
        input.nextLine();

        acc.deposit(amt);
    }

    private void withdraw() {
        System.out.print("Enter account number: ");
        String accNum = input.nextLine();

        BankAccount acc = findAccount(accNum);

        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter withdrawal amount: ");
        while (!input.hasNextDouble()) {
            System.out.print("Enter a number: ");
            input.next();
        }
        double amt = input.nextDouble();
        input.nextLine();

        acc.withdraw(amt);
    }

    private void displayAccount() {
        System.out.print("Enter account number: ");
        String accNum = input.nextLine();

        BankAccount acc = findAccount(accNum);

        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        acc.displayInfo();
    }

    private void applyInterest() {
        System.out.print("Enter account number: ");
        String accNum = input.nextLine();

        BankAccount acc = findAccount(accNum);

        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        if (acc instanceof SavingsAccount) {
            System.out.print("Enter number of years: ");
            while (!input.hasNextInt()) {
                System.out.print("Enter a number: ");
                input.next();
            }
            int years = input.nextInt();
            input.nextLine();

            ((SavingsAccount) acc).applyInterest(years);
        } else {
            System.out.println("This is not a savings account.");
        }
    }
}

// =======================================================
// Abstract Class: BankAccount
// =======================================================

abstract class BankAccount {

    private String holderName;
    private String accountNumber;
    protected double balance;

    private static int totalAccounts = 0;

    public BankAccount(String holderName, String number, double bal) {
        this.holderName = holderName;
        this.accountNumber = number;
        this.balance = bal;
        totalAccounts++;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amt) {
        if (amt <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        balance += amt;
        System.out.println("Deposit successful. New balance: " + balance);
    }

    public void withdraw(double amt) {
        if (amt <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        if (amt > balance) {
            System.out.println("Insufficient funds.");
            return;
        }
        balance -= amt;
        System.out.println("Withdraw successful. New balance: " + balance);
    }

    // abstract method
    public abstract void displayInfo();

    // Overloaded method
    public void displayInfo(String title) {
        System.out.println("--- " + title + " ---");
        displayInfo();
    }
}

// =======================================================
// BasicAccount (Non-Savings)
// =======================================================

class BasicAccount extends BankAccount {

    public BasicAccount(String name, String num, double bal) {
        super(name, num, bal);
    }

    public void displayInfo() {
        System.out.println("\n--- Basic Account Info ---");
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Balance: " + balance);
    }
}

// =======================================================
// SavingsAccount (Interest)
// =======================================================

class SavingsAccount extends BankAccount {

    private double INTEREST_RATE = 0.05;

    public SavingsAccount(String name, String num, double bal) {
        super(name, num, bal);
    }

    public void displayInfo() {
        System.out.println("\n--- Savings Account Info ---");
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Balance: " + balance);
        System.out.println("Interest Rate: " + (INTEREST_RATE * 100) + "%");
    }

    public void applyInterest(int years) {
        if (years <= 0) {
            System.out.println("Years must be positive.");
            return;
        }

        balance = balance * Math.pow(1 + INTEREST_RATE, years);

        System.out.println("New balance after " + years + " years: " + balance);
    }
}
