package user;

import java.util.Objects;
import java.util.Scanner;

import static order.OrderService.clearOrderList;
import static order.OrderService.loadCustomerOrders;
import static user.CustomerService.addCustomer;
import static user.CustomerService.findCustomer;

public class AuthenticationService implements Authenticatable{

    private static Scanner input = new Scanner(System.in);
    public static Customer login() {
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

    private static String[] promptForEmailAndPassword() {
        System.out.println("Email: ");
        String email = input.nextLine();

        System.out.println("Password: ");
        String password = input.nextLine();

        return new String[]{email, password};
    }

    private static boolean verifyPassword(Customer customer, String password) {
        return Objects.equals(customer.getPassword(), password);
    }

    private static Customer retryLogin(Customer customer) {
        String password;
        do {
            System.out.println("Password: ");
            password = input.nextLine();
            if (Objects.equals(customer.getPassword(), password)) {
                loadCustomerOrders(customer.getID());
                return customer;
            }
            System.out.println("You entered the wrong password. Try again. (Enter to interrupt).");
            input.nextLine();
        } while (!password.equals(""));
        return null;
    }

    public static Customer register(){
        String[] credentials = registrationPrompt();
        if (credentials == null){
            return null;
        }

        int age = Integer.parseInt(credentials[0]);
        String name = credentials[1];
        String email = credentials[2];
        String password = credentials[3];

        if(isEmailUsed(email))
            return null;


        Customer newCustomer = new Customer(name, email, age, password);
        addCustomer(newCustomer);
        return newCustomer;
    }

    private static String[] registrationPrompt(){
        System.out.println("Enter your age: ");
        int age = input.nextInt();
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

    private static boolean isEmailUsed(String email){
        if(findCustomer(email) != null){
            System.out.println("This email is already registered.");
            return true;
        }
        return false;
    }
    public  static boolean logout(){
        clearOrderList();

        return true;
    }
}
