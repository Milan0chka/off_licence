package user;

import java.util.Objects;
import java.util.Scanner;

import static order.OrderService.*;
import static user.CustomerService.*;

/**
 * Handles authentication processes including login, registration, and logout for the application.
 */
public class AuthenticationService implements Authenticatable {

    private static final Scanner input = new Scanner(System.in);

    /**
     * Performs the login operation for a customer.
     *
     * @return The logged-in Customer object or null if the login fails.
     */
    public Customer login() {

        String[] credentials = promptForEmailAndPassword();
        String email = credentials[0];
        String password = credentials[1];

        Customer customer = findCustomer(email);

        if (customer == null) {
            System.out.println("No customer with this email is found");
            return null;
        }

        if (verifyPassword(customer, password)) {
            System.out.println("Log in successfully.");

            loadCustomerOrders(customer.getID());

            return customer;
        } else {

            System.out.println("You entered the wrong password. Try again. (Enter to interrupt).");

            return retryLogin(customer);
        }
    }

    /**
     * Prompts for and reads the user's email and password.
     *
     * @return An array of Strings containing the email and password.
     */
    private static String[] promptForEmailAndPassword() {
        System.out.println("Email: ");
        String email = input.nextLine();

        System.out.println("Password: ");
        String password = input.nextLine();

        return new String[]{email, password};
    }

    /**
     * Verifies if the entered password matches the customer's password.
     *
     * @param customer The customer whose password is to be verified.
     * @param password The password to verify.
     * @return true if the password matches, false otherwise.
     */
    private static boolean verifyPassword(Customer customer, String password) {
        return Objects.equals(customer.getPassword(), password);
    }

    /**
     * Provides the user with the option to retry logging in after an incorrect password entry.
     *
     * @param customer The customer attempting to log in.
     * @return The Customer object if login is successful, null otherwise.
     */
    private static Customer retryLogin(Customer customer) {
        String password;

        do {
            System.out.println("Password: ");
            password = input.nextLine();

            if (password.equals(customer.getPassword())) {
                loadCustomerOrders(customer.getID());
                return customer;
            }

            System.out.println("You entered the wrong password. Try again. (Enter to interrupt).");
            input.nextLine();

        } while (!password.equals(""));

        return null;
    }

    /**
     * Handles the customer registration process.
     *
     * @return The newly registered Customer object or null if registration fails.
     */
    public Customer register() {
        String[] credentials = registrationPrompt();

        if (credentials == null) {
            return null;
        }

        int age = Integer.parseInt(credentials[0]);
        String name = credentials[1];
        String email = credentials[2];
        String password = credentials[3];

        if (isEmailUsed(email))
            return null;

        Customer newCustomer = new Customer(name, email, age, password);

        addCustomer(newCustomer);

        return newCustomer;
    }

    /**
     * Prompts the user for registration details including age, name, email, and password.
     *
     * @return An array of Strings containing the registration details.
     */
    private static String[] registrationPrompt() {
        System.out.println("Enter your age: ");
        int age = safeReadInt();
        input.nextLine(); // Consume the newline character left in the buffer

        if (age < 18) {
            System.out.println("Only adults can register at this website.");
            return null;
        }

        System.out.println("Enter your full name: ");
        String name = input.nextLine();

        System.out.println("Email: ");
        String email = input.nextLine();

        System.out.println("Password: ");
        String password = input.nextLine();

        return new String[]{String.valueOf(age), name, email, password};
    }

    /**
     * Safely reads an integer from the user input.
     *
     * @return The integer entered by the user.
     */
    private static int safeReadInt() {
        int number;

        while (true) {
            String inputLine = input.nextLine();
            try {
                number = Integer.parseInt(inputLine);
                break;
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid input. Please enter a number.");
            }
        }
        return number;
    }

    /**
     * Checks if the given email is already used by an existing customer.
     *
     * @param email The email to check.
     * @return true if the email is already in use, false otherwise.
     */
    private static boolean isEmailUsed(String email) {
        if (findCustomer(email) != null) {
            System.out.println("This email is already registered.");
            return true;
        }
        return false;
    }

    /**
     * Handles the logout operation by clearing the order list.
     */
    public void logout() {
        clearOrderList();
    }
}
