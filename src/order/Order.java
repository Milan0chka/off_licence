package order;
import product.Alcohol;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private static int orderNumber = 1;
    private final int ID;
    private final int customerID;
    private Map<Alcohol, Integer> purchasedItems;
    private double total;

    public Order(int customerID){
        this.ID = orderNumber++;
        this.customerID = customerID;
        this.purchasedItems = new HashMap<>();
    }

    public Order(Order originalOrder){
        this.ID = orderNumber++;
        this.customerID = originalOrder.getCustomerID();
        this.total = originalOrder.getTotal();

        if (originalOrder.purchasedItems != null) {
            this.purchasedItems = new HashMap<>();
            this.purchasedItems.putAll(originalOrder.purchasedItems);
        }
    }


    public void addItem(Alcohol item, int quantity){
        purchasedItems.put(item, quantity);
    }

    @Override
    public String toString(){
        String str = "Order #"+ ID + "\nCustomer #" + customerID + "\nTotal : "+ total + "\n";
        int counter = 1;
        for ( Alcohol item : purchasedItems.keySet())
            str += counter++ + ". " + item + " x " + purchasedItems.get(item) + "\n";
        return str;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public static int getOrderNumber() {
        return orderNumber;
    }

    public static void setOrderNumber(int orderNumber) {
        Order.orderNumber = orderNumber;
    }

    public int getID() {
        return ID;
    }

    public int getCustomerID() {
        return customerID;
    }

}
