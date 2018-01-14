package web;

import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.*;

/**
 * Class which takes a vehicle's sipp and finds the specification for the vehicle.
 * Specification details can be seen in /resources/VehicleDetails.json
 * */
public class VehicleSpecification {

    private JSONObject specification    =   new JSONObject();
    private final String CAR_TYPES      =   "CarTypes";
    private final String DOORS          =   "DoorsAndType";
    private final String TRANSMISSION   =   "Transmission";
    private final String FUEL           =   "FuelAirCon";

    /**
     * Initialises the class and begins the parsing of the VehicleDetails.json file.
     * */
    public VehicleSpecification(String fileSource) {

        getFullVehicleSpecifications(fileSource);

    }

    /**
     * Takes a file location and creates a JSONObject which holds the information found in the file.
     * @param fileSource The location of the file.
     * */
    public void getFullVehicleSpecifications(String fileSource) {

        FileReader reader = null;

        try {

            reader = new FileReader(fileSource);

        } catch (FileNotFoundException e) {
            //Log error

            //Print to console for demo...
            System.out.println("Error: " + e.getMessage());
        }

        JSONTokener tokener = new JSONTokener(reader);
        specification = new JSONObject(tokener);

    }

    /**
     * Checks to see if the vehicle type indicated in the vehicle's sipp is valid and then returns the value
     * associated with it.
     * @param typeIdentifier A one character string which indicates which car type the vehicle is.
     * @return String The type associated with that vehicle.
     * */
    public String getVehicleType(String typeIdentifier) {

        JSONObject carType = specification.getJSONObject(CAR_TYPES);

        if(carType.has(typeIdentifier)) {

            return carType.getString(typeIdentifier);

        }

        return "No type info";
    }

    /**
     * Checks to see if the number of doors/type that the vehicle has indicated in the vehicle's sipp is valid
     * and then returns the value associated with it.
     * @param doorsIdentifier A one character string which indicates how many doors/the type that the vehicle has.
     * @return String The number of doors/type associated with that vehicle.
     * */
    public String getVehicleDoors(String doorsIdentifier) {

        if(doorsIdentifier.equals("X")) return "Special";

        JSONObject doors = specification.getJSONObject(DOORS);

        if(doors.has(doorsIdentifier)) {

            return doors.getString(doorsIdentifier);

        }

        return "No door info";
    }

    /**
     * Checks to see if the vehicle transmission indicated in the vehicle's sipp is valid and then returns the value
     * associated with it.
     * @param transmissionIdentifier A one character string which indicates which transmission the vehicle has.
     * @return String The transmission associated with that vehicle.
     * */
    public String getVehicleTransmission(String transmissionIdentifier) {

        JSONObject transmission = specification.getJSONObject(TRANSMISSION);

        if(transmission.has(transmissionIdentifier)) {

            return transmission.getString(transmissionIdentifier);

        }

        return "No transmission info";
    }

    /**
     * Checks to see if the fuel and AC type indicated in the vehicle's sipp is valid and then returns the value
     * associated with it.
     * @param fuelIdentifier A one character string which indicates what fuel the vehicle uses and whether it has AC.
     * @return String[] The fuel and AC values associated with that vehicle
     * */
    public String[] getVehicleFuelAndAC(String fuelIdentifier) {

        JSONObject fuel = specification.getJSONObject(FUEL);

        if(fuel.has(fuelIdentifier)) {

            return fuel.getString(fuelIdentifier).split("/");

        }

        String[] noData = {"No fuel info", "No AC info"};
        return noData;

    }

}
