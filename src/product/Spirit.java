package product;

public abstract class Spirit extends Alcohol {

    public Spirit(String name, String producer, double price, double litres,
                double alcoholContent){
        super(name, producer, price, litres, alcoholContent);
    }

    @Override
    public abstract void infoAboutAlcoholType();

}


