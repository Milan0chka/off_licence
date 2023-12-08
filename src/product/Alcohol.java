package product;

/**
 * The abstract class Alcohol represents a general type of alcoholic beverage.
 * It provides common attributes and methods for all alcohol products.
 */
public abstract class Alcohol {

    /**
     * The unique ID of the alcohol product.
     */
    protected final int ID;

    /**
     * The name of the alcohol product.
     */
    protected String name;

    /**
     * The producer or manufacturer of the alcohol product.
     */
    protected String producer;

    /**
     * The price of the alcohol product.
     */
    protected double price;

    /**
     * The volume of the alcohol product in liters (L).
     */
    protected double litres;

    /**
     * The alcohol content percentage of the alcohol product.
     */
    protected double alcoholContent;

    /**
     * Creates a new instance of the Alcohol class with the specified attributes.
     *
     * @param ID             The unique ID of the alcohol product.
     * @param name           The name of the alcohol product.
     * @param producer       The producer or manufacturer of the alcohol product.
     * @param price          The price of the alcohol product.
     * @param litres         The volume of the alcohol product in liters (L).
     * @param alcoholContent The alcohol content percentage of the alcohol product.
     */
    protected Alcohol(int ID, String name, String producer, double price,
                      double litres, double alcoholContent) {
        this.ID = ID;
        this.name = name;
        this.producer = producer;
        this.price = price;
        this.litres = litres;
        this.alcoholContent = alcoholContent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getLitres() {
        return litres;
    }

    public void setLitres(double litres) {
        this.litres = litres;
    }

    public double getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(double alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public int getID() {
        return ID;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    /**
     * Calculates the price per liter (price per L) of the alcohol product.
     *
     * @return The price per liter of the alcohol product.
     */
    public final double calculatePricePerLitre() {
        return price / litres;
    }

    /**
     * Provides information about the specific type of alcohol (e.g., wine, beer) represented by the subclass.
     */
    public abstract void infoAboutAlcoholType();

    @Override
    public String toString() {
        return String.format("%s %.2fL(%.2f%%) - %.2f$ [%d]", name, litres, alcoholContent, price, ID);
    }
}
