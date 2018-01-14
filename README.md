# Vehicle Information

This project takes a JSON file from a URL containing information about different vehicles.

It takes this information and performs 4 different tasks:

1 - Retrieve all the vehicles and their prices

2 - Retrieve the specification for all vehicles

3 - Retrieve the highest rated supplier for each vehicle type

4 - Retrieve all the vehicles and their overall score

NB: Overall score = Supplier rating + Car rating (A score based on transmission type and fuel/AC)

## Running the project

This project runs and prints to the console and can be accessed via web API which uses the Spring framework.

### To print to console:

Open the project and run it. This will print out each one of the tasks to the console.

You can comment these out in the ```Main.java``` class.

### Accessing the API: 

Once the project is running, open a browser and navigate to localhost:8080.

In order to run the four tasks, add the following to the URL:

1 - /price

2 - /specification

3 - /supplierRating

4 - /overallRating

## Run the tests

To run the tests, navigate to ```/src/tests/CarTests.java```.

Then run the tests.