package catHouse.entities.toys;

public abstract class BaseToy implements Toy{
    private int softness;
    private double price;

    public BaseToy(int softness, double price) {
        this.softness = softness;
        this.price = price;
    }

    @Override
    public int getSoftness() {
        return softness;
    }

    public void setSoftness(int softness) {
        this.softness = softness;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
