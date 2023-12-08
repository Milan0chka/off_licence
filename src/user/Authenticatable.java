package user;

/**
 * Defines the contract for authentication services.
 */
public interface Authenticatable {
    /**
     * Performs the login operation and returns the logged-in customer.
     *
     * @return The logged-in Customer object, or null if login fails.
     */
    Customer login();

    /**
     * Handles the registration of a new customer.
     *
     * @return The newly registered Customer object, or null if registration fails.
     */
    Customer register();

    /**
     * Performs the logout operation.
     */
    void logout();
}
