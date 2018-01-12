import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Vehicle {

    private String carName;
    private String sipp;
    private BigDecimal price;
    private String supplier;
    private double rating;

    public Vehicle(JSONObject car) {
        this.carName = car.getString("name");
        this.sipp = car.getString("sipp");
        this.price = new BigDecimal(car.getDouble("price"));
        price = price.setScale(2, RoundingMode.CEILING);
        this.supplier = car.getString("supplier");
        this.rating = car.getDouble("rating");
    }

    public String getCarName() {
        return carName;
    }

    public String getSipp() {
        return sipp;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSupplier() {
        return supplier;
    }

    public double getRating() {
        return rating;
    }
}
