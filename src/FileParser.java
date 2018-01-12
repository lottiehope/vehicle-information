import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class FileParser {

    private ArrayList<Vehicle> vehicles;

    protected FileParser(String url) {
        JSONObject jsonFile = retrieveJSONFromURL(url);
        vehicles = parseJSON(jsonFile);

    }

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

    protected ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }
}

