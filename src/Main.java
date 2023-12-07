import order.*;
import product.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static order.OrderService.createOrderFromCart;

public class Main {
    public static void main(String[] args) {
        Customer user = new Customer();
        Cart cart = new Cart();

        List<Alcohol> listOfItems = new ArrayList<>();

        listOfItems.add(new Wine("Cab Sav", "Villa Cream", 13.25, 0.75, 0.11, "Spain", "Red"));
        listOfItems.add(new Wine("Sav Blan", "Berefoot", 10.5, 0.5, 0.12, "France", "White"));
        listOfItems.add(new Wine("White Zinfandel", "Barefoot", 4, 0.13, 0.10, "USA", "Pink"));
        listOfItems.add(new Whiskey("Black Barrel", "Jameson", 35.1, 0.95, 0.65, "Ireland", 3));
        listOfItems.add(new Vodka("Hlybniy Dar", "Horodyk", 10.25, 0.25, 0.40, 0.87));
        listOfItems.add(new Beer("Lager", "Heineken", 3.70, 0.5, 0.6, "Wheat", false));
        listOfItems.add(new Beer("Juicer", "Copensberg", 5.50, 0.35, 0.5, "Barley", true));

        System.out.println("WELLCOME \n Our products:");
        int counter = 1;
        for (Alcohol item : listOfItems)
            System.out.println(counter++ + ". " + item);

        Scanner input = new Scanner(System.in);
        int choice = -1;
        while (choice != 0){
            System.out.println("Choose what to add to cart");
            choice = input.nextInt();
            if (choice <= counter-1 && choice != 0){
                System.out.println("QT = ");
                cart.addItem(listOfItems.get(choice-1), input.nextInt());
            }
        }

        System.out.println(cart);

        choice = -1;
        while (choice != 0){
            System.out.println("Do you want to edit cart? 0-no, 1- delet item, 2- change QT");
            choice = input.nextInt();
            switch (choice){
                case 0:
                    break;
                case 1:
                    System.out.println("Whatt item you want to delete?");
                    choice = input.nextInt();
                    cart.removeProduct(choice);
                    break;
                case 2:
                    System.out.println("What items QT you want to change?");
                    choice = input.nextInt();
                    System.out.println("What is new QT?");
                    cart.updateQuantity(choice, input.nextInt());
                    break;
            }
        }




        Order order = createOrderFromCart(cart, user.getID());
        System.out.println(order);

        Order orderRepeat = new Order(order);
        System.out.println(orderRepeat);



        }
    }
