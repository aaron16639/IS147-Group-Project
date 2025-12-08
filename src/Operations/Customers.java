package Operations;

// This class represents a single customer in the system.
// It stores all customer information and provides methods
// to access and update that information.
public class Customers {

    // Data fields

    private String customerId;     // Unique ID for the customer
    private String name;           // Full name of the customer
    private String email;          // Email address
    private String phoneNumber;    // Contact phone number
    private String address;        // Home or business address
    private String customerType;   // Type of customer (ex: Regular, VIP, Business)

    // Constructor: creates a customer
    public Customers(String customerId, String name, String email,
                     String phoneNumber, String address, String customerType) {

        // Assigning constructor parameters to class fields
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.customerType = customerType;
    }

    // Getters (Accessors)

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCustomerType() {
        return customerType;
    }

    // Setters (Mutators)

    // Update the customer's name
    public void setName(String name) {
        this.name = name;
    }

    // Update the customer's email
    public void setEmail(String email) {
        this.email = email;
    }

    // Update the phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Update the address
    public void setAddress(String address) {
        this.address = address;
    }

    // Update the customer type
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    // Display customer information
    public void displayInfo() {
        System.out.println("ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Address: " + address);

        // If customerType is missing, show "Unspecified"
        System.out.println("Customer Type: " +
                (customerType == null || customerType.isEmpty() ? "Unspecified" : customerType));
    }
}