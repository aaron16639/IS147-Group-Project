// SavingsAccount.java
// Represents a savings account that earns interest

public class SavingAccount extends BankAccount {

    // Constant interest rate
    private static final double INTEREST_RATE = 0.03; // 3%

    // Constructor
    public SavingAccount(String accountHolder, String accountNumber, double initialDeposit) {
        super(accountHolder, accountNumber, initialDeposit); // Call parent constructor
    }

    // Calculate interest using Math class
    public double calculateInterest(int years) {
        // Compound interest formula: A = P * (1 + r)^t
        double futureBalance = getBalance() * Math.pow((1 + INTEREST_RATE), years);
        double interest = futureBalance - getBalance();
        return Math.round(interest * 100.0) / 100.0; // Round to 2 decimal places
    }

    // Apply interest to balance
    public void applyInterest(int years) {
        double interest = calculateInterest(years);
        deposit(interest);
        System.out.printf("Interest of $%.2f added for %d year(s).%n", interest, years);
    }

    // Override displayInfo method to include account type
    @Override
    public void displayInfo() {
        System.out.println("Account Type: Savings Account");
        super.displayInfo(); // Call parent displayInfo
        System.out.println("Annual Interest Rate: " + (INTEREST_RATE * 100) + "%");
    }
}
