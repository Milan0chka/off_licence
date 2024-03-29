
import order.*;
import product.Alcohol;
import product.ProductService;
import user.AuthenticationService;
import user.Customer;
import user.CustomerService;

/**
 * The Shop class represents an off-licence shop where customers can browse and purchase products.
 */
public class Shop implements ShopInterface {
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderService orderService;
    private final AuthenticationService authenticationService;
    private final Cart cart;
    private Customer customer;

    /**
     * Constructs a new Shop instance. Initializes various services and data structures
     * required for managing customer interactions, authentication, product catalog,
     * shopping carts, and order history. Initially, there is no logged-in customer.
     */
    public Shop() {
        this.customer = null;
        this.cart = Cart.getInstance();
        this.customerService = CustomerService.getInstance();
        this.authenticationService = AuthenticationService.getInstance();
        this.productService = ProductService.getInstance();
        this.orderService = OrderService.getInstance();
    }


    /**
     * Check if a customer is logged in.
     *
     * @return True if a customer is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return customer != null;
    }


    public Cart getCart() {
        return cart;
    }

    /**
     * Load information about products and customers.
     */
    public void loadInformation() {
        productService.loadProducts();
        customerService.loadCustomers();
    }

    /**
     * Display a list of available products.
     */
    public void showProducts() {
        productService.showProducts();
    }

    /**
     * Find a product by its ID.
     *
     * @param productID The ID of the product to find.
     * @return The found product or null if not found.
     */
    public Alcohol findProduct(int productID) {
        return ProductService.findProduct(productID);
    }

    /**
     * Find a product by its name.
     *
     * @param productName The name of the product to find.
     * @return The found product or null if not found.
     */
    public Alcohol findProduct(String productName) {
        return ProductService.findProduct(productName);
    }

    /**
     * Show detailed information about a product.
     *
     * @param product The product to display information about.
     */
    public void showDetailedInfoAboutProducts(Alcohol product) {
        productService.showDetailedInformationAboutProduct(product);
    }

    /**
     * Log in a customer.
     */
    public void logIn() {
        this.customer = authenticationService.login();
        if (customer != null)
            orderService.loadCustomerOrders(customer.getID());
    }

    /**
     * Register a new customer.
     */
    public void register() {
        this.customer = authenticationService.register();
    }

    /**
     * Add a product to the shopping cart.
     *
     * @param product The product to add.
     * @param qt      The quantity to add.
     */
    public void addProductToCart(Alcohol product, int qt) {
        cart.addProduct(product, qt);
    }

    /**
     * Clear the shopping cart.
     */
    public void clearCart() {
        cart.clearCart();
    }

    /**
     * Remove a product from the shopping cart.
     *
     * @param product The product to remove.
     * @return True if the product was removed, false otherwise.
     */
    public boolean removeProductFromCart(Alcohol product) {
        return cart.removeProduct(product);
    }

    /**
     * Update the quantity of a product in the shopping cart.
     *
     * @param product The product to update.
     * @param qt      The new quantity.
     * @return True if the quantity was updated, false if the product is not in the cart.
     */
    public boolean updateQuantityInCart(Alcohol product, int qt) {
        return cart.updateQuantity(product, qt);
    }

    /**
     * Create an order from the items in the shopping cart.
     *
     * @return The created order.
     */
    public Order createOrder() {
        return orderService.createOrderFromCart(cart, customer.getID());
    }

    /**
     * Show a list of customer orders.
     *
     * @return The number of customer orders.
     */
    public int showCustomerOrders() {
        return orderService.showCustomerOrders();
    }

    /**
     * Repeat a previous order.
     *
     * @param orderID The ID of the order to repeat.
     * @return The repeated order or null if the order does not exist.
     */
    public Order repeatOrder(int orderID) {
        return orderService.repeatOrder(orderID);
    }

    /**
     * Log out the current customer.
     */
    public void logOut() {
        cart.clearCart();
        orderService.clearOrderList();
        customer = null;
        authenticationService.logout();
    }

}
