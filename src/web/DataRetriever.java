package web;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Comparator;

public class DataRetriever {

    VehicleSpecification vehicleInfoSource = new VehicleSpecification();
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    JSONArray allVehicleSpecifications = new JSONArray();

    public DataRetriever(ArrayList<Vehicle> vehiclesList) {
        vehicles = vehiclesList;
        setSpecifications();
    }

    public JSONArray getVehicleNamesAndPrices() {
        JSONArray namesAndPrices = new JSONArray();
        vehicles.sort(Comparator.comparing(Vehicle::getCarName));
        for (Vehicle car: vehicles) {
            JSONObject nameAndPrice = new JSONObject();
            nameAndPrice.put("Name", car.getCarName());
            nameAndPrice.put("Price", car.getPrice());
            namesAndPrices.put(nameAndPrice);
        }
        return namesAndPrices;
    }

    public void printVehicleNamesAndPrices() {
        JSONArray vehiclePrices = getVehicleNamesAndPrices();
        for (Object car: vehiclePrices) {
            JSONObject JSONCar = (JSONObject)car;
            System.out.println(JSONCar.get("Name") + " - " + JSONCar.get("Price"));
        }
    }

    public void printVehicleSpecifications() {
        for (Object car: allVehicleSpecifications) {
            JSONObject JSONCar = (JSONObject)car;
            System.out.println(JSONCar.get("Name") + " - " + JSONCar.get("Sipp") + " - " + JSONCar.get("CarType") +
                    " - " + JSONCar.get("Doors") + " - " + JSONCar.get("Transmission") + " - " + JSONCar.get("Fuel")
                    + " - " + JSONCar.get("AC"));
        }

    }

    public void setSpecifications() {
        for (Vehicle car : vehicles) {
            String[] sipp = car.getSipp().split("");
            String[] fuelAndAC = vehicleInfoSource.getVehicleFuelAndAC(sipp[3]);
            String type = vehicleInfoSource.getVehicleType(sipp[0]);
            String doors = vehicleInfoSource.getVehicleDoors(sipp[1]);
            String transmission = vehicleInfoSource.getVehicleTransmission(sipp[2]);
            JSONObject vehicle = new JSONObject();
            vehicle.put("Name", car.getCarName());
            vehicle.put("Sipp", car.getSipp());
            vehicle.put("CarType", type);
            vehicle.put("Doors", doors);
            vehicle.put("Transmission", transmission);
            vehicle.put("Fuel", fuelAndAC[0]);
            vehicle.put("AC", fuelAndAC[1]);
            allVehicleSpecifications.put(vehicle);
            int score = setVehicleScore(transmission, fuelAndAC[1]);
            car.addSpecifications(type, doors, transmission, fuelAndAC, score);
        }
    }

    public JSONArray getAllVehicleSpecifications() {
        return allVehicleSpecifications;
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
        JSONArray ratedVehicles = getRankedVehicleSuppliers();
        for(Object car : ratedVehicles) {
            JSONObject vehicle = (JSONObject) car;
            System.out.println(vehicle.get("Name") + " - " + vehicle.get("Type") + " - " + vehicle.get("Supplier") +
                " - " + vehicle.get("Rating"));
        }

    }

    public JSONArray getRankedVehicleSuppliers() {
        vehicles.sort(Comparator.comparing(Vehicle::getRating).reversed());
        ArrayList<String> carTypes = new ArrayList<>();
        JSONArray ratedVehicles = new JSONArray();
        for (Vehicle vehicle : vehicles) {
            String currentType = vehicle.getCarType();
            if(!carTypes.contains(currentType)) {
                JSONObject car = new JSONObject();
                car.put("Name", vehicle.getCarName());
                car.put("Type", currentType);
                car.put("Supplier", vehicle.getSupplier());
                car.put("Rating", vehicle.getRating());
                carTypes.add(currentType);
                ratedVehicles.put(car);
            }
        }
        return ratedVehicles;
    }

    public JSONArray getOverallScoring() {
        vehicles.sort(Comparator.comparingDouble(Vehicle::getOverallScore).reversed());
        JSONArray allOverallScores = new JSONArray();
        for(Vehicle vehicle : vehicles) {
            JSONObject car = new JSONObject();
            car.put("Name", vehicle.getCarName());
            car.put("Score", vehicle.getScore());
            car.put("SupplierRating", vehicle.getRating());
            car.put("OverallRating", vehicle.getOverallScore());
            allOverallScores.put(car);
        }
        return allOverallScores;
    }

    public void printOverallScoring() {
        JSONArray allVehicles = getOverallScoring();
        for(Object car : allVehicles) {
            JSONObject vehicle = (JSONObject) car;
            System.out.println(vehicle.get("Name") + " - " + vehicle.get("Score") + " - " + vehicle.get("SupplierRating")
                    + " - " + vehicle.get("OverallRating"));
        }

    }

}
