package Inventory;

public class InventoryItem {private String itemId;
    private String name;      // item name"
    private int quantity;     // how many units we have
    private String location;  // where it is

    // Constructor
    public InventoryItem(String itemId, String name, int quantity, String location) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.location = location;
    }

    // Getters and setters
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            System.out.println("Quantity cannot be negative. Kept old value.");
            return;
        }
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Increase quantity (for new stock)
    public void increaseQuantity(int amount) {
        if (amount > 0) {
            this.quantity += amount;
        }
    }

    // Decrease quantity (for used / removed stock)
    public void decreaseQuantity(int amount) {
        if (amount > 0) {
            if (this.quantity - amount < 0) {
                System.out.println("Not enough stock to remove that amount.");
            } else {
                this.quantity -= amount;
            }
        }
    }

    // displays 1 item
    public void displayInfo() {
        System.out.println("Item ID: " + itemId);
        System.out.println("Name   : " + name);
        System.out.println("Qty    : " + quantity);
        System.out.println("Location: " + location);
        System.out.println("-----------------------------------");
    }
}

