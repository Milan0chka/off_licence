package product;

public class Vodka extends Spirit{
    private double purity;

    public Vodka(int ID, String name, String producer, double price,
                 double litres, double alcoholContent, double purity) {
        super(ID, name, producer, price, litres, alcoholContent);
        this.purity = purity;
    }

    @Override
    public void infoAboutAlcoholType() {
        System.out.println("Vodka is a clear and smooth liquor, \n" +
                "celebrated for its purity and versatility in cocktails");
    }

    public double getPurity() {
        return purity;
    }

    public void setPurity(double purity) {
        this.purity = purity;
    }

    @Override
    public String toString(){
        return "Vodka (" + purity + "% pure) " + super.toString();
    }
}
