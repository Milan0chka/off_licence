import order.*;
import product.*;
import user.Customer;

import java.util.Scanner;

import static order.OrderService.*;
import static product.ProductService.*;
import static user.AuthenticationService.*;
import static user.CustomerService.loadCustomers;

public class Main {

    private static final Scanner input = new Scanner(System.in);
    private static Customer user;
    private static final Cart cart = new Cart();

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

    private static void loadInformation() {
        loadProducts();
        loadCustomers();
    }

    private static int loggedMenu() {
        System.out.println("\t\t* OFF-LICENCE SHOP *");
        printSeparator();

        System.out.println("\nMenu :");
        System.out.println("""
                1. View list of products
                2. Search for a product by ID
                3. Open cart
                4. Order history
                5. Log out
                6. Exit""");

        printSeparator();
        System.out.println("=>");

        return safeReadInt(input.nextLine());
    }

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
        System.out.println("\n=>");

        return safeReadInt(input.nextLine());
    }

    private static void viewListOfProducts() {
        System.out.println("\nLIST OF AVAILABLE PRODUCTS :");
        showProducts();
        delay();
    }

    private static void searchProductByID() {
        System.out.println("\nSEARCH PRODUCT BY ID : ");

        System.out.println("Enter productID => ");
        int productID = safeReadInt(input.nextLine());

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

                    System.out.println("Enter a quantity => ");
                    int qt = safeReadInt(input.nextLine());

                    cart.addItem(product, qt);
                }
            }
        }
        delay();
    }

    private static void logIn() {
        System.out.println("\t\t* LOGIN * ");

        user = login();
        if (user != null)
            System.out.println("Welcome " + user.getFullName());

        delay();
    }

    private static void registerAccount() {
        System.out.println("\t\t* REGISTRATION * ");

        user = register();
        if (user != null)
            System.out.println("We are happy that you joined us, " + user.getFullName() + "!");

        delay();
    }

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
        System.out.println("=>");

        return safeReadInt(input.nextLine());
    }

    private static void addProductToCart() {
        System.out.println("\nADDING TO CART :");

        while (true) {
            System.out.println("Enter ID of product (Enter 0 to finish) =>");
            int productID = safeReadInt(input.nextLine());

            if (productID == 0)
                return;

            Alcohol product = findProduct(productID);

            if (product == null)
                System.out.println("No product with ID " + productID + " was found.");
            else {
                System.out.println("Enter quantity =>");
                int qt = safeReadInt(input.nextLine());

                cart.addItem(product, qt);

                System.out.println();
            }

        }
    }

    private static void clearCart() {
        System.out.println("""
                Are you sure you want to clear the cart?
                y/n?
                =>""");

        String response = input.next();
        if (response.equals("y"))
            cart.clearCart();
    }

    private static void deleteProductFromCart() {
        System.out.println("\nDELETING FROM CART :");

        while (true) {
            System.out.println("Enter ID of product (Enter 0 to finish) =>");
            int productID = safeReadInt(input.nextLine());

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

    private static void changeQuantityInCart() {
        System.out.println("\nCHANGING AN QUANTITY :");

        while (true) {
            System.out.println("Enter ID of product (Enter 0 to finish) =>");
            int productID = safeReadInt(input.nextLine());

            if (productID == 0)
                return;

            Alcohol product = findProduct(productID);

            if (product == null)
                System.out.println("No product with ID " + productID + " was found.");
            else {

                System.out.println("Enter quantity =>");
                int qt = safeReadInt(input.nextLine());

                if (cart.updateQuantity(product, qt))
                    System.out.println("Product " + product + " quantity was changed.");
                else
                    System.out.println("Product " + product + " is not in a cart.");
            }


        }
    }

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

    private static void viewOrderHistory() {
        System.out.println("\nORDER HISTORY :");

        int ordersNumber = showCustomerOrders();

        if (ordersNumber != 0) {
            System.out.println("""

                    Do you wish to repeat some order?
                    y/n?
                    =>""");

            String response = input.next();

            if (response.equals("y"))
                repeatCustomerOrder();

        }
        delay();
    }

    private static void repeatCustomerOrder() {
        System.out.println("Enter ID of the order that you want to repeat =>");
        Order newOrder = repeatOrder(
                safeReadInt(
                        input.nextLine()));

        if (newOrder == null)
            System.out.println("Oops, something went wrong. Order was not repeated.");
        else
            System.out.println("Order has successfully placed!\n" + newOrder);
    }

    private static void logOut() {
        System.out.println("""

                Are you sure you want to log out?
                y/n ?
                =>""");

        String response = input.next();
        if (response.equals("y")) {
            cart.clearCart();
            logout();

            user = null;
        }
    }

    private static void delay() {
        System.out.println("\n\nPress Enter to continue ...");
        input.nextLine();
        input.nextLine();
    }

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

    private static void printSeparator() {
        for (int i = 0; i < 40; i++) {
            System.out.print("~");
        }
        System.out.println();
    }

}
