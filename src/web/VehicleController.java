package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;

/**
 * Spring controller to respond to requests via URL. Can be accessed by running the project and entering
 * localhost:8080/vehicle into the search bar, followed by one of the options defined in this class, e.g. /price.
 * */
@Controller
@RequestMapping("/vehicle")
public class VehicleController {

    private DataRetriever retriever;

    /**
     * Initialise the class and create a DataRetriever which contains a list of all vehicles defined in the JSON.
     * */
    public VehicleController() {
        FileParser parser = new FileParser("http://www.rentalcars.com/js/vehicles.json");
        ArrayList<Vehicle> vehicles = parser.getVehicles();
        retriever = new DataRetriever(vehicles);
    }

    /**
     * Retrieves the names and prices of all vehicles in the JSON file previously parsed.
     * To access this method type localhost:8080/vehicle/price into a browser.
     * @return String The JSON corresponding to each vehicle's name and price.
     * */
    @RequestMapping(value = "/price", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getVehicleNamesAndPrices() {

        return retriever.getVehicleNamesAndPrices().toString();

    }

    /**
     * Retrieves the specification for each vehicle defined in the JSON file previously parsed.
     * To access this method type localhost:8080/vehicle/specification into a browser.
     * @return String The JSON corresponding to each vehicle's specification.
     * */
    @RequestMapping(value = "/specification", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getVehicleSpecifications() {

        return retriever.getAllVehicleSpecifications().toString();

    }

    /**
     * Retrieves the best supplier for each car type defined in the JSON file previous parsed.
     * To access this method type localhost:8080/vehicle/supplierRating into a browser.
     * @return String The JSON corresponding to each car type and it's supplier rating.
     * */
    @RequestMapping(value = "/supplierRating", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getVehicleSupplierRatings() {

        return retriever.getRankedVehicleSuppliers().toString();

    }

    /**
     * Retrieves the overall rating for each vehicle defined in the JSON file previously parsed.
     * To access this method type localhost:8080/vehicle/overallRating into a browser.
     * @return String The JSON corresponding to each vehicle and it's overall rating.
     * */
    @RequestMapping(value = "/overallRating", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getVehicleOverallRating() {

        return retriever.getOverallScoring().toString();

    }

}
