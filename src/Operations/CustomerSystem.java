package Operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CustomerSystem {

    // In-memory list of customers
    private final List<Customers> customers = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    // Main menu loop
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

            choice = readInt();

            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> viewAllCustomers();
                case 3 -> searchCustomerById();
                case 4 -> searchCustomerByName();
                case 5 -> updateCustomer();
                case 6 -> deleteCustomer();
                case 7 -> showSummary();
                case 0 -> System.out.println("Returning to ERP main menu...");
                default -> System.out.println("Invalid selection, try again.");
            }

        } while (choice != 0);
    }

    // Safer int input
    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            scanner.next(); // discard invalid input
        }
        int number = scanner.nextInt();
        scanner.nextLine(); // clear newline
        return number;
    }

    // ---------- Core Operations ----------

    private void addCustomer() {
        System.out.println("\n--- Add Customer ---");

        System.out.print("Customer ID: ");
        String id = scanner.nextLine().trim();

        // Check for duplicate ID
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

        Customers newCustomer = new Customers(id, name, email, phone, address, type);
        customers.add(newCustomer);
        System.out.println("Customer successfully added.");
    }

    private void viewAllCustomers() {
        System.out.println("\n--- Customer List ---");

        if (customers.isEmpty()) {
            System.out.println("No customers in system.");
            return;
        }

        int index = 1;
        for (Customers c : customers) {
            System.out.println("Customer #" + index++);
            c.displayInfo();
            System.out.println("-----------------------------------");
        }
    }

    // Helper: find single customer by exact ID
    private Customers findCustomer(String id) {
        for (Customers c : customers) {
            if (c.getCustomerId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    // ---------- Search Features ----------

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

    private void searchCustomerByName() {
        System.out.print("\nEnter full or partial customer name: ");
        String nameQuery = scanner.nextLine().trim().toLowerCase();

        List<Customers> matches = new ArrayList<>();
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

    // ---------- Update & Delete ----------

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

    // ---------- Simple Analytics / Summary ----------

    private void showSummary() {
        System.out.println("\n--- Customer Summary ---");
        System.out.println("Total number of customers: " + customers.size());

        if (customers.isEmpty()) {
            return;
        }

        int completeProfiles = 0;
        Map<String, Integer> typeCounts = new HashMap<>();

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