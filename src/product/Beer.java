package product;

/**
 * The `Beer` class represents a specific type of alcoholic beverage, which is a subclass of `Alcohol`.
 * It includes additional attributes and methods related to beer.
 */
public class Beer extends Alcohol {
    /**
     * The type of grain used in brewing the beer.
     */
    private String grain;
    /**
     * Indicates whether the beer is a craft beer.
     */
    private boolean isCraft;

    /**
     * Creates a new instance of the `Beer` class with the specified attributes.
     *
     * @param ID             The unique ID of the beer.
     * @param name           The name of the beer.
     * @param producer       The producer or manufacturer of the beer.
     * @param price          The price of the beer.
     * @param litres         The volume of the beer in liters (L).
     * @param alcoholContent The alcohol content percentage of the beer.
     * @param grain          The type of grain used in brewing the beer.
     * @param isCraft        Indicates whether the beer is a craft beer.
     */
    public Beer(int ID, String name, String producer, double price, double litres,
                double alcoholContent, String grain, Boolean isCraft) {
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

    /**
     * Provides information about the specific beer.
     */
    @Override
    public void infoAboutAlcoholType() {
        System.out.println("products.Beer is a beloved and versatile beverage, crafted through the fermentation \n" +
                "of grains, which offers a broad spectrum of flavors and styles, from light and refreshing\n" +
                "lagers to rich and complex stouts.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return grain + (isCraft ? " crafted" : "") + " beer " + super.toString();
    }

}
