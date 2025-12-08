package Inventory;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventorySystem { // List to hold all inventory items
    private final List<InventoryItem> items = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    // Entry point from the main ERP menu
    public void start() {
        int choice;

        do {
            System.out.println("\n==== Inventory System ====");
            System.out.println("1. Add new inventory item");
            System.out.println("2. View all inventory items");
            System.out.println("3. Update quantity of an item");
            System.out.println("0. Return to main menu");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // clear leftover newline

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    viewItems();
                    break;
                case 3:
                    updateQuantity();
                    break;
                case 0:
                    System.out.println("Returning to main ERP menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);
    }

    // 1. Add a new item
    private void addItem() {
        System.out.println("\n--- Add New Inventory Item ---");
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine().trim();

        System.out.print("Enter item name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter location (Where are you storing this?): ");
        String location = scanner.nextLine().trim();

        System.out.print("Enter starting quantity: ");
        int qty = readIntSafe();

        InventoryItem item = new InventoryItem(id, name, qty, location);
        items.add(item);

        System.out.println("Item added to inventory successfully!.");
    }

    // 2. View all items
    private void viewItems() {
        System.out.println("\n--- Current Inventory ---");

        if (items.isEmpty()) {
            System.out.println("No items in inventory.");
            return;
        }

        for (InventoryItem item : items) {
            item.displayInfo();
        }
    }

    // 3. Update the quantity of an item
    private void updateQuantity() {
        System.out.println("\n--- Update Item Quantity ---");
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine().trim();

        InventoryItem found = findItemById(id);
        if (found == null) {
            System.out.println("No item found with that ID.");
            return;
        }

        System.out.println("Current quantity: " + found.getQuantity());
        System.out.println("1. Set new quantity");
        System.out.println("2. Increase quantity");
        System.out.println("3. Decrease quantity");
        System.out.print("Choose an option: ");

        int choice = readIntSafe();

        switch (choice) {
            case 1:
                System.out.print("Enter new quantity: ");
                int newQty = readIntSafe();
                found.setQuantity(newQty);
                System.out.println("Quantity updated.");
                break;
            case 2:
                System.out.print("Enter amount to add: ");
                int add = readIntSafe();
                found.increaseQuantity(add);
                System.out.println("Quantity increased.");
                break;
            case 3:
                System.out.print("Enter amount to remove: ");
                int remove = readIntSafe();
                found.decreaseQuantity(remove);
                System.out.println("Quantity decreased.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Helper: find an item by ID
    private InventoryItem findItemById(String id) {
        for (InventoryItem item : items) {
            if (item.getItemId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }

    // Helper: safe integer input
    private int readIntSafe() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a whole number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // clear newline
        return value;
    }
}

