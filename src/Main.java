import order.*;
import product.*;
import user.Authenticatable;
import user.AuthenticationService;
import user.Customer;

import java.util.Scanner;

import static order.OrderService.*;
import static product.ProductService.*;
import static user.AuthenticationService.*;
import static user.CustomerService.loadCustomers;

/**
 * The Main class serves as the entry point for the off-licence shop application.
 * It handles user interactions and navigates through different functionalities
 * of the application based on user input.
 */
public class Main {

    /**
     * Scanner for reading input from the console.
     */
    private static final Scanner input = new Scanner(System.in);
    /**
     * The currently logged-in customer, or null if no user is logged in.
     */
    private static Customer user;
    /**
     * The shopping cart associated with the current user session.
     */
    private static final Cart cart = new Cart();

    /**
     * The main method that starts the execution of the application.
     * It loads necessary information and displays the main menu.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        loadInformation();

        while (true) {

            if (user == null)
                switch (menu()) {
                    case 1 -> viewListOfProducts();
                    case 2 -> searchProductByID();
                    case 3 -> logIn();
                    case 4 -> registerAccount();
                    case 5 -> {
                        System.out.println("\nSee you later!");
                        return;
                    }
                }

            else
                switch (loggedMenu()) {
                    case 1 -> viewListOfProducts();
                    case 2 -> searchProductByID();
                    case 3 -> openCart();
                    case 4 -> viewOrderHistory();
                    case 5 -> logOut();
                    case 6 -> {
                        System.out.println("\nSee you later!");
                        return;
                    }
                    default -> {
                        System.out.println("Unavailable option chosen.");
                        delay();
                    }
                }

        }
    }

    /**
     * Loads necessary information for the application to start.
     * This involves loading products and customers.
     */
    private static void loadInformation() {
        loadProducts();
        loadCustomers();
    }

    /**
     * Displays the menu for logged-in users and returns the user's choice.
     *
     * @return The choice selected by the user.
     */
    private static int loggedMenu() {
        System.out.println("\t\t* OFF-LICENCE SHOP *");
        printSeparator();
        System.out.println("\nMENU :");
        System.out.println("""
                1. View list of products
                2. Search for a product by ID
                3. Open cart
                4. Order history
                5. Log out
                6. Exit""");

        printSeparator();


        return safeReadInt("=>");
    }

    /**
     * Displays the main menu for non-logged-in users and returns the user's choice.
     *
     * @return The choice selected by the user.
     */
    private static int menu() {
        System.out.println("\t\t* OFF-LICENCE SHOP *");
        printSeparator();

        System.out.println("\nMenu :");
        System.out.println("""
                1. View list of products
                2. Search for a product by ID
                3. Log in
                4. Register
                5. Exit""");

        printSeparator();

        return safeReadInt("=>");
    }

    /**
     * Displays a list of available products to the user.
     */
    private static void viewListOfProducts() {
        System.out.println("\nLIST OF AVAILABLE PRODUCTS :");
        showProducts();
        delay();
    }

    /**
     * Searches for a product by its ID and allows the user to add it to their cart.
     */
    private static void searchProductByID() {
        System.out.println("\nSEARCH PRODUCT BY ID : ");


        int productID = safeReadInt("Enter productID => ");

        Alcohol product = findProduct(productID);

        if (product == null)
            System.out.println("No product with ID " + productID + " was found.");
        else {
            System.out.println("\nProduct with ID " + productID + " : \n");
            showDetailedInformationAboutProduct(findProduct(productID));


            if (user != null) {
                System.out.println("""

                        Do you wish to add product in cart?
                        y/n?
                        =>""");

                String response = input.next();
                if (response.equals("y")) {
                    int qt = safeReadInt("Enter a quantity => ");
                    cart.addProduct(product, qt);
                }
            }
        }
        delay();
    }

    /**
     * Handles the login process for the user.
     * If successful, it updates the global 'user' variable with the logged-in user.
     */
    private static void logIn() {
        System.out.println("\t\t* LOGIN * ");

        AuthenticationService login = new AuthenticationService();
        user = login.login();

        if (user != null)
            System.out.println("Welcome " + user.getFullName());

        delay();
    }

    /**
     * Handles the account registration process for new users.
     * If successful, it updates the global 'user' variable with the newly created user.
     */
    private static void registerAccount() {
        System.out.println("\t\t* REGISTRATION * ");

        AuthenticationService registration = new AuthenticationService();
        user = registration.register();

        if (user != null)
            System.out.println("We are happy that you joined us, " + user.getFullName() + "!");

        delay();
    }

    /**
     * Opens and manages the user's shopping cart.
     * Allows the user to add, delete, or change the quantity of products,
     * clear the cart, or place an order.
     */
    private static void openCart() {
        while (true) {
            System.out.println("\nCART : \n" + cart);

            switch (cartPrompt()) {
                case 1 -> addProductToCart();
                case 2 -> changeQuantityInCart();
                case 3 -> deleteProductFromCart();
                case 4 -> clearCart();
                case 5 -> placeOrder();
                case 6 -> {
                    return;
                }
                default -> System.out.println("Unavailable option chosen.");
            }

        }

    }

    /**
     * Displays the cart menu and returns the user's choice.
     *
     * @return The choice selected by the user.
     */
    private static int cartPrompt() {
        printSeparator();
        System.out.println("""
                CART MENU :
                1. Add products to the cart
                2. Change products quantity
                3. Delete products from the cart
                4. Clear the cart
                5. Place an order
                6. Exit""");

        printSeparator();

        return safeReadInt("=>");
    }

