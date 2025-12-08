package HumanResources;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanResources {
    private final List<Employee> employees = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        int choice;

        do {
            displayMainMenu();
            choice = readInt();

            switch (choice) {
                case 1 -> manageEmployees();
                case 2 -> showVacay();
                case 0 -> System.out.println("Exiting Human Resources module...");
                default -> System.out.println("Invalid selection, try again.");
            }

        } while (choice != 0);
    }

    private void displayMainMenu() {
        System.out.println("\n=== Human Resources Module ===");
        System.out.println("1. Manage Employees");
        System.out.println("2. View PTO/Vacations");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            scanner.next();
        }
        int number = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return number;
    }

    private void manageEmployees() {
        int choice;

        do {
            System.out.println("\n--- Manage Employees ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Delete Employee");
            System.out.println("3. View All Employees");
            System.out.println("4. Update Employee");
            System.out.println("5. Manage PTO");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            choice = readInt();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> deleteEmployee();
                case 3 -> viewAllEmployees();
                case 4 -> updateEmployee();
                case 5 -> managePTO();
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid selection, try again.");
            }

        } while (choice != 0);
    }

    private void addEmployee() {
        System.out.println("\n--- Add Employee ---");

        System.out.print("Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Salary: $");
        int salary = readInt();

        employees.add(new Employee(name, salary));
        System.out.println("Employee successfully added.");
    }

    private void deleteEmployee() {
        if (employees.isEmpty()) {
            System.out.println("No employees to delete.");
            return;
        }

        viewAllEmployees();
        System.out.print("Enter the number of the employee to delete (or 0 to cancel): ");
        int index = readInt();

        if (index == 0) {
            System.out.println("Deletion cancelled.");
            return;
        }

        if (index > 0 && index <= employees.size()) {
            Employee removed = employees.remove(index - 1);
            System.out.println("Employee '" + removed.getName() + "' deleted successfully.");
        } else {
            System.out.println("Invalid employee number.");
        }
    }

    private void viewAllEmployees() {
        System.out.println("\n--- Employee List ---");

        if (employees.isEmpty()) {
            System.out.println("No employees in system.");
            return;
        }

        for (int i = 0; i < employees.size(); i++) {
            System.out.print((i + 1) + ". ");
            employees.get(i).displayInfo();
        }
    }

    private Employee selectEmployee(String action) {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return null;
        }

        viewAllEmployees();
        System.out.print("Enter the number of the employee to " + action + " (or 0 to cancel): ");
        int index = readInt();

        if (index == 0) {
            System.out.println("Action cancelled.");
            return null;
        }

        if (index > 0 && index <= employees.size()) {
            return employees.get(index - 1);
        } else {
            System.out.println("Invalid employee number.");
            return null;
        }
    }

    private void updateEmployee() {
        Employee employee = selectEmployee("update");
        if (employee == null) return;

        System.out.println("\nUpdating: " + employee.getName());

        System.out.print("New name (leave blank to keep): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            employee.setName(name);
        }

        System.out.print("New salary (enter 0 to keep current): $");
        int salary = readInt();
        if (salary > 0) {
            employee.setSalary(salary);
        }

        System.out.println("Employee updated successfully.");
    }

    private void managePTO() {
        Employee employee = selectEmployee("manage PTO for");
        if (employee == null) return;

        System.out.println("\n--- Managing PTO for: " + employee.getName() + " ---");
        System.out.println("Current PTO days: " + employee.getPtoDays());
        System.out.println("1. Add PTO days");
        System.out.println("2. Deduct PTO days");
        System.out.println("0. Cancel");
        System.out.print("Enter choice: ");

        int choice = readInt();

        if (choice == 0) {
            System.out.println("Action cancelled.");
            return;
        }

        System.out.print("Enter number of days: ");
        int days = readInt();

        if (days <= 0) {
            System.out.println("Invalid number of days.");
            return;
        }

        switch (choice) {
            case 1 -> {
                employee.addPtoDays(days);
                System.out.println("Successfully added " + days + " PTO days. New total: " + employee.getPtoDays());
            }
            case 2 -> {
                if (employee.deductPtoDays(days)) {
                    System.out.println("Successfully deducted " + days + " PTO days. New total: " + employee.getPtoDays());
                } else {
                    System.out.println("Error: Not enough PTO days available.");
                }
            }
            default -> System.out.println("Invalid option.");
        }
    }

    private void showVacay() {
        System.out.println("\n--- PTO/Vacation Summary ---");

        if (employees.isEmpty()) {
            System.out.println("No employees in system.");
            return;
        }

        System.out.printf("%-25s %10s%n", "Employee Name", "PTO Days");
        System.out.println("-------------------------------------");

        for (Employee emp : employees) {
            System.out.printf("%-25s %10d%n", emp.getName(), emp.getPtoDays());
        }
    }
}