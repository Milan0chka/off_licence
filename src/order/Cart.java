package order;

import product.Alcohol;

import java.util.HashMap;
import java.util.Map;

/**
 * The `Cart` class represents a shopping cart where customers can add, update, remove, and view selected products.
 */
public class Cart {
    /**
     * A map that stores the selected products and their quantities in the cart.
     */
    private final Map<Alcohol, Integer> chosenProducts;

    /**
     * Creates an empty shopping cart.
     */
    public Cart() {
        this.chosenProducts = new HashMap<>();
    }


    /**
     * Gets the map of selected products and their quantities in the cart.
     *
     * @return A map containing selected products and their quantities.
     */
    public Map<Alcohol, Integer> getChosenProducts() {
        return chosenProducts;
    }

    /**
     * Adds a product to the cart with the specified quantity. If the product is already in the cart, the quantity is updated.
     *
     * @param item     The alcohol product to add to the cart.
     * @param quantity The quantity of the product to add.
     */
    public void addProduct(Alcohol item, int quantity) {
        if (isProductInCart(item))
            increaseQuantity(item, quantity);
        else
            chosenProducts.put(item, quantity);
    }

    /**
     * Checks if a product is already in the cart.
     *
     * @param item The alcohol product to check.
     * @return true if the product is in the cart, false otherwise.
     */
    private boolean isProductInCart(Alcohol item) {
        for (Alcohol product : chosenProducts.keySet())
            if (item == product)
                return true;
        return false;
    }

    /**
     * Increases the quantity of a product in the cart.
     *
     * @param item             The alcohol product whose quantity should be increased.
     * @param quantityIncrease The quantity to add to the existing quantity.
     */
    private void increaseQuantity(Alcohol item, int quantityIncrease) {
        chosenProducts.replace(item, chosenProducts.get(item) + quantityIncrease);
    }

    /**
     * Updates the quantity of a product in the cart.
     *
     * @param item     The alcohol product to update.
     * @param quantity The new quantity for the product.
     * @return true if the update was successful, false if the product is not in the cart.
     */
    public boolean updateQuantity(Alcohol item, int quantity) {
        if (!isProductInCart(item))
            return false;

        chosenProducts.replace(item, quantity);
        return true;
    }

    /**
     * Removes a product from the cart.
     *
     * @param item The alcohol product to remove.
     * @return true if the removal was successful, false if the product is not in the cart.
     */
    public boolean removeProduct(Alcohol item) {
        if (isProductInCart(item)) {
            chosenProducts.remove(item);
            return true;
        }
        return false;
    }

    /**
     * Clears all products from the cart.
     */
    public void clearCart() {
        chosenProducts.clear();
    }

    /**
     * Calculates the total cost of all products in the cart.
     *
     * @return The total cost of the products in the cart.
     */
    public double getTotal() {
        int total = 0;

        for (Alcohol item : chosenProducts.keySet())
            total += item.getPrice() * chosenProducts.get(item);

        return total;
    }


    /**
     * Checks if the cart is empty.
     *
     * @return true if the cart is empty, false otherwise.
     */
    public boolean isEmpty() {
        return chosenProducts.size() == 0;
    }


    @Override
    public String toString() {
        String str = "Items in a Cart:\n";

        if (chosenProducts.size() == 0)
            return "Cart is empty.";

        int counter = 1;
        for (Alcohol item : chosenProducts.keySet())
            str += counter++ + ". " + item + " x " + chosenProducts.get(item) + "\n";

        str += "\nTotal : " + getTotal() + "$";

        return str;
    }
}
