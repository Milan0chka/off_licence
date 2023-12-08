package order;

import product.Alcohol;
import java.util.HashMap;
import java.util.Map;


public final class Cart {
    private Map<Alcohol, Integer> chosenItems;

    public Cart(){
        this.chosenItems = new HashMap<>();
    }

    public Map<Alcohol, Integer> getChosenItems() {
        return chosenItems;
    }

    public void addItem(Alcohol item, int quantity){
        if (isProductInCart(item))
            increaseQuantity(item, quantity);
        else
            chosenItems.put(item, quantity);
    }

    private boolean isProductInCart(Alcohol item){
        for (Alcohol product: chosenItems.keySet()){
            if (item == product)
                return true;
        }
        return false;
    }

    private void increaseQuantity(Alcohol item, int quantityIncrease){
        chosenItems.replace(item, chosenItems.get(item)+quantityIncrease);
    }

    public boolean updateQuantity(Alcohol item, int quantity){
        if (!isProductInCart(item))
            return false;
        chosenItems.replace(item, quantity);
        return true;
    }


    public boolean removeProduct(Alcohol item){
        if(isProductInCart(item)){
            chosenItems.remove(item);
            return true;
        }
        return false;
    }

    public boolean removeProduct(int index){
        int counter = 1;
        for(Alcohol item: chosenItems.keySet()){
            if (counter == index){
                chosenItems.remove(item);
                return true;
            }
            counter++;
        }
        return false;
    }

    public void clearCart(){
        chosenItems.clear();
    }
    public double getTotal(){
        int total = 0;
        for (Alcohol item: chosenItems.keySet())
            total += item.getPrice()* chosenItems.get(item);
        return total;
    }

    public boolean isEmpty(){
        return chosenItems.size() == 0;
    }

    @Override
    public String toString(){
        String str = "Items in a Cart:\n";
        if ( chosenItems.size() ==0)
            return "Cart is empty.";
        int counter = 1;
        for ( Alcohol item : chosenItems.keySet())
            str += counter++ + ". " + item + " x " + chosenItems.get(item) + "\n";
        str += "\nTotal : " + getTotal() + "$";
        return str;
    }
}
