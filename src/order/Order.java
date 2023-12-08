package order;
import product.Alcohol;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private static int orderNumber = 1;
    private final int ID;
    private final int customerID;
    private Map<Alcohol, Integer> purchasedProducts;
    private double total;

    public static int getOrderNumber() {
        return orderNumber;
    }

    public Order(int customerID){
        this.ID = orderNumber++;
        this.customerID = customerID;
        this.purchasedProducts = new HashMap<>();
    }

    public Order(int ID, int customerID, double total){
        this.ID = orderNumber++;
        this.customerID = customerID;
        this.total = total;
        this.purchasedProducts = new HashMap<>();
    }

    public Order(Order originalOrder){
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

    public void addProduct(Alcohol item, int quantity){
        purchasedProducts.put(item, quantity);
    }

    @Override
    public String toString(){
        String str = "Order #"+ ID + "\nCustomer #" + customerID + "\nTotal : "+ total + "\n";
        int counter = 1;
        for ( Alcohol item : purchasedProducts.keySet())
            str += counter++ + ". " + item + " x " + purchasedProducts.get(item) + "\n";
        return str;
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

    public String toFileString() {
        return ID + "," + total;
    }

    public Map<Alcohol, Integer> getPurchasedProducts() {
        return purchasedProducts;
    }
}
