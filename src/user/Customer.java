package user;

/**
 * Represents a customer in the off-licence shop application.
 * It stores customer details such as name, email, age, and password.
 */
public class Customer {
    private static int customerNumber = 1;
    /**
     * Unique identifier for the customer.
     */
    private final int ID;
    /**
     * Full name of the customer.
     */
    private final String fullName;
    /**
     * Email address of the customer.
     */
    private String email;
    /**
     * Age of the customer.
     */
    private int age;
    /**
     * Password for the customer's account.
     */
    private String password;

    /**
     * Constructs a new Customer after registration.
     *
     * @param fullName The full name of the customer.
     * @param email    The email address of the customer.
     * @param age      The age of the customer.
     * @param password The password for the customer's account.
     */
    public Customer(String fullName, String email, int age, String password) {
        ID = customerNumber++;
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    /**
     * Constructs a new Customer from a file.
     *
     * @param ID       The unique identifier for the customer.
     * @param fullName The full name of the customer.
     * @param email    The email address of the customer.
     * @param age      The age of the customer.
     * @param password The password for the customer's account.
     */
    public Customer(int ID, String fullName, String email, int age, String password) {
        this.ID = ID;
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    public static int getCustomerNumber() {
        return customerNumber;
    }

    public static void setCustomerNumber(int customerNumber) {
        Customer.customerNumber = customerNumber;
    }

    public int getID() {
        return ID;
    }


    public String getFullName() {
        return fullName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a string representation of the customer suitable for file storage.
     *
     * @return A string containing customer details in a file-friendly format.
     */
    public String toFileString() {
        return ID + ", " + fullName + ", " + email + ", " + age + ", " + password;
    }

}
