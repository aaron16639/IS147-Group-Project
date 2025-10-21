package Modules;

import java.util.Scanner;

public class HumanResources {
    public static Scanner input = new Scanner(System.in);
    public static int toolChoice = 0;

    public static void main(String[] args) {
        welcome();
        chooseTool();

        input.close();
    }

    public static void welcome() {
        System.out.println("Welcome to the Human Resources module. What would you like to do today?");

        System.out.println("1. Manage Employees");
        System.out.println("2. Manage PTO/Vacations");
        System.out.println("0. Quit");
    }

    public static void chooseTool() {
        if (input.hasNextInt()) {
            toolChoice = input.nextInt();
        } else {
            System.out.println("You have inputted an invalid input. Please try again.");
            chooseTool();
            // TODO: Fix error for invalid input
        }

        switch (toolChoice) {
            case 1:
                showEmployees();
            case 2:
                showVacay();
            case 0:
                quit();
        }
    }

    // TODO: Make Tool pages
    public static void showEmployees() {

    }
    public static void showVacay() {

    }
    public static void quit() {

    }

}