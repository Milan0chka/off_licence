package product;

public class Beer extends Alcohol {
    private String type;
    private boolean isCraft;

    public Beer(String name, String producer, double price, double litres,
                double alcoholContent, String type, Boolean isCraft){
        super(name, producer, price, litres, alcoholContent);
        this.type = type;
        this.isCraft = isCraft;
    }

    @Override
    public void infoAboutAlcoholType() {
        System.out.println("products.Beer is a beloved and versatile beverage, crafted through the fermentation \n" +
                "of grains, which offers a broad spectrum of flavors and styles, from light and refreshing\n" +
                "lagers to rich and complex stouts.");
    }
    @Override
    public String toString() {
        return (isCraft ? "Crafted" : "") + type + " beer " + super.toString();
    }

}
