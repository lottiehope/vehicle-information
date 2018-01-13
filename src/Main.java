import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        FileParser parser = new FileParser("http://www.rentalcars.com/js/vehicles.json");
        ArrayList<Vehicle> vehicles = parser.getVehicles();
        DataRetriever retriever = new DataRetriever(vehicles);
        //retriever.printVehicleNamesAndPrices();
        //retriever.printVehicleSpecifications();
        //retriever.printRankedVehicleSuppliers();
        retriever.printOverallScoring();
    }

}
