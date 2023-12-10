package product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The `ProductService` class manages the loading and retrieval of alcohol products.
 */
public class ProductService {
    /**
     * A list that stores alcohol products.
     */
    private static final List<Alcohol> productList = new ArrayList<>();

    /**
     * Loads alcohol products from a file and populates the product list.
     */
    public void loadProducts() {
        try (BufferedReader reader = new BufferedReader(new FileReader("database/products.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Alcohol product = parseAlcoholLine(line);
                if (product != null) {
                    productList.add(product);
                }
            }
        } catch (IOException e) {
            System.out.println("Oops, problem occur while reading products file. Impossible to continue work.");
            e.printStackTrace();
        }
    }

    /**
     * Parses a line of alcohol product data from a file.
     *
     * @param line The line of alcohol product data.
     * @return The parsed Alcohol object, or null if parsing fails.
     */
    private Alcohol parseAlcoholLine(String line) {
        String[] parts = line.split(", ");
        String type = parts[1];

        return switch (type) {
            case "Beer" ->
                    new Beer(Integer.parseInt(parts[0]), parts[2], parts[3], Double.parseDouble(parts[4]), Double.parseDouble(parts[5]),
                            Double.parseDouble(parts[6]), parts[7], Boolean.parseBoolean(parts[8]));
            case "Wine" ->
                    new Wine(Integer.parseInt(parts[0]), parts[2], parts[3], Double.parseDouble(parts[4]), Double.parseDouble(parts[5]),
                            Double.parseDouble(parts[6]), parts[7], parts[8]);
            case "Whiskey" ->
                    new Whiskey(Integer.parseInt(parts[0]), parts[2], parts[3], Double.parseDouble(parts[4]), Double.parseDouble(parts[5]),
                            Double.parseDouble(parts[6]), Integer.parseInt(parts[7]));
            case "Vodka" ->
                    new Vodka(Integer.parseInt(parts[0]), parts[2], parts[3], Double.parseDouble(parts[4]), Double.parseDouble(parts[5]),
                            Double.parseDouble(parts[6]), Double.parseDouble(parts[7]));
            default -> null;
        };
    }

    /**
     * Finds an alcohol product by its ID.
     *
     * @param productID The ID of the product to find.
     * @return The found Alcohol object, or null if not found.
     */
    public Alcohol findProduct(int productID) {
        for (Alcohol product : productList)
            if (product.ID == productID)
                return product;

        return null;
    }

    /**
     * Displays a list of alcohol products.
     */
    public void showProducts() {
        for (int i = 0; i < productList.size(); i++)
            System.out.println((i + 1) + ". " + productList.get(i));

    }

    /**
     * Displays detailed information about an alcohol product.
     *
     * @param product The Alcohol object for which to display information.
     */
    public void showDetailedInformationAboutProduct(Alcohol product) {
        String str = "";

        if (product instanceof Wine wine) {

            str += String.format("""
                    Alcohol type : Wine
                    Color        : %s
                    Origin       : %s
                    """, wine.getColor(), wine.getCountryOfOrigin());

        } else if (product instanceof Beer beer) {

            str += String.format("""
                    Alcohol type : Beer
                    Made of      : %s
                    Beer type    :""", beer.getGrain());
            str += (beer.isCraft() ? " Crafted\n" : " Regular\n");

        } else if (product instanceof Vodka vodka) {

            str += String.format("""
                    Alcohol type : Spirit
                    Spirit type  : Vodka
                    Purity       : %.2f
                    """, vodka.getPurity());

        } else if (product instanceof Whiskey whiskey) {

            str += String.format("""
                    Alcohol type : Spirit
                    Spirit type  : Whiskey
                    Age          : %d y.o
                    """, whiskey.getYearsOfAging());

        }

        str += String.format("""
                        Product name : %s
                        Producer     : %s
                        ABV          : %.2f
                        Volume       : %.2f
                        Price        : %.2f
                        """, product.getName(), product.getProducer(), product.getAlcoholContent(),
                product.getLitres(), product.getPrice());

        System.out.println(str);

        System.out.println("\nInformation about alcohol type: ");
        product.infoAboutAlcoholType();
        if (product instanceof Spirit)
            ((Spirit) product).notifyCustomer();

        System.out.printf("Price per litre : %.2f\n", product.calculatePricePerLitre());
    }
}