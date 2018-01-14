package web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DataRetriever {

    VehicleSpecification vehicleInfoSource = new VehicleSpecification();
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    ArrayList<String> allCarDetails = new ArrayList<>();

    public DataRetriever(ArrayList<Vehicle> vehiclesList) {
        vehicles = vehiclesList;
        setSpecifications();
    }

    //Task 1
    public void printVehicleNamesAndPrices() {
        ArrayList<String> namesAndPrices = new ArrayList<>();
        for (Vehicle car: vehicles) {
            String nameAndPrice = car.getCarName() + " - " + car.getPrice().toString();
            namesAndPrices.add(nameAndPrice);
        }
        Collections.sort(namesAndPrices);
        for (String s: namesAndPrices) {
            System.out.println(s);
        }
    }

    public void printVehicleSpecifications() {
        for (String details : allCarDetails) {
            System.out.println(details);
        }

    }

    public void setSpecifications() {
        ArrayList<String> carDetails = new ArrayList<>();
        for (Vehicle car : vehicles) {
            String[] sipp = car.getSipp().split("");
            carDetails.add(car.getCarName());
            carDetails.add(car.getSipp());
            carDetails.add(vehicleInfoSource.getVehicleType(sipp[0]));
            carDetails.add(vehicleInfoSource.getVehicleDoors(sipp[1]));
            carDetails.add(vehicleInfoSource.getVehicleTransmission(sipp[2]));
            String[] fuelAndAC = vehicleInfoSource.getVehicleFuelAndAC(sipp[3]);
            carDetails.add(fuelAndAC[0]);
            carDetails.add(fuelAndAC[1]);
            int score = setVehicleScore(carDetails.get(4), fuelAndAC[1]);
            car.addSpecifications(carDetails.get(2),carDetails.get(3), carDetails.get(4), fuelAndAC, score);
            allCarDetails.add(String.join(" - ", carDetails));
            carDetails.clear();
        }
    }

    public int setVehicleScore(String transmission, String AC) {
        int score = 0;
        if(transmission.equals("Manual")){
            score = score + 1;
        } else if(transmission.equals("Automatic")){
            score = score + 5;
        }
        if(!AC.contentEquals("no AC")){
            score = score + 2;
        }
        return score;
    }

    public void printRankedVehicleSuppliers() {
        ArrayList<String> carTypes = new ArrayList<>();
        vehicles.sort(Comparator.comparingDouble(Vehicle::getRating).reversed());
        for (Vehicle vehicle : vehicles) {
            String currentType = vehicle.getCarType();
            if(!carTypes.contains(currentType)){
                System.out.println(vehicle.getCarName() + " - " + currentType + " - " +
                                       vehicle.getSupplier() + " - " + vehicle.getRating());
                carTypes.add(currentType);
            } }

    }

    public void printOverallScoring() {
        vehicles.sort(Comparator.comparingDouble(Vehicle::getOverallScore).reversed());
        for(Vehicle vehicle : vehicles) {
            System.out.println(vehicle.getCarName() + " - " + vehicle.getScore() + " - " +
             vehicle.getRating() + " - " + vehicle.getOverallScore());
        }

    }

    //Task 4

}
