import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        FileParser parser = new FileParser("http://www.rentalcars.com/js/vehicles.json");
        ArrayList<Vehicle> vehicles = parser.getVehicles();
//        for (Vehicle car: vehicles) {
//            System.out.println(car.getCarName());
//        }
        DataRetriever dr = new DataRetriever();
        dr.printVehicleNamesAndPrices(vehicles);
    }
}
