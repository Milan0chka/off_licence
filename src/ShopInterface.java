import order.Cart;
import order.Order;
import product.Alcohol;

/**
 * This interface defines the methods that a shop should implement.
 */
public interface ShopInterface {

    /**
     * Check if a customer is logged in.
     *
     * @return True if a customer is logged in, false otherwise.
     */
    boolean isLoggedIn();

    /**
     * Load information about products and customers.
     */
    void loadInformation();

    /**
     * Display a list of available products.
     */
    void showProducts();

    /**
     * Find a product by its ID.
     *
     * @param productID The ID of the product to find.
     * @return The found product or null if not found.
     */
    Alcohol findProduct(int productID);

    /**
     * Show detailed information about a product.
     *
     * @param product The product to display information about.
     */
    void showDetailedInfoAboutProducts(Alcohol product);

    /**
     * Log in a customer.
     */
    void logIn();

    /**
     * Register a new customer.
     */
    void register();

    /**
     * Add a product to the shopping cart.
     *
     * @param product The product to add.
     * @param qt      The quantity to add.
     */
    void addProductToCart(Alcohol product, int qt);

    /**
     * Clear the shopping cart.
     */
    void clearCart();

    /**
     * Remove a product from the shopping cart.
     *
     * @param product The product to remove.
     * @return True if the product was removed, false otherwise.
     */
    boolean removeProductFromCart(Alcohol product);

    /**
     * Update the quantity of a product in the shopping cart.
     *
     * @param product The product to update.
     * @param qt      The new quantity.
     * @return True if the quantity was updated, false if the product is not in the cart.
     */
    boolean updateQuantityInCart(Alcohol product, int qt);

    /**
     * Create an order from the items in the shopping cart.
     *
     * @return The created order.
     */
    Order createOrder();

    /**
     * Show a list of customer orders.
     *
     * @return The number of customer orders.
     */
    int showCustomerOrders();

    /**
     * Repeat a previous order.
     *
     * @param orderID The ID of the order to repeat.
     * @return The repeated order or null if the order does not exist.
     */
    Order repeatOrder(int orderID);

    /**
     * Log out the current customer.
     */
    void logOut();

}
