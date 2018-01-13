import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Vehicle {

    private String carName;
    private String sipp;
    private BigDecimal price;
    private String supplier;
    private double rating;
    private String carType;
    private String doors;
    private String transmission;
    private String fuel;
    private String AC;
    private int score = 0;
    private double overallScore;

    public Vehicle(JSONObject car) {
        this.carName = car.getString("name");
        this.sipp = car.getString("sipp");
        this.price = new BigDecimal(car.getDouble("price"));
        price = price.setScale(2, RoundingMode.CEILING);
        this.supplier = car.getString("supplier");
        this.rating = car.getDouble("rating");
        overallScore = rating;
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

    public String getCarType() {
        return carType;
    }

    public String getDoors() {
        return doors;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getFuel() {
        return fuel;
    }

    public String getAC() {
        return AC;
    }

    public void addSpecifications(String type, String noOfDoors, String transmissionType, String[] fuelAC, int increase) {
        carType = type;
        doors = noOfDoors;
        transmission = transmissionType;
        fuel = fuelAC[0];
        AC = fuelAC[1];
        addToScore(increase);
    }

    public int getScore() {
        return score;
    }

    public double getOverallScore() {

        return overallScore;
    }

    public void addToScore(int increase) {
        score = score + increase;
        overallScore = overallScore + score;

    }
}
