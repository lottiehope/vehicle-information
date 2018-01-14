package web;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class VehicleSpecification {

    JSONObject specification            =   new JSONObject();
    private final String CAR_TYPES      =   "CarTypes";
    private final String DOORS          =   "DoorsAndType";
    private final String TRANSMISSION   =   "Transmission";
    private final String FUEL           =   "FuelAirCon";

    public VehicleSpecification() {
        getFullVehicleSpecifications();
    }

    public void getFullVehicleSpecifications() {
        FileReader reader = null;
        try {
            reader = new FileReader("resources/VehicleDetails.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONTokener tokener = new JSONTokener(reader);
        specification = new JSONObject(tokener);
    }

    public String getVehicleType(String typeIdentifier) {
        JSONObject carType = specification.getJSONObject(CAR_TYPES);

        if(carType.has(typeIdentifier)) {
            return carType.getString(typeIdentifier);
        }

        return "No type info";
    }

    public String getVehicleDoors(String doorsIdentifier) {
        if(doorsIdentifier.equals("X")) return "Special";
        JSONObject doors = specification.getJSONObject(DOORS);

        if(doors.has(doorsIdentifier)) {
            return doors.getString(doorsIdentifier);
        }

        return "No door info";
    }

    public String getVehicleTransmission(String transmissionIdentifier) {
        JSONObject transmission = specification.getJSONObject(TRANSMISSION);

        if(transmission.has(transmissionIdentifier)) {
            return transmission.getString(transmissionIdentifier);
        }

        return "No transmission info";
    }

    public String[] getVehicleFuelAndAC(String fuelIdentifier) {
        JSONObject fuel = specification.getJSONObject(FUEL);

        if(fuel.has(fuelIdentifier)) {
            return fuel.getString(fuelIdentifier).split("/");
        }

        String[] noData = {"No fuel info", "No AC info"};
        return noData;
    }



}
