package order;
import product.Alcohol;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static order.Order.getOrderNumber;
import static order.Order.setOrderNumber;
import static product.ProductService.findProduct;

public class OrderService {

    private static List<Order> customerOrderList = new ArrayList<>();

    public static void loadCustomerOrders(int customerID){
        String filename = "src/database/orders/orders_" + customerID + ".txt";
        File file = new File(filename);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Order order = parseOrderLine(line, customerID);
                    if (order != null) {
                        customerOrderList.add(order);
                    }
                }
                setOrderNumber(customerOrderList.size() +1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Order parseOrderLine(String line, int customerID) {
        String[] parts = line.split(", ");
        int orderId = Integer.parseInt(parts[0].trim());
        double orderTotal = Double.parseDouble(parts[1].trim());

        Order order = new Order(orderId, customerID, orderTotal);

        // Starting from index 2, each pair of elements represents a product and its quantity
        for (int i = 2; i < parts.length; i += 2) {
            int productId = Integer.parseInt(parts[i].trim());
            int quantity = Integer.parseInt(parts[i + 1].trim());
            order.addProduct(findProduct(productId), quantity);
        }
        return order;
    }

    public static Order createOrderFromCart(Cart cart, int customerId) {
        Order newOrder = new Order(customerId);
        for (Map.Entry<Alcohol, Integer> entry : cart.getChosenItems().entrySet()) {
            newOrder.addProduct(entry.getKey(), entry.getValue());
        }
        newOrder.setTotal(cart.getTotal());
        saveOrderToFile(newOrder);
        cart.clearCart();
        customerOrderList.add(newOrder);
        return newOrder;
    }

    public static Order repeatOrder(int orderID){
        Order oldOrder = null;
        for (Order order : customerOrderList)
            if (order.getID() == orderID){
                oldOrder = order;
                break;
            }

        if (oldOrder == null)
            return null;

        Order newOrder = new Order(oldOrder);
        saveOrderToFile(newOrder);
        customerOrderList.add(newOrder);
        return newOrder;
    }

    public static void saveOrderToFile(Order order) {
        String filename = "src/database/orders/orders_" + order.getCustomerID() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            String orderLine = constructOrderLine(order);
            writer.write(orderLine);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions here
        }
    }

    private static String constructOrderLine(Order order) {
        StringBuilder line = new StringBuilder();
        line.append(order.getID()).append(", ");
        line.append(order.getTotal());

        for (Map.Entry<Alcohol, Integer> entry : order.getPurchasedProducts().entrySet()) {
            line.append(", ").append(entry.getKey().getID()).append(", ").append(entry.getValue());
        }

        return line.toString();
    }

    public static int showCustomerOrders(){
        if ( customerOrderList.size() == 0){
            System.out.println("No orders found.");
            return 0;
        }
        for(int i=0; i < customerOrderList.size(); i++){
            System.out.println((i+1)+"."+ customerOrderList.get(i));
        }
        return customerOrderList.size();
    }

    public static void clearOrderList(){
        customerOrderList.clear();
    }

}
