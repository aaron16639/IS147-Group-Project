package Finance;// Finance.BankAccount.java
// Represents a general bank account

import java.util.Date; // For timestamps

public class BankAccount {
    // Static variable (shared among all objects)
    private static int totalAccounts = 0;

    // Instance variables (encapsulated)
    private String accountHolder;
    private String accountNumber;
    private double balance;
    private Date dateCreated;

    // Constructor
    public BankAccount(String accountHolder, String accountNumber, double initialDeposit) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = initialDeposit;
        this.dateCreated = new Date(); // Current date
        totalAccounts++;
    }

    // Getter methods (Encapsulation)
    public String getAccountHolder() {
        return accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    // Deposit money (method returns new balance)
    public double deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited $%.2f%n", amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
        return balance;
    }

    // Withdraw money
    public double withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.printf("Successfully withdrew $%.2f%n", amount);
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
        return balance;
    }

    // Static method
    public static int getTotalAccounts() {
        return totalAccounts;
    }

    // Display account info
    public void displayInfo() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.printf("Balance: $%.2f%n", balance);
        System.out.println("Date Created: " + dateCreated);
    }
}
