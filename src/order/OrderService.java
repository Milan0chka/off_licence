package order;

import product.Alcohol;
import product.ProductService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The `OrderService` class manages customer orders, including loading, creating, repeating, and saving orders to files.
 */
public class OrderService {

    /**
     * A list that stores customer orders.
     */
    private static final List<Order> customerOrderList = new ArrayList<>();
    private static OrderService instance;

    /**
     * Private constructor to enforce the Singleton pattern.
     */

    private OrderService() {
    }

    /**
     * Gets the singleton instance of the OrderService class. If an instance
     * does not exist, it creates one. Subsequent calls return the existing instance.
     *
     * @return The OrderService instance.
     */
    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    /**
     * Loads customer orders from a file and populates the order list.
     * Sets the next order number based on the loaded data.
     *
     * @param customerID The ID of the customer for whom orders are loaded.
     */
    public void loadCustomerOrders(int customerID) {
        readCustomerOrdersFromFile(customerID);
        Order.setOrderNumber(customerOrderList.size() + 1);
    }

    private void readCustomerOrdersFromFile(int customerID) {
        String filename = "database/orders/orders_" + customerID + ".txt";
        File file = new File(filename);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    Order order = parseOrderLine(line, customerID);

                    if (order != null)
                        customerOrderList.add(order);
                }

            } catch (IOException e) {
                System.out.println("Oops, problem occur during reading your order history.");
                e.printStackTrace();

            }
        }
    }

    /**
     * Parses a line of order data from a file.
     *
     * @param line       The line of order data.
     * @param customerID The ID of the customer associated with the order.
     * @return The parsed Order object, or null if parsing fails.
     */
    private Order parseOrderLine(String line, int customerID) {
        if (line == null || line.trim().isEmpty())
            return null;


        String[] parts = line.split(", ");
        int orderId = Integer.parseInt(parts[0].trim());
        double orderTotal = Double.parseDouble(parts[1].trim());

        Order order = new Order(orderId, customerID, orderTotal);

        // Starting from index 2, each pair of elements represents a product and its quantity
        for (int i = 2; i < parts.length; i += 2) {
            int productId = Integer.parseInt(parts[i].trim());
            int quantity = Integer.parseInt(parts[i + 1].trim());

            order.addProduct(ProductService.findProduct(productId), quantity);
        }

        return order;
    }

    /**
     * Creates an order from the items in a customer's cart and saves it to a file.
     *
     * @param cart       The customer's shopping cart.
     * @param customerId The ID of the customer placing the order.
     * @return The newly created Order object.
     */
    public Order createOrderFromCart(Cart cart, int customerId) {
        if (cart.isEmpty())
            return null;

        Order newOrder = new Order(customerId);

        for (Map.Entry<Alcohol, Integer> entry : cart.getChosenProducts().entrySet())
            newOrder.addProduct(entry.getKey(), entry.getValue());


        newOrder.setTotal(cart.getTotal());
        saveOrderToFile(newOrder);
        customerOrderList.add(newOrder);

        cart.clearCart();

        return newOrder;
    }

    /**
     * Repeats a previous order and saves it as a new order.
     *
     * @param orderID The ID of the order to repeat.
     * @return The newly created Order object, or null if the original order does not exist.
     */
    public Order repeatOrder(int orderID) {
        Order oldOrder = findOrder(orderID);

        if (oldOrder == null)
            return null;

        Order newOrder = new Order(oldOrder);

        saveOrderToFile(newOrder);
        customerOrderList.add(newOrder);

        return newOrder;
    }

    /**
     * Looks for the order with same orderID, as passed into function.
     *
     * @param orderID ID of a searched order
     * @return found order, or null if it is now found
     */
    private Order findOrder(int orderID) {
        for (Order order : customerOrderList)
            if (order.getID() == orderID)
                return order;

        return null;
    }

    /**
     * Saves an order to a file.
     *
     * @param order The Order object to be saved.
     */
    private void saveOrderToFile(Order order) {
        String filename = "database/orders/orders_" + order.getCustomerID() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {

            String orderLine = constructOrderLine(order);
            writer.write(orderLine);

            writer.newLine();
        } catch (IOException e) {
            System.out.println("The problem with saving order occurred. Please repeat order later.");
            e.printStackTrace();
        }
    }

    /**
     * Constructs a line of order data to be saved to a file.
     *
     * @param order The Order object to be converted to a string.
     * @return A string containing order data.
     */
    private String constructOrderLine(Order order) {
        StringBuilder line = new StringBuilder();
        line.append(order.getID()).append(", ");
        line.append(order.getTotal());

        // Iterate through purchased products and append their IDs and quantities to the line.
        for (Map.Entry<Alcohol, Integer> entry : order.getPurchasedProducts().entrySet()) {
            line.append(", ").append(entry.getKey().getID()).append(", ").append(entry.getValue());
        }

        return line.toString();
    }


    /**
     * Displays the list of customer orders.
     *
     * @return The number of customer orders displayed.
     */
    public int showCustomerOrders() {
        if (customerOrderList.size() == 0)
            return 0;

        for (int i = 0; i < customerOrderList.size(); i++)
            System.out.println((i + 1) + "." + customerOrderList.get(i));

        return customerOrderList.size();
    }

    /**
     * Clears the list of customer orders.
     */
    public void clearOrderList() {
        // Clear the list of customer orders and reset the order number.
        customerOrderList.clear();
        Order.setOrderNumber(1);
    }

}
