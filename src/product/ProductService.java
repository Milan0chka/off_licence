package product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.VolatileCallSite;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public static List<Alcohol> productList = new ArrayList<>();

    public static void loadProducts(){
        try (BufferedReader reader = new BufferedReader(new FileReader("src/database/products.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Alcohol product = parseAlcoholLine(line);
                if (product != null) {
                    productList.add(product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Alcohol parseAlcoholLine(String line) {
        String[] parts = line.split(", ");
        String ID = parts[0];
        String type = parts[1];

        switch (type) {
            case "Beer":
                return new Beer(Integer.parseInt(parts[0]), parts[2],parts[3], Double.parseDouble(parts[4]), Double.parseDouble(parts[5]),
                                Double.parseDouble(parts[6]), parts[7], Boolean.parseBoolean(parts[8]));
            case "Wine":
                return new Wine(Integer.parseInt(parts[0]), parts[2],parts[3], Double.parseDouble(parts[4]), Double.parseDouble(parts[5]),
                                Double.parseDouble(parts[6]), parts[7], parts[8]);
            case "Whiskey":
                return new Whiskey(Integer.parseInt(parts[0]), parts[2],parts[3], Double.parseDouble(parts[4]), Double.parseDouble(parts[5]),
                                Double.parseDouble(parts[6]),Integer.parseInt(parts[7]));
            case "Vodka":
                return new Vodka(Integer.parseInt(parts[0]), parts[2],parts[3], Double.parseDouble(parts[4]), Double.parseDouble(parts[5]),
                             Double.parseDouble(parts[6]), Double.parseDouble(parts[7]));
            default:
                return null;
        }
    }

    public static void showProducts(){
        for( int i = 0; i < productList.size(); i++){
            System.out.println((i+1)+ ". "+ productList.get(i));
        }
    }

    public static Alcohol findProduct(int productID){
        for (Alcohol product: productList){
            if (product.ID == productID){
                return product;
            }
        }
        return null;
    }

    public static void showDetailedInformationAboutProduct(Alcohol product) {
        String str = "";
        if (product instanceof Wine) {
            Wine wine = (Wine) product;
            str += String.format("""
                    Alcohol type : Wine
                    Color        : %s
                    Origin       : %s
                    """, wine.getColor(), wine.getCountryOfOrigin());
        } else if (product instanceof Beer) {
            Beer beer = (Beer) product;
            str += String.format("""
                    Alcohol type : Beer
                    Made of      : %s
                    Beer type    : """, beer.getGrain());
            str += (beer.isCraft() ? " Crafted\n" : " Regular\n");
        } else if (product instanceof Vodka) {
            Vodka vodka = (Vodka) product;
            str += String.format("""
                    Alcohol type : Spirit
                    Spirit type  : Vodka
                    Purity       : %.2f
                    """, vodka.getPurity());
        }else if (product instanceof Whiskey){
            Whiskey whiskey = (Whiskey) product;
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
        System.out.printf("Price per litre : %.2f", product.calculatePricePerLitre());
    }
}