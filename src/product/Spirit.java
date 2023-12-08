package product;

public abstract class Spirit extends Alcohol {

    public Spirit(int ID, String name, String producer, double price, double litres,
                double alcoholContent){
        super(ID, name, producer, price, litres, alcoholContent);
    }

    @Override
    public abstract void infoAboutAlcoholType();

}


