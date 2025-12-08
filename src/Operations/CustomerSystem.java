package Operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// This class controls all customer-related operations for the ERP system.
public class CustomerSystem {

    // Stores all customers in memory
    private final List<Customers> customers = new ArrayList<>();

    // Scanner for user input
    private final Scanner scanner = new Scanner(System.in);

    // Main menu the user interacts with
    public void start() {
        int choice;

        do {
            System.out.println("\n=== Customer Management ===");
            System.out.println("1. Add Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Search Customer by ID");
            System.out.println("4. Search Customer by Name");
            System.out.println("5. Update Customer");
            System.out.println("6. Delete Customer");
            System.out.println("7. View Customer Summary");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter choice: ");

            // Reads only valid integers
            choice = readInt();

            switch (choice) {
                case 1 -> addCustomer();         // Create a new customer
                case 2 -> viewAllCustomers();    // Display all customers
                case 3 -> searchCustomerById();  // Find customer using ID
                case 4 -> searchCustomerByName();// Find by name (partial search)
                case 5 -> updateCustomer();      // Modify existing customer info
                case 6 -> deleteCustomer();      // Remove a customer
                case 7 -> showSummary();         // Show analytics
                case 0 -> System.out.println("Returning to ERP main menu...");
                default -> System.out.println("Invalid selection, try again.");
            }

        } while (choice != 0);
    }

    // Ensures user enters a valid whole number
    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            scanner.next(); // throw away bad input
        }
        int number = scanner.nextInt();
        scanner.nextLine(); // remove leftover newline
        return number;
    }

    // Adds a new customer to the system
    private void addCustomer() {
        System.out.println("\n--- Add Customer ---");

        System.out.print("Customer ID: ");
        String id = scanner.nextLine().trim();

        // Prevents duplicate IDs
        if (findCustomer(id) != null) {
            System.out.println("A customer with that ID already exists. Please use a unique ID.");
            return;
        }

        System.out.print("Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Phone Number: ");
        String phone = scanner.nextLine().trim();

        System.out.print("Address: ");
        String address = scanner.nextLine().trim();

        System.out.print("Customer Type (e.g., Regular, VIP, Corporate): ");
        String type = scanner.nextLine().trim();

        // Create and store the new customer
        Customers newCustomer = new Customers(id, name, email, phone, address, type);
        customers.add(newCustomer);
        System.out.println("Customer successfully added.");
    }

    // Shows all customers in the list
    private void viewAllCustomers() {
        System.out.println("\n--- Customer List ---");

        if (customers.isEmpty()) {
            System.out.println("No customers in system.");
            return;
        }

        int index = 1;
        for (Customers c : customers) {
            System.out.println("Customer #" + index++);
            c.displayInfo(); // show customer details
            System.out.println("-----------------------------------");
        }
    }

    // Helper that returns a customer if IDs match
    private Customers findCustomer(String id) {
        for (Customers c : customers) {
            if (c.getCustomerId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    // Search customer using ID field
    private void searchCustomerById() {
        System.out.print("\nEnter Customer ID: ");
        String id = scanner.nextLine().trim();

        Customers customer = findCustomer(id);

        if (customer == null) {
            System.out.println("Customer not found.");
        } else {
            System.out.println("\n--- Customer Found ---");
            customer.displayInfo();
        }
    }

    // Search customer by partial or full name
    private void searchCustomerByName() {
        System.out.print("\nEnter full or partial customer name: ");
        String nameQuery = scanner.nextLine().trim().toLowerCase();

        List<Customers> matches = new ArrayList<>();

        // Finds all customers whose names contain the search text
        for (Customers c : customers) {
            if (c.getName().toLowerCase().contains(nameQuery)) {
                matches.add(c);
            }
        }

        if (matches.isEmpty()) {
            System.out.println("No customers matched that name.");
            return;
        }

        System.out.println("\n--- Matching Customers ---");
        for (Customers c : matches) {
            c.displayInfo();
            System.out.println("-----------------------------------");
        }
    }

    // Updates customer fields (only changes non-empty inputs)
    private void updateCustomer() {
        System.out.print("\nEnter Customer ID to update: ");
        String id = scanner.nextLine().trim();

        Customers customer = findCustomer(id);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("\nCurrent information:");
        customer.displayInfo();

        System.out.println("\nLeave a field blank to keep the current value.");

        System.out.print("New name (leave blank to keep): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) customer.setName(name.trim());

        System.out.print("New email (leave blank to keep): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) customer.setEmail(email.trim());

        System.out.print("New phone (leave blank to keep): ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) customer.setPhoneNumber(phone.trim());

        System.out.print("New address (leave blank to keep): ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) customer.setAddress(address.trim());

        System.out.print("New customer type (leave blank to keep): ");
        String type = scanner.nextLine();
        if (!type.isEmpty()) customer.setCustomerType(type.trim());

        System.out.println("Customer updated successfully.");
    }

    // Deletes a customer from the list
    private void deleteCustomer() {
        System.out.print("\nEnter Customer ID to delete: ");
        String id = scanner.nextLine().trim();

        Customers customer = findCustomer(id);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("\nCustomer to be deleted:");
        customer.displayInfo();

        System.out.print("Are you sure you want to delete this customer? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("y") || confirm.equals("yes")) {
            customers.remove(customer);
            System.out.println("Customer deleted.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    // Shows useful stats about customers
    private void showSummary() {
        System.out.println("\n--- Customer Summary ---");
        System.out.println("Total number of customers: " + customers.size());

        if (customers.isEmpty()) {
            return;
        }

        int completeProfiles = 0;
        Map<String, Integer> typeCounts = new HashMap<>();

        // Count complete profiles and customer types
        for (Customers c : customers) {

            boolean hasEmail = c.getEmail() != null && !c.getEmail().isEmpty();
            boolean hasPhone = c.getPhoneNumber() != null && !c.getPhoneNumber().isEmpty();

            if (hasEmail && hasPhone) {
                completeProfiles++;
            }

            String type = c.getCustomerType();
            if (type == null || type.isEmpty()) {
                type = "Unspecified";
            }

            typeCounts.put(type, typeCounts.getOrDefault(type, 0) + 1);
        }

        System.out.println("Customers with complete contact info (email + phone): " + completeProfiles);

        System.out.println("\nCustomers by type:");
        for (Map.Entry<String, Integer> entry : typeCounts.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue());
        }
    }
}