package web;

import org.json.JSONObject;
import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * Class to define a single vehicle and all information associated with it.
 * */
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

    /**
     * Initialise a vehicle and define the information that was provided in the JSON file retrieved from the URL.
     * */
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

    public int getScore() {
        return score;
    }

    public double getOverallScore() {
        return overallScore;
    }

    /**
     * Functionality to add the vehicle information found when investigating the vehicle's sipp.
     * More information on sipp breakdown can be seen in /resources/VehicleDetails.json
     * @param type              The vehicle type.
     * @param noOfDoors         The number of doors or type the vehicle has.
     * @param transmissionType  The transmission of the vehicle.
     * @param fuelAC            What fuel the vehicle takes and whether it has AC or not
     * @param increase          How much the vehicle scored based on transmission and AC.
     * */
    protected void addSpecifications(String type, String noOfDoors, String transmissionType, String[] fuelAC, int increase) {

        carType = type;
        doors = noOfDoors;
        transmission = transmissionType;
        fuel = fuelAC[0];
        AC = fuelAC[1];
        addToScore(increase);

    }

    /**
     * Called when the vehicle's score has been calculated so it can update the overall score.
     * @param increase How much the vehicle scored based on transmission and AC.
     * */
    protected void addToScore(int increase) {

        score = score + increase;
        overallScore = overallScore + score;

    }
}
