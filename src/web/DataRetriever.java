package web;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Comparator;


/** Class responsible for returning the content for the following tasks:
 *
 1 - Retrieve all the vehicles and their prices

 2 - Retrieve the specification for all vehicles

 3 - Retrieve the highest rated supplier for each vehicle type

 4 - Retrieve all the vehicles and their overall score
 *
 * It will either return a JSONArray containing the data or it will print it to the console.
 * */
public class DataRetriever {

    private VehicleSpecification vehicleInfoSource = new VehicleSpecification("resources/VehicleDetails.json");
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private JSONArray allVehicleSpecifications = new JSONArray();

    /**
     * Initialise data retriever by taking a list of vehicles to perform actions on.
     * It also gets the specification for each vehicle based on the sipp
     * @param vehiclesList The list of vehicles to be used inside this class.
     * */
    public DataRetriever(ArrayList<Vehicle> vehiclesList) {

        vehicles = vehiclesList;
        setSpecifications();

    }

    /**
     * Functionality for the first task stated in class definition.
     * It sorts the vehicles alphabetically A-Z.
     * @return JSONArray Containing an array of the vehicle information relevant to this task: Name and Price.
     **/
    protected JSONArray getVehicleNamesAndPrices() {

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

    protected void printVehicleNamesAndPrices() {

        System.out.println();
        JSONArray vehiclePrices = getVehicleNamesAndPrices();

        for (Object car: vehiclePrices) {

            JSONObject JSONCar = (JSONObject)car;
            System.out.println(JSONCar.get("Name") + " - " + JSONCar.get("Price"));

        }

    }

    protected void printVehicleSpecifications() {

        System.out.println();

        for (Object car: allVehicleSpecifications) {

            JSONObject JSONCar = (JSONObject)car;
            System.out.println(JSONCar.get("Name") + " - " + JSONCar.get("Sipp") + " - " + JSONCar.get("CarType") +
                    " - " + JSONCar.get("Doors") + " - " + JSONCar.get("Transmission") + " - " + JSONCar.get("Fuel")
                    + " - " + JSONCar.get("AC"));

        }

    }

    /**
     * This is called whenever this class is created. It retrieves the specification for each vehicle in the
     * ArrayList defined when the class is instantiated. It then stores this information in the instance of the Vehicle
     * that it is investigating. It also creates a score for each vehicle.
     * */
    protected void setSpecifications() {

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

    /**
     * Getter for the second task defined in the class definition.
     * @return JSONArray Containing JSONObjects for each vehicle and the corresponding specifications.
     * */
    protected JSONArray getAllVehicleSpecifications() {

        return allVehicleSpecifications;

    }

    /**
     * In order to set and overall score for each vehicle, it is possible to add values based on transmission type
     * and whether or not it has AC.
     * @param transmission  The type of transmission for the vehicle (Manual or Automatic)
     * @param AC            Whether the vehicle has AC or not
     * @return int          The value to be added to the overall score for a vehicle.
     * */
    private int setVehicleScore(String transmission, String AC) {

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

    protected void printRankedVehicleSuppliers() {

        JSONArray ratedVehicles = getRankedVehicleSuppliers();
        System.out.println();

        for(Object car : ratedVehicles) {

            JSONObject vehicle = (JSONObject) car;
            System.out.println(vehicle.get("Name") + " - " + vehicle.get("Type") + " - " + vehicle.get("Supplier") +
                " - " + vehicle.get("Rating"));

        }

    }

    /**
     * Creates a JSONArray which is ordered by supplier rating descending. It will find the best supplier for each
     * different car type. It then stores the relevant information required for task 3, defined in the class
     * definition: vehicle name, type, supplier and rating for each vehicle type.
     * @return JSONArray Containing a JSONObject for each car type.
     * */
    protected JSONArray getRankedVehicleSuppliers() {

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

    /**
     * Sorts the list of vehicles on overall score (combination of the car's rating, based on transmission and AC, and
     * the supplier rating. It creates a JSONObject for each vehicle and stores the relevant information for task 4
     * defined in the class definition.
     * @return JSONArray Containing the list of vehicles in order of overall score descending.
     * */
    protected JSONArray getOverallScoring() {

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

    protected void printOverallScoring() {

        JSONArray allVehicles = getOverallScoring();
        System.out.println();

        for(Object car : allVehicles) {

            JSONObject vehicle = (JSONObject) car;
            System.out.println(vehicle.get("Name") + " - " + vehicle.get("Score") + " - " + vehicle.get("SupplierRating")
                    + " - " + vehicle.get("OverallRating"));

        }

    }

}