    /**
     * Provides the functionality to add products to the user's cart.
     * It prompts the user for the product ID and quantity and adds the product to the cart.
     */
    private static void addProductToCart() {
        System.out.println("\nADDING TO CART :");

        while (true) {
            int productID = safeReadInt("Enter ID of product (Enter 0 to finish) =>");

            if (productID == 0)
                return;

            Alcohol product = findProduct(productID);

            if (product == null)
                System.out.println("No product with ID " + productID + " was found.");
            else {
                int qt = safeReadInt("Enter quantity =>");
                cart.addProduct(product, qt);

                System.out.println();
            }

        }
    }

    /**
     * Clears all items from the user's cart after confirming with the user.
     */
    private static void clearCart() {
        System.out.println("""
                Are you sure you want to clear the cart?
                y/n?
                =>""");

        String response = input.next();
        if (response.equals("y"))
            cart.clearCart();
    }

    /**
     * Allows the user to delete a specific product from the cart.
     * It prompts for the product ID and removes the product if it exists in the cart.
     */
    private static void deleteProductFromCart() {
        System.out.println("\nDELETING FROM CART :");

        while (true) {
            int productID = safeReadInt("Enter ID of product (Enter 0 to finish) =>");

            if (productID == 0)
                return;

            Alcohol product = findProduct(productID);

            if (product == null)
                System.out.println("No product with ID " + productID + " was found.");

            else if (cart.removeProduct(product))
                System.out.println("Product " + product + " was deleted from cart.");

            else
                System.out.println("Product " + product + " is not in a cart.");
        }
    }

    /**
     * Allows the user to change the quantity of a specific product in the cart.
     * It prompts for the product ID and the new quantity and updates the cart accordingly.
     */
    private static void changeQuantityInCart() {
        System.out.println("\nCHANGING AN QUANTITY :");

        while (true) {
            int productID = safeReadInt("Enter ID of product (Enter 0 to finish) =>");

            if (productID == 0)
                return;

            Alcohol product = findProduct(productID);

            if (product == null)
                System.out.println("No product with ID " + productID + " was found.\n");
            else {

                int qt = safeReadInt("Enter quantity =>");

                if (cart.updateQuantity(product, qt))
                    System.out.println("Product " + product + " quantity was changed.\n");
                else
                    System.out.println("Product " + product + " is not in a cart.\n");
            }


        }
    }

    /**
     * Handles the process of placing an order with the items in the user's cart.
     * It creates a new order from the cart contents and displays the order details.
     */
    private static void placeOrder() {
        System.out.println("\nPLACING OF THE ORDER :");

        if (cart.isEmpty()) {
            System.out.println("Cart in empty. Impossible to place an order");

            delay();
            return;
        }

        Order newOrder = createOrderFromCart(cart, user.getID());
        System.out.println("Order has successfully placed!\n" + newOrder);

        delay();
    }

    /**
     * Displays the order history for the current user and provides an option to repeat an order.
     */
    private static void viewOrderHistory() {
        System.out.println("\nORDER HISTORY :");

        int ordersNumber = showCustomerOrders();

        if (ordersNumber != 0)
            if (promptConfirmation("\n\nDo you wish to repeat some order?"))
                repeatCustomerOrder();

        delay();
    }

    /**
     * Allows the user to repeat an existing order by specifying the order ID.
     * Displays a message indicating whether the order repetition was successful.
     */
    private static void repeatCustomerOrder() {
        Order newOrder = repeatOrder(
                safeReadInt("Enter ID of the order that you want to repeat => "));

        if (newOrder == null)
            System.out.println("Oops, something went wrong. Order was not repeated.");
        else
            System.out.println("Order has successfully placed!\n" + newOrder);
    }

    /**
     * Logs the user out of the application after confirming their intent.
     * Clears the user's cart upon successful logout.
     */
    private static void logOut() {
        if (promptConfirmation("\n\nAre you sure you want to log out?")) {
            cart.clearCart();

            AuthenticationService logout = new AuthenticationService();
            logout.logout();

            user = null;
        }
    }

    /**
     * Safely reads an integer input from the user.
     * Prompts the user with the specified message and validates the input.
     *
     * @param prompt The message displayed to prompt the user for input.
     * @return The integer input by the user.
     */
    private static int safeReadInt(String prompt) {
        int number;

        while (true) {
            System.out.print(prompt);
            String inputLine = input.nextLine(); // Use nextLine to handle incorrect inputs better

            try {
                number = Integer.parseInt(inputLine);
                break;
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid input. Please enter a number.");
            }
        }
        return number;
    }

    /**
     * Prompts the user for a confirmation response (yes or no).
     *
     * @param promptMessage The message displayed to prompt the user for confirmation.
     * @return true if the user responds with 'y', false otherwise.
     */
    private static boolean promptConfirmation(String promptMessage) {
        System.out.print("\n" + promptMessage + "\ny/n ? => ");
        String response = input.nextLine().trim().toLowerCase();
        return response.equals("y");
    }

    /**
     * Prints a separator line to the console for UI formatting purposes.
     */
    private static void printSeparator() {
        for (int i = 0; i < 40; i++) {
            System.out.print("~");
        }
        System.out.println();
    }

    /**
     * Delays the execution until the user presses the Enter key.
     * Used to pause the program and allow the user to read the displayed information.
     */
    private static void delay() {
        System.out.println("\n\nPress Enter to continue ...");
        input.nextLine();
    }

}
