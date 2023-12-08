package product;

public class Beer extends Alcohol {
    private String grain;
    private boolean isCraft;

    public Beer(int ID, String name, String producer, double price, double litres,
                double alcoholContent, String grain, Boolean isCraft){
        super(ID, name, producer, price, litres, alcoholContent);
        this.grain = grain;
        this.isCraft = isCraft;
    }

    public String getGrain() {
        return grain;
    }

    public void setGrain(String grain) {
        this.grain = grain;
    }

    public boolean isCraft() {
        return isCraft;
    }

    public void setCraft(boolean craft) {
        isCraft = craft;
    }

    @Override
    public void infoAboutAlcoholType() {
        System.out.println("products.Beer is a beloved and versatile beverage, crafted through the fermentation \n" +
                "of grains, which offers a broad spectrum of flavors and styles, from light and refreshing\n" +
                "lagers to rich and complex stouts.");
    }
    @Override
    public String toString() {
        return (isCraft ? "Crafted " : "") + grain + " beer " + super.toString();
    }

}
