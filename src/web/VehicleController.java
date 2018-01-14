package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/vehicle")
public class VehicleController {

    private DataRetriever retriever;

    public VehicleController() {
        FileParser parser = new FileParser("http://www.rentalcars.com/js/vehicles.json");
        ArrayList<Vehicle> vehicles = parser.getVehicles();
        retriever = new DataRetriever(vehicles);
    }

    @RequestMapping(value = "/price", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getVehicleNamesAndPrices() {
        return retriever.getVehicleNamesAndPrices().toString();
    }

    @RequestMapping(value = "/specification", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getVehicleSpecifications() {
        return retriever.getAllVehicleSpecifications().toString();
    }

    @RequestMapping(value = "/supplierRating", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getVehicleSupplierRatings() {
        return retriever.getRankedVehicleSuppliers().toString();
    }

    @RequestMapping(value = "/overallRating", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getVehicleOverallRating() {
        return retriever.getOverallScoring().toString();
    }

}
