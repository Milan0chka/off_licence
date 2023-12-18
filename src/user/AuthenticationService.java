package user;

import java.util.Scanner;

/**
 * Handles authentication processes including login, registration, and logout for the application.
 */
public class AuthenticationService {
    private static final Scanner input = new Scanner(System.in);
    private static AuthenticationService instance;

    /**
     * Private constructor to enforce the Singleton pattern.
     */
    private AuthenticationService() {
    }

    /**
     * Gets the singleton instance of the AuthenticationService class. If an instance
     * does not exist, it creates one. Subsequent calls return the existing instance.
     *
     * @return The AuthenticationService instance.
     */
    public static AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    /**
     * Performs the login operation for a customer.
     *
     * @return The logged-in Customer object or null if the login fails.
     */
    public Customer login() {
        // Prompt for email and password
        String[] credentials = promptForEmailAndPassword();
        String email = credentials[0];
        String password = credentials[1];

        // Find the customer by email
        Customer customer = CustomerService.findCustomer(email);

        if (customer == null) {
            System.out.println("No customer with this email is found");
            return null;
        }

        // Verify the entered password
        if (verifyPassword(customer, password)) {
            System.out.println("Log in successfully.");
            return customer;
        } else {
            // Retry login in case of incorrect password
            return retryLogin(customer);
        }
    }

    /**
     * Prompts for and reads the user's email and password.
     *
     * @return An array of Strings containing the email and password.
     */
    private String[] promptForEmailAndPassword() {
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
    private boolean verifyPassword(Customer customer, String password) {
        return password.equals(customer.getPassword());
    }

    /**
     * Provides the user with the option to retry logging in after an incorrect password entry.
     *
     * @param customer The customer attempting to log in.
     * @return The Customer object if login is successful, null otherwise.
     */
    private Customer retryLogin(Customer customer) {
        String password;

        do {
            System.out.println("You entered the wrong password. Try again. (Enter empty line to interrupt).");
            System.out.println("Password: ");
            password = input.nextLine();

            if (password.equals(customer.getPassword()))
                return customer;

        } while (!password.equals(""));

        return null;
    }

    /**
     * Handles the customer registration process.
     *
     * @return The newly registered Customer object or null if registration fails.
     */
    public Customer register() {
        // Prompt the user for registration details
        String[] credentials = registrationPrompt();

        if (credentials == null)
            return null;

        // Extract registration details
        int age = Integer.parseInt(credentials[0]);
        String name = credentials[1];
        String email = credentials[2];
        String password = credentials[3];

        // Check if the email is already in use
        if (isEmailUsed(email))
            return null;

        // Create a new Customer and add it to the customer list
        Customer newCustomer = new Customer(name, email, age, password);
        CustomerService.addCustomer(newCustomer);

        return newCustomer;
    }

    /**
     * Prompts the user for registration details including age, name, email, and password.
     *
     * @return An array of Strings containing the registration details.
     */
    private String[] registrationPrompt() {
        System.out.println("Enter your age: ");
        int age = safeReadInt();

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
    private int safeReadInt() {
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
    private boolean isEmailUsed(String email) {
        if (CustomerService.findCustomer(email) != null) {
            System.out.println("This email is already registered.");
            return true;
        }
        return false;
    }

    /**
     * Handles the logout operation by clearing the order list.
     */
    public void logout() {
        System.out.println("\nYou have successfully logged out.");
    }
}
