package order;

import product.Alcohol;

import java.util.HashMap;
import java.util.Map;

/**
 * The `Order` class represents an order placed by a customer, including the purchased products and total amount.
 */
public class Order {
    /**
     * Counter for customer orders
     */
    private static int orderNumber = 1;

    /**
     * The unique ID of the order.
     */
    private final int ID;

    /**
     * The unique ID of the customer placing the order.
     */
    private final int customerID;

    /**
     * A map that stores the purchased products and their quantities in the order.
     */
    private Map<Alcohol, Integer> purchasedProducts;

    /**
     * The total amount of the order.
     */
    private double total;

    /**
     * Creates a new order for a customer.
     *
     * @param customerID The unique ID of the customer placing the order.
     */
    public Order(int customerID) {
        this.ID = orderNumber++;
        this.customerID = customerID;
        this.purchasedProducts = new HashMap<>();
    }

    /**
     * Creates an order with the specified attributes.
     *
     * @param ID         The unique ID of the order.
     * @param customerID The unique ID of the customer placing the order.
     * @param total      The total amount of the order.
     */
    public Order(int ID, int customerID, double total) {
        this.ID = ID;
        this.customerID = customerID;
        this.total = total;
        this.purchasedProducts = new HashMap<>();
    }

    /**
     * Creates a new order based on an existing order.
     *
     * @param originalOrder The original order to be copied.
     */
    public Order(Order originalOrder) {
        this.ID = orderNumber++;
        this.customerID = originalOrder.getCustomerID();
        this.total = originalOrder.getTotal();

        if (originalOrder.purchasedProducts != null) {
            this.purchasedProducts = new HashMap<>();
            this.purchasedProducts.putAll(originalOrder.purchasedProducts);
        }
    }

    public static void setOrderNumber(int orderNumber) {
        Order.orderNumber = orderNumber;
    }

    public static int getOrderNumber() {
        return orderNumber;
    }

    public void addProduct(Alcohol item, int quantity) {
        purchasedProducts.put(item, quantity);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public int getID() {
        return ID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public Map<Alcohol, Integer> getPurchasedProducts() {
        return purchasedProducts;
    }

    @Override
    public String toString() {
        String str = "Order #" + ID + "\nCustomer #" + customerID + "\nTotal : " + total + "\n";

        int counter = 1;
        for (Alcohol item : purchasedProducts.keySet())
            str += counter++ + ". " + item + " x " + purchasedProducts.get(item) + "\n";

        return str;
    }
}
