package product;

/**
 * The `Wine` class represents a specific type of alcoholic beverage, which is a subclass of `Alcohol`.
 * It includes additional attributes and methods related to wine.
 */
public class Wine extends Alcohol {

    /**
     * The country of origin where the wine is produced.
     */
    private String countryOfOrigin;

    /**
     * The color of the wine (e.g., red, white).
     */
    private String color;

    /**
     * Creates a new instance of the `Wine` class with the specified attributes.
     *
     * @param ID              The unique ID of the wine.
     * @param name            The name of the wine.
     * @param producer        The producer or manufacturer of the wine.
     * @param price           The price of the wine.
     * @param litres          The volume of the wine in liters (L).
     * @param alcoholContent  The alcohol content percentage of the wine.
     * @param countryOfOrigin The country of origin where the wine is produced.
     * @param color           The color of the wine (e.g., red, white).
     */
    public Wine(int ID, String name, String producer, double price, double litres,
                double alcoholContent, String countryOfOrigin, String color) {
        super(ID, name, producer, price, litres, alcoholContent);
        this.countryOfOrigin = countryOfOrigin;
        this.color = color;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Provides information about the wine.
     */
    @Override
    public void infoAboutAlcoholType() {
        System.out.println("Wine is an elegant and diverse beverage made from fermented grapes or other fruits,\n" +
                "celebrated for its rich array of flavors and its integral role in various cultures \n" +
                "and culinary traditions.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return color + " wine (" + countryOfOrigin + ") " + super.toString();
    }

}
