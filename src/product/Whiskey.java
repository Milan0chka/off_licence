package product;

public class Whiskey extends Spirit{

    private int yearsOfAging;

    public Whiskey(String name, String producer, double price, double litres, double alcoholContent, String type, int yearsOfAging) {
        super(name, producer, price, litres, alcoholContent);
        this.yearsOfAging = yearsOfAging;
    }

    @Override
    public void infoAboutAlcoholType() {
        System.out.println("Whiskey is a rich, amber-colored spirit known for its deep,\n" +
                " complex flavors derived from grains and aging in wooden barrels");
    }

    @Override
    public String toString(){
        return "Whiskey (" + yearsOfAging + "y.o) " + super.toString();
    }

}

