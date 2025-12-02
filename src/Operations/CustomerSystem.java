package Operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerSystem {

    private final List<Customers> customers = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        int choice;

        do {
            System.out.println("\n=== Customer Management ===");
            System.out.println("1. Add Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Search Customer by ID");
            System.out.println("4. Update Customer");
            System.out.println("5. Delete Customer");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter choice: ");

            choice = readInt();

            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> viewAllCustomers();
                case 3 -> searchCustomer();
                case 4 -> updateCustomer();
                case 5 -> deleteCustomer();
                case 0 -> System.out.println("Returning to ERP main menu...");
                default -> System.out.println("Invalid selection, try again.");
            }

        } while (choice != 0);
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            scanner.next();
        }
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    private void addCustomer() {
        System.out.println("\n--- Add Customer ---");

        System.out.print("Customer ID: ");
        String id = scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        customers.add(new Customers(id, name, email, phone, address));
        System.out.println("Customer successfully added.");
    }

    private void viewAllCustomers() {
        System.out.println("\n--- Customer List ---");

        if (customers.isEmpty()) {
            System.out.println("No customers in system.");
            return;
        }

        for (Customers c : customers) {
            c.displayInfo();
        }
    }

    private Customers findCustomer(String id) {
        for (Customers c : customers) {
            if (c.getCustomerId().equalsIgnoreCase(id)) return c;
        }
        return null;
    }

    private void searchCustomer() {
        System.out.print("\nEnter Customer ID: ");
        Customers customer = findCustomer(scanner.nextLine());

        if (customer == null) System.out.println("Customer not found.");
        else customer.displayInfo();
    }

    private void updateCustomer() {
        System.out.print("\nEnter Customer ID: ");
        Customers customer = findCustomer(scanner.nextLine());

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("New name (leave blank to keep): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) customer.setName(name);

        System.out.print("New email (leave blank to keep): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) customer.setEmail(email);

        System.out.print("New phone (leave blank to keep): ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) customer.setPhoneNumber(phone);

        System.out.print("New address (leave blank to keep): ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) customer.setAddress(address);

        System.out.println("Customer updated successfully.");
    }

    private void deleteCustomer() {
        System.out.print("\nEnter Customer ID to delete: ");
        Customers customer = findCustomer(scanner.nextLine());

        if (customer == null) System.out.println("Customer not found.");
        else {
            customers.remove(customer);
            System.out.println("Customer deleted.");
        }
    }
}