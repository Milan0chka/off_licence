package order;

import product.Alcohol;
import java.util.HashMap;
import java.util.Map;


public final class Cart {
    private Map<Alcohol, Integer> chosenItems;

    public Cart(){
        chosenItems = new HashMap<>();
    }

    public void addItem(Alcohol item, int quantity){
        chosenItems.put(item, quantity);
    }

    public void addItem(Alcohol item){
        chosenItems.put(item, 1);
    }

    public void updateQuantity(Alcohol item, int quantity){
        chosenItems.replace(item, quantity);
    }

    public void updateQuantity(int index, int quantity){
        int counter = 1;
        for(Alcohol item: chosenItems.keySet()){
            if (counter == index){
                chosenItems.replace(item, quantity);
                return;
            }
            counter++;
        }
    }

    public void removeProduct(Alcohol item){
        chosenItems.remove(item);
    }

    public void removeProduct(int index){
        int counter = 1;
        for(Alcohol item: chosenItems.keySet()){
            if (counter == index){
                chosenItems.remove(item);
                return;
            }
            counter++;
        }
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

    public Map<Alcohol, Integer> getChosenItems() {
        return chosenItems;
    }

    @Override
    public String toString(){
        String str = "Items in a Cart:";
        int counter = 1;
        for ( Alcohol item : chosenItems.keySet())
            str += counter++ + ". " + item + " x " + chosenItems.get(item) + "\n";
        return str;
    }
}
