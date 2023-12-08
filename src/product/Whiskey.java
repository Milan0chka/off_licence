package product;


/**
 * The `Whiskey` class represents a specific type of spirit, which is a subclass of `Spirit`.
 * It includes additional attributes and methods related to whiskey.
 */
public class Whiskey extends Spirit {

    private int yearsOfAging;

    /**
     * Creates a new instance of the `Whiskey` class with the specified attributes.
     *
     * @param ID             The unique ID of the whiskey.
     * @param name           The name of the whiskey.
     * @param producer       The producer or manufacturer of the whiskey.
     * @param price          The price of the whiskey.
     * @param litres         The volume of the whiskey in liters (L).
     * @param alcoholContent The alcohol content percentage of the whiskey.
     * @param yearsOfAging   The number of years the whiskey has been aged.
     */
    public Whiskey(int ID, String name, String producer, double price,
                   double litres, double alcoholContent, int yearsOfAging) {
        super(ID, name, producer, price, litres, alcoholContent);
        this.yearsOfAging = yearsOfAging;
    }

    public int getYearsOfAging() {
        return yearsOfAging;
    }

    public void setYearsOfAging(int yearsOfAging) {
        this.yearsOfAging = yearsOfAging;
    }

    /**
     * Provides information about the whiskey .
     */
    @Override
    public void infoAboutAlcoholType() {
        System.out.println("Whiskey is a rich, amber-colored spirit known for its deep, " +
                "complex flavors derived from grains and aging in wooden barrels.");
    }

    @Override
    public String toString() {
        return "Whiskey (" + yearsOfAging + " years old) " + super.toString();
    }
}

