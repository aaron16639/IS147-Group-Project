package Operations;

public class Customers {
    private String customersId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    public Customers(String customerId, String name, String email,
                    String phoneNumber, String address) {
        this.customersId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getCustomerId() { return customersId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setAddress(String address) { this.address = address; }

    public void displayInfo() {
        System.out.println("Customer ID: " + customersId);
        System.out.println("Name       : " + name);
        System.out.println("Email      : " + email);
        System.out.println("Phone      : " + phoneNumber);
        System.out.println("Address    : " + address);
        System.out.println("---------------------------------");
    }
}