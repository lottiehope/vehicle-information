import java.util.ArrayList;
import java.util.Collections;

public class DataRetriever {


    //Task 1
    public void printVehicleNamesAndPrices(ArrayList<Vehicle> vehicles){
        ArrayList<String> namesAndPrices = new ArrayList<>();
        for (Vehicle car: vehicles) {
            String nameAndPrice = car.getCarName() + " - " + car.getPrice().toString();
            namesAndPrices.add(nameAndPrice);
        }
        Collections.sort(namesAndPrices);
        for (String s: namesAndPrices) {
            System.out.println(s);
        }
    }

    //Task 2


    //Task 3


    //Task 4

}
