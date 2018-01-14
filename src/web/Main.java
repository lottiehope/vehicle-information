package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;


/**
 * Instantiating class for Vehicle-Information. A more in-depth description of what can be done, see the class
 * definition for DataRetriever.java
 * Responsible for starting the Spring application and printing to console.
 * */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
        FileParser parser = new FileParser("http://www.rentalcars.com/js/vehicles.json");
        ArrayList<Vehicle> vehicles = parser.getVehicles();
        DataRetriever retriever = new DataRetriever(vehicles);
        retriever.printVehicleNamesAndPrices();
        retriever.printVehicleSpecifications();
        retriever.printRankedVehicleSuppliers();
        retriever.printOverallScoring();

    }

}
