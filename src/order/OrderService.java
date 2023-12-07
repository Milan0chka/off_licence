package order;
import product.Alcohol;
import java.util.Map;

public class OrderService {
    public static Order createOrderFromCart(Cart cart, int customerId) {
        Order newOrder = new Order(customerId);
        for (Map.Entry<Alcohol, Integer> entry : cart.getChosenItems().entrySet()) {
            newOrder.addItem(entry.getKey(), entry.getValue());
        }
        newOrder.setTotal(cart.getTotal());
        //saveOrderToFile(newOrder);
        cart.clearCart();
        return newOrder;
    }

    public static void saveOrderToFile(Order order, int customerId){
        return;
    }
}
