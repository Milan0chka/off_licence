package product;

public class Wine extends Alcohol {
    private String countryOfOrigin;
    private String color;

    public Wine(int ID, String name, String producer, double price, double litres,
                double alcoholContent, String countryOfOrigin, String color){
        super(ID, name, producer, price, litres, alcoholContent);
        this.countryOfOrigin = countryOfOrigin;
        this.color = color;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void infoAboutAlcoholType() {
        System.out.println("Wine is an elegant and diverse beverage made from fermented grapes or other fruits,\n" +
                "celebrated for its rich array of flavors and its integral role in various cultures \n" +
                "and culinary traditions.");
    }
    @Override
    public String toString(){
        return color + " wine ("+ countryOfOrigin + ") " + super.toString();
    }

}
