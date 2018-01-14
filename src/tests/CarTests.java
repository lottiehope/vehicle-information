package tests;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import web.DataRetriever;
import web.Vehicle;
import web.VehicleSpecification;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Class responsible for testing whether a Vehicle gets defined correctly.
 * */
public class CarTests {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    /**
     * Tests whether the vehicle got initialised with the correct values.
     * */
    @Test
    public void initialiseVehicle() {

        Vehicle vehicle = setUpVehicle();
        Assert.assertEquals("Ford Mondeo", vehicle.getCarName());
        Assert.assertEquals("SDMR", vehicle.getSipp());
        Assert.assertEquals(new BigDecimal(339.31).setScale(2, RoundingMode.CEILING), vehicle.getPrice());
        Assert.assertEquals("Hertz", vehicle.getSupplier());
        Assert.assertEquals(8.9, vehicle.getRating(), 0.01);
        System.out.println(ANSI_GREEN + "Initialise vehicle tests passing\n" + ANSI_RESET);

    }

    /**
     * Used to define a vehicle that can be used in tests.
     * @return Vehicle A single vehicle for testing purposes.
     * */
    public Vehicle setUpVehicle() {

        JSONObject vehicleDetails = new JSONObject();
        vehicleDetails.put("name", "Ford Mondeo");
        vehicleDetails.put("sipp", "SDMR");
        vehicleDetails.put("price", "339.31");
        vehicleDetails.put("supplier", "Hertz");
        vehicleDetails.put("rating", "8.9");
        Vehicle vehicle = new Vehicle(vehicleDetails);
        return vehicle;

    }

    /**
     * Used to check that the vehicle specification will return the correct value for vehicle type.
     * */
    @Test
    public void checkVehicleType() {

        VehicleSpecification specification = new VehicleSpecification("resources/VehicleDetails.json");
        Assert.assertEquals("Mini", specification.getVehicleType("M"));
        System.out.println(ANSI_GREEN + "Check vehicle type test passing\n" + ANSI_RESET);

    }

    /**
     * Used to check that the vehicle specification will return the correct value for vehicle doors/type.
     * */
    @Test
    public void checkVehicleDoors() {

        VehicleSpecification specification = new VehicleSpecification("resources/VehicleDetails.json");
        Assert.assertEquals("2 doors", specification.getVehicleDoors("B"));
        System.out.println(ANSI_GREEN + "Check vehicle doors test passing\n" + ANSI_RESET);

    }

    /**
     * Used to check that the vehicle specification will return the correct value for vehicle transmission.
     * */
    @Test
    public void checkVehicleTransmission() {

        VehicleSpecification specification = new VehicleSpecification("resources/VehicleDetails.json");
        Assert.assertEquals("Automatic", specification.getVehicleTransmission("A"));
        System.out.println(ANSI_GREEN + "Check vehicle transmission test passing\n" + ANSI_RESET);

    }

    /**
     * Used to check that the vehicle specification will return the correct values for vehicle fuel and AC.
     * */
    @Test
    public void checkVehicleFuelAndAC() {

        VehicleSpecification specification = new VehicleSpecification("resources/VehicleDetails.json");
        String[] fuelAndAC = specification.getVehicleFuelAndAC("R");
        Assert.assertEquals("Petrol", fuelAndAC[0]);
        Assert.assertEquals("AC", fuelAndAC[1]);
        System.out.println(ANSI_GREEN + "Fuel and AC test passing\n" + ANSI_RESET);

    }

    /**
     * Used to test whether the overall score of a vehicle includes both the supplier rating and the vehicle rating.
     * */
    @Test
    public void checkOverallScore() {

        Vehicle vehicle = setUpVehicle();
        ArrayList<Vehicle> allVehicles = new ArrayList<>();
        allVehicles.add(vehicle);
        DataRetriever retriever = new DataRetriever(allVehicles);
        Assert.assertEquals(11.9, vehicle.getOverallScore(), 0.001);
        System.out.println(ANSI_GREEN + "Check overall score test passing\n" + ANSI_RESET);

    }

    /**
     * Used to ensure that when a vehicle's specification is found, it will add these values to the vehicle.
     * */
    @Test
    public void checkSippValuesAreUpdated() {

        Vehicle vehicle = setUpVehicle();
        ArrayList<Vehicle> allVehicles = new ArrayList<>();
        allVehicles.add(vehicle);
        DataRetriever retriever = new DataRetriever(allVehicles);

        Assert.assertEquals("Standard", vehicle.getCarType());
        Assert.assertEquals("5 doors", vehicle.getDoors());
        Assert.assertEquals("Manual", vehicle.getTransmission());
        Assert.assertEquals("Petrol", vehicle.getFuel());
        Assert.assertEquals("AC", vehicle.getAC());
        System.out.println(ANSI_GREEN + "Check Sipp values are updated tests passing\n" + ANSI_RESET);

    }

}
