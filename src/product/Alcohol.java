package product;

import static java.lang.Math.round;

public abstract class Alcohol {
    protected static int barcode = 1;
    protected final int ID;
    protected String name;
    protected String producer;
    protected double price;
    protected double litres;
    protected double alcoholContent;

    protected Alcohol(String name, String producer, double price,
                      double litres, double alcoholContent){
        this.ID = barcode++;
        this.name = name;
        this.producer = producer;
        this.price = price;
        this.litres = litres;
        this.alcoholContent = alcoholContent;
    }

    protected Alcohol(int ID, String name, String producer, double price,
                      double litres, double alcoholContent){
        this.ID = ID;
        this.name = name;
        this.producer = producer;
        this.price = price;
        this.litres = litres;
        this.alcoholContent = alcoholContent;
    }

    public final double calculatePricePerLitre(){
        return price/litres ;
    }

    public abstract void infoAboutAlcoholType();

    @Override
    public String toString(){
        return String.format("%s %.2fL(%.2f%%) - %.2f$ [%d]", name, litres, alcoholContent, price, ID);
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
}
