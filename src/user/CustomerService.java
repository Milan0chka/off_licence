package user;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CustomerService {
    private static List<Customer> customers = new ArrayList<>();

    public static void loadCustomers(){
        readCustomersFromFile();
        Customer.setCustomerNumber(customers.size() + 1);
    }

    private static void readCustomersFromFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader("src/database/customers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(", ");
                Customer customer = new Customer(Integer.parseInt(details[0]), details[1], details[2], Integer.parseInt(details[3]), details[4]);
                customers.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addCustomer(Customer customer){
        customers.add(customer);
        writeCustomerToFile(customer);
    }

    private static void writeCustomerToFile(Customer customer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/database/customers.txt", true))) {
            writer.write(customer.toFileString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Customer findCustomer(String email){
        for ( Customer customer : customers)
            if(Objects.equals(customer.getEmail(), email))
                return customer;
        return null;
    }

}
