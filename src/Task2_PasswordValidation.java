import java.util.Scanner;

public class Task2_PasswordValidation {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        printPasswordRequirements();

        System.out.print("Enter your password: ");
        String password = scan.nextLine();

        if (isPassStrong(password)) {
            System.out.println("Password is strong.");
        } else {
            System.out.println("Password does not meet the required conditions.");
        }

        scan.close();
    }

    // Method to print the password requirements
    public static void printPasswordRequirements() {
        System.out.println("Password Requirements:");
        System.out.println("Password must have at least 1 special character");
        System.out.println("Password must be at least 8 characters");
        System.out.println("Password must have at least 1 number");
    }

    // Method to check password strength
    public static boolean isPassStrong(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        String specialChars = "!@#$%^&*()_+-=[]{};':\"\\|,.<>/?";

        // Loop through each character
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            // Check for digit
            if (c >= '0' && c <= '9') {
                hasDigit = true;
            }

            // Check for special character
            if (specialChars.indexOf(c) != -1) {
                hasSpecialChar = true;
            }
        }

        // Return true only if all conditions are met
        return hasDigit && hasSpecialChar;
    }
}
