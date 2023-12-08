package product;

/**
 * The `Spirit` class represents a specific type of alcoholic beverage, which is a subclass of `Alcohol`.
 * It includes additional attributes and methods related to spirits.
 */
public abstract class Spirit extends Alcohol {

    /**
     * Creates a new instance of the `Spirit` class with the specified attributes.
     *
     * @param ID             The unique ID of the spirit.
     * @param name           The name of the spirit.
     * @param producer       The producer or manufacturer of the spirit.
     * @param price          The price of the spirit.
     * @param litres         The volume of the spirit in liters (L).
     * @param alcoholContent The alcohol content percentage of the spirit.
     */
    public Spirit(int ID, String name, String producer,
                  double price, double litres, double alcoholContent) {
        super(ID, name, producer, price, litres, alcoholContent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void infoAboutAlcoholType();

    /**
     * This method prints a message indicating that the product is not recomended to
     * be purchased by individuals under 21 years old.
     */
    public void notifyCustomer() {
        System.out.println("We don`t recommend purchasing this product, if you are under 21 y.o..");
    }

}


