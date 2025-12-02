// ERPSystemMain.java
// Main entry point for the ERP system

import Finance.BankingSystem;
import Operations.CustomerSystem;

import java.util.Scanner;

public class ERPSystemMain {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        BankingSystem bankingSystem = new BankingSystem(); // Create the banking system object
        CustomerSystem customerSystem = new CustomerSystem();
        int choice;

        do {
            System.out.println("\n===== ERP System Main Menu =====");
            System.out.println("1. Banking / Finance System");
            System.out.println("2. Human Resources System (Coming Soon)");
            System.out.println("3. Operations System");
            System.out.println("4. Inventory Management (Coming Soon)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Redirecting to Banking System ---");
                    bankingSystem.start(); // Open banking system menu
                    break;
                case 2:
                    System.out.println("HR System will be added by another team member.");
                    break;
                case 3:
                    System.out.println("\n--- Redirecting to Operations System ---");
                    customerSystem.start(); // Open Operations system menu
                    break;
                case 4:
                    System.out.println("Inventory System will be added by another team member.");
                    break;
                case 0:
                    System.out.println("Exiting ERP System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 0);

        input.close();
    }
}
