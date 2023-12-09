package product;

/**
 * The `Vodka` class represents a specific type of spirit, which is a subclass of `Spirit`.
 * It includes additional attributes and methods related to vodka.
 */
public final class Vodka extends Spirit {
    /**
     * Percentage of purity of a product
     */
    private double purity;

    /**
     * Creates a new instance of the `Vodka` class with the specified attributes.
     *
     * @param ID             The unique ID of the vodka.
     * @param name           The name of the vodka.
     * @param producer       The producer or manufacturer of the vodka.
     * @param price          The price of the vodka.
     * @param litres         The volume of the vodka in liters (L).
     * @param alcoholContent The alcohol content percentage of the vodka.
     * @param purity         The purity percentage of the vodka.
     */
    public Vodka(int ID, String name, String producer, double price,
                 double litres, double alcoholContent, double purity) {
        super(ID, name, producer, price, litres, alcoholContent);
        this.purity = purity;
    }

    public double getPurity() {
        return purity;
    }

    public void setPurity(double purity) {
        this.purity = purity;
    }

    /**
     * Provides information about the vodka.
     */
    @Override
    public void infoAboutAlcoholType() {
        System.out.println("Vodka is a clear and smooth liquor, \n" +
                "celebrated for its purity and versatility in cocktails");
    }

    @Override
    public String toString() {
        return "Vodka (" + purity + "% pure) " + super.toString();
    }
}
