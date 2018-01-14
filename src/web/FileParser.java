package web;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Class responsible for reading in a JSON file from a URL and creating an ArrayList of Vehicles based on the
 * aforementioned JSON file.
 * */
public class FileParser {

    private ArrayList<Vehicle> vehicles;

    /**
     * Initialises the class and then parses the file and populates an ArrayList of vehicles.
     * @param url The URL to the JSON file.
     * */
    public FileParser(String url) {

        JSONObject jsonFile = retrieveJSONFromURL(url);
        vehicles = parseJSON(jsonFile);

    }

    /**
     * Takes a JSON file from a URL and then creates a JSONObject holding the contents of the JSON file.
     * @param url           The URL to the JSON file.
     * @return JSONObject   The output of parsing the JSON file.
     * */
    private JSONObject retrieveJSONFromURL(String url){

        try {

            InputStream vehiclesJSON = new URL(url).openStream();
            String jsonDump = IOUtils.toString(new InputStreamReader(vehiclesJSON, Charset.forName("UTF-8")));
            JSONObject jsonFile = new JSONObject(jsonDump);
            return jsonFile;

        } catch (IOException e) {

            // Log error ...

            // For demo print to console
            System.out.println("Error: " + e.getMessage());

        }

        return null;
    }

    /**
     * Transforms the JSON into Vehicles and then stores all Vehicles in an ArrayList.
     * @param jsonFile              The JSON to be transformed.
     * @return ArrayList<Vehicle>   The Vehicles retrieved from the JSON.
     * */
    private ArrayList<Vehicle> parseJSON(JSONObject jsonFile) {

        JSONArray vehicles = jsonFile.getJSONObject("Search").getJSONArray("VehicleList");
        ArrayList<Vehicle> listOfVehicles = new ArrayList<>();

        for (Object vehicle : vehicles) {

            JSONObject car = (JSONObject) vehicle;
            Vehicle newCar = new Vehicle(car);
            listOfVehicles.add(newCar);

        }

        return listOfVehicles;
    }

    /**
     * Getter to retrieve the list of all vehicles found in the JSON file.
     * @return ArrayList<Vehicle> The list of all vehicles in the JSON.
     * */
    protected ArrayList<Vehicle> getVehicles() {

        return vehicles;

    }
}

