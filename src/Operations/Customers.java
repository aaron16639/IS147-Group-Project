package Operations;

public class Customers {
    private String customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String customerType;  // NEW

    public Customers(String customerId, String name, String email,
                     String phoneNumber, String address, String customerType) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.customerType = customerType;
    }

    // getters
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
    public String getCustomerType() { return customerType; }

    // setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setAddress(String address) { this.address = address; }
    public void setCustomerType(String customerType) { this.customerType = customerType; }

    public void displayInfo() {
        System.out.println("ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Address: " + address);
        System.out.println("Customer Type: " + (customerType == null || customerType.isEmpty()
                ? "Unspecified"
                : customerType));
    }
}