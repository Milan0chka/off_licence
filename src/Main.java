import order.*;
import product.*;
import user.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static order.OrderService.*;
import static product.ProductService.loadProducts;
import static product.ProductService.showProducts;
import static product.ProductService.findProduct;

import static user.AuthenticationService.register;
import static user.AuthenticationService.login;

import static user.AuthenticationService.logout;
import static user.CustomerService.loadCustomers;

//to-do 1.repeat an order 2.add product to cart after viewing


public class Main {

    public static Scanner input = new Scanner(System.in);

    public static Customer user;
    public static Cart cart = new Cart();

    public static void main(String[] args) {
        loadInformation();

        while (true){
            if (user == null)
                switch (menu()){
                    case 1:
                        viewListOfProducts();
                        break;
                    case 2:
                        searchProductByID();
                        break;
                    case 3:
                        logIn();
                        break;
                    case 4:
                        registerAccount();
                        break;
                    case 5:
                        System.out.println("\nSee you later!");
                        return;
                }
            else
                switch (loggedMenu()){
                    case 1:
                        viewListOfProducts();
                        break;
                    case 2:
                        searchProductByID();
                        break;
                    case 3:
                        openCart();
                        break;
                    case 4:
                        viewOrderHistory();
                        break;
                    case 5:
                        logOut();
                        break;
                    case 6:
                        System.out.println("\nSee you later!");
                        return;
                    default:
                        System.out.println("Unavailable option chosen.");
                        delay();
                        break;

                }
        }

    }

    public static void loadInformation(){
        loadProducts();
        loadCustomers();
    }

    public static int loggedMenu(){
        System.out.println("\t\t* OFF-LICENCE SHOP *");
        for (int i=0; i < 40; i++)
            System.out.print("~");

        System.out.println("\nMenu :");
        System.out.println("""
                1. View list of products
                2. Search for a product by ID
                3. Open cart
                4. Order history
                5. Log out
                6. Exit""");
        for (int i=0; i < 40; i++)
            System.out.print("~");
        System.out.println("\n =>");
        return input.nextInt();
    }

    public static int menu(){
        System.out.println("\t\t* OFF-LICENCE SHOP *");
        for (int i=0; i < 40; i++)
            System.out.print("~");
        System.out.println("\nMenu :");
        System.out.println("""
                1. View list of products
                2. Search for a product by ID
                3. Log in
                4. Register
                5. Exit""");
        for (int i=0; i < 40; i++)
            System.out.print("~");
        System.out.println("\n=>");
        return input.nextInt();
    }

    public static void viewListOfProducts(){
        System.out.println("\nLIST OF AVAILABLE PRODUCTS :");
        showProducts();
        delay();
    }

    public static void searchProductByID(){
        System.out.println("\nSEARCH PRODUCT BY ID : ");
        System.out.println("Enter productID => ");
        int productID = input.nextInt();
        Alcohol product = findProduct(productID);
        if (product == null)
            System.out.println("No product with ID "+ productID + " was found.");
        else{
            System.out.println("\nProduct with ID "+ productID+" : ");
            System.out.println(product);
            System.out.println("\nInformation about alcohol: ");
            product.infoAboutAlcoholType();
            System.out.printf("Price per litre : %.2f", product.calculatePricePerLitre());
            if(user != null){
                System.out.println("\n\nDo you wish to add product in cart?\n" +
                        "y/n?\n" +
                        "=>");
                String responce = input.next();
                if(responce.equals("y")){
                    System.out.println("Enter a quantity => ");
                    int qt = input.nextInt();
                    cart.addItem(product, qt);
                }
            }
        }
        delay();
    }

    public static void logIn(){
        System.out.println("\t\t* LOGIN * ");
        user = login();
        if( user != null)
            System.out.println("Welcome "+ user.getFullName());
        delay();
    }

    public static void registerAccount(){
        System.out.println("\t\t* REGISTRATION * ");
        user = register();
        if( user != null)
            System.out.println("We are happy that you joined us, "+ user.getFullName()+"!");

        delay();
    }
    public static void openCart(){
        while (true){
            for (int i=0; i < 40; i++)
                System.out.print("~");
            System.out.println("\nCART : \n"+ cart);
            switch (cartPrompt()){
                case 1:
                    addProductToCart();
                    break;
                case 2:
                    changeQuatityInCart();
                    break;
                case 3:
                    deleteProductFromCart();
                    break;
                case 4:
                    clearCart();
                    break;
                case 5:
                    placeOrder();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Unavailable option chosen.");
                    break;
            }
        }

    }

