package product;

public class Whiskey extends Spirit{

    private int yearsOfAging;

    public Whiskey(int ID, String name, String producer, double price, double litres, double alcoholContent, int yearsOfAging) {
        super(ID, name, producer, price, litres, alcoholContent);
        this.yearsOfAging = yearsOfAging;
    }

    public int getYearsOfAging() {
        return yearsOfAging;
    }

    public void setYearsOfAging(int yearsOfAging) {
        this.yearsOfAging = yearsOfAging;
    }

    @Override
    public void infoAboutAlcoholType() {
        System.out.println("Whiskey is a rich, amber-colored spirit known for its deep,\n" +
                "complex flavors derived from grains and aging in wooden barrels");
    }

    @Override
    public String toString(){
        return "Whiskey (" + yearsOfAging + "y.o) " + super.toString();
    }

}

