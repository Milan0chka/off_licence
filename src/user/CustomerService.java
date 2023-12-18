package user;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static user.Customer.setCustomerNumber;

/**
 * Handles the storage and retrieval of customer data.
 * It provides functionality to load, add, and find customers,
 * and to interact with the customer data file.
 */
public class CustomerService {
    /**
     * List of registered customers.
     */
    private static final List<Customer> customers = new ArrayList<>();
    private static CustomerService instance;

    /**
     * Private constructor to enforce the Singleton pattern.
     */
    private CustomerService() {
    }

    /**
     * Gets the singleton instance of the CustomerService class. If an instance
     * does not exist, it creates one. Subsequent calls return the existing instance.
     *
     * @return The CustomerService instance.
     */
    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    /**
     * Loads customers from a file into the application.
     * Sets the next customer number based on the loaded data.
     */
    public void loadCustomers() {
        readCustomersFromFile();
        setCustomerNumber(customers.size() + 1);
    }

    /**
     * Reads customer data from a file and adds them to the customers list.
     */
    private void readCustomersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("database/customers.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] details = line.split(", ");
                Customer customer = new Customer(Integer.parseInt(details[0]), details[1],
                        details[2], Integer.parseInt(details[3]), details[4]);
                customers.add(customer);
            }

        } catch (IOException e) {
            System.out.println("Oops, problem occur. Cant reed information about customers.");
            e.printStackTrace();
        }
    }

    /**
     * Adds a new customer to the customer list and writes their data to a file.
     *
     * @param customer The customer to be added.
     */
    protected static void addCustomer(Customer customer) {
        customers.add(customer);
        writeCustomerToFile(customer);
    }

    /**
     * Writes a customer's data to the customer file.
     *
     * @param customer The customer whose data is to be written to the file.
     */
    private static void writeCustomerToFile(Customer customer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("database/customers.txt", true))) {
            writer.write(customer.toFileString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Oops, problem occur. Can save customer into file.");
            e.printStackTrace();
        }
    }

    /**
     * Finds and returns a customer based on their email address.
     *
     * @param email The email address of the customer to be found.
     * @return The customer if found, or null if no customer matches the email address.
     */
    protected static Customer findCustomer(String email) {
        for (Customer customer : customers)
            if (email.equals(customer.getEmail()))
                return customer;
        return null;
    }

}