    private static int cartPrompt(){
        System.out.println("\nCart menu :");
        System.out.println("""
                1. Add products to the cart
                2. Change products quantity
                3. Delete products from the cart
                4. Clear the cart
                5. Place an order
                6. Exit
                => """);
        return input.nextInt();
    }

    private static void addProductToCart(){
        System.out.println("\nADDING TO CART :");
        while (true){
            System.out.println("Enter ID of product (Enter 0 to finish) =>");
            int productID = input.nextInt();
            if (productID == 0)
                return;
            Alcohol product = findProduct(productID);
            if (product == null)
                System.out.println("No product with ID "+ productID + " was found.");
            else {
                System.out.println("Enter quantity =>");
                int qt = input.nextInt();
                cart.addItem(product, qt);
                System.out.println();
            }

        }
    }

    private static void clearCart(){
        System.out.println("Are you sure you want to clear the cart?\n" +
                "y/n?\n" +
                "=>");
        String responce = input.next();
        if(responce.equals("y"))
            cart.clearCart();
    }

    private static void deleteProductFromCart(){
        System.out.println("\nDELETING FROM CART :");
        while (true){
            System.out.println("Enter ID of product (Enter 0 to finish) =>");
            int productID = input.nextInt();
            if (productID == 0)
                return;
            Alcohol product = findProduct(productID);
            if (product == null)
                System.out.println("No product with ID "+ productID + " was found.");
            else if (cart.removeProduct(product))
                System.out.println("Product "+ product + " was deleted from cart.");
            else
                System.out.println("Product "+ product +" is not in a cart.");
        }
    }

    private static void changeQuatityInCart(){
        System.out.println("\nCHANGING AN QUANTITY :");
        while (true){
            System.out.println("Enter ID of product (Enter 0 to finish) =>");
            int productID = input.nextInt();
            if (productID == 0)
                return;
            Alcohol product = findProduct(productID);
            if (product == null)
                System.out.println("No product with ID "+ productID + " was found.");

            System.out.println("Enter quantity =>");
            int qt = input.nextInt();
            if (cart.updateQuantity(product, qt))
                System.out.println("Product "+ product + " quantity was changed.");
            else
                System.out.println("Product "+ product +" is not in a cart or you entered negative integer.");

        }
    }

    private static void placeOrder(){
        System.out.println("\nPLACING OF THE ORDER :");
        if(cart.isEmpty()){
            System.out.println("Cart in empty. Impossible to place an order");
            return;
        }
        Order newOrder = createOrderFromCart(cart,user.getID());
        if( newOrder != null)
            System.out.println("Order has successfully placed!\n"+ newOrder);
        else
            System.out.println("Oops, problem occur. Order was not placed.");
        delay();
    }

    public static void viewOrderHistory(){
        System.out.println("\nORDER HISTORY :");
        int ordersNumber = showCustomerOrders();
        if (ordersNumber != 0){
            System.out.println("\n\n Do you wish to repeat some order?\n" +
                    "y/n?\n" +
                    "=> ");
            String responce = input.next();
            if(responce.equals("y")){
                repeatCustomerOrder();
            }
        }
        delay();
    }

    private static void repeatCustomerOrder(){
        System.out.println("Enter ID of the order that you want to repeat =>");
        Order newOrder = repeatOrder(input.nextInt());
        if(newOrder == null)
            System.out.println("Oops, something went wrong.");
        else
            System.out.println("Order has successfully placed!\n"+ newOrder);
    }

    public static void logOut(){
        System.out.println("\nAre you sure you want to log out?\n" +
                "y/n ?\n" +
                "=>");
        String responce = input.next();
        if(responce.equals("y")){
            cart.clearCart();
            logout();
            user = null;
        }
    }
    public static void delay(){
        System.out.println("\n\nPress Enter to continue ...");
        input.nextLine();
        input.nextLine();
    }

    }
