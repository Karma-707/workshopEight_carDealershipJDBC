package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    static Scanner scanner = new Scanner(System.in);

    private void init() {
        //load dealership details
        this.dealership = DealershipFileManager.getDealership();
    }

    //Constructor
    public UserInterface() {
        init();
    }

    //Display all
    public void display() {
        clearLogsFile();
        int mainMenuCommand;

        do {
            printMenu();

            System.out.print("👉 Enter your command: ");
            mainMenuCommand = checkIntInput();

            //options switches
            switch(mainMenuCommand) {
                case 1: //Get by price
                    processGetByPriceRequest();
                    break;
                case 2: //Get by make/model
                    processGetByMakeModelRequest();
                    break;
                case 3: //Get by year range
                    processGetByYearRequest();
                    break;
                case 4: //Get by color
                    processGetByColorRequest();
                    break;
                case 5: //Get by mileage range
                    processGetByMileageRequest();
                    break;
                case 6: //Get by type (car, truck, SUV, van)
                    processGetByVehicleTypeRequest();
                    break;
                case 7: //Get ALL vehicles
                    processGetAllVehiclesRequest();
                    break;
                case 8: //Add vehicle
                    processAddVehicleRequest();
                    break;
                case 9: //Remove vehicle
                    processRemoveVehicleRequest();
                    break;
                case 0: //Exits
                    System.out.println("👋 Exiting...");
                    scanner.close();
                    break;
                default:
                    System.out.println("⚠️ Invalid choice, please try again");
            }

        } while (mainMenuCommand != 0);
    }

    //print price by user range
    private void processGetByPriceRequest() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("💰 Price Request Filter");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━");

        //ask the user for a starting price & ending price
        System.out.print("👉 Enter Minimum Price: ");
        double minPrice = checkDoubleInput();
        System.out.print("👉 Enter Maximum Price: ");
        double maxPrice = checkDoubleInput();

        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);

        //display vehicles filtered by price
        if(filteredVehicles.isEmpty()) {
            System.out.println("📭 No vehicles found in that price range");
        }
        else {
            System.out.println("\n📊 Displaying Filtered Price Range");
            displayVehicles(filteredVehicles);
        }
    }

    //print model by user request
    private void processGetByMakeModelRequest() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🏷️ Make/Model Request Filter");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        //ask user for model to filter
        System.out.print("👉 Enter Make: ");
        String make = scanner.nextLine();
        System.out.print("👉 Enter Model: ");
        String model = scanner.nextLine();

        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByMakeModel(make, model);

        //display vehicles filtered by make/model
        if(make.isEmpty() && model.isEmpty()) {
            System.out.println("❌ Oops! You left both the make and model blank. 🛠️ Please enter at least one to filter vehicles.");
        }
        else if (!make.isEmpty() && model.isEmpty()) {
            System.out.println("\n🔍 Displaying Filtered Make Range");
            if(filteredVehicles.isEmpty()) {
                System.out.println("❌ Sorry no vehicle of your search");
            }
            else {
                displayVehicles(filteredVehicles);
            }
        }
        else if (make.isEmpty() && !model.isEmpty()) {
            System.out.println("\n🔍 Displaying Filtered Model Range");
            if(filteredVehicles.isEmpty()) {
                System.out.println("❌ Sorry no vehicle of your search");
            }
            else {
                displayVehicles(filteredVehicles);
            }
        }
        else {
            System.out.println("\n🔍 Displaying Filtered Make & Model Range");
            if(filteredVehicles.isEmpty()) {
                System.out.println("❌ Sorry no vehicle of your search");
            }
            else {
                displayVehicles(filteredVehicles);
            }
        }

    }

    //print year by user request
    private void processGetByYearRequest() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("📅 Year Request Filter");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━");

        //ask user for year range to filter
        System.out.print("👉 Enter Minimum Year: ");
        double minYear = checkDoubleInput();
        System.out.print("👉 Enter Maximum Year: ");
        double maxYear = checkDoubleInput();

        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByYear(minYear, maxYear);

        //display vehicles filtered by year range
        if(filteredVehicles.isEmpty()) {
            System.out.println("📭 No vehicles found in that year range");
        }
        else {
            System.out.println("\n📊 Displaying Filtered Year Range");
            displayVehicles(filteredVehicles);
        }
    }

    //print color by user request
    private void processGetByColorRequest() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🎨 Color Request Filter");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━");
//        scanner.nextLine(); //eat white space

        //ask user for color to filter
        System.out.print("👉 Enter Color: ");
        String color = checkStringInput();

        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByColor(color);

        //display vehicles filtered by color
        if(filteredVehicles.isEmpty()) {
            System.out.println("📭 No vehicles found in that color");
        }
        else {
            System.out.println("\n📊 Displaying Filtered Color");
            displayVehicles(filteredVehicles);
        }
    }

    //display vehicles filtered by mileage
    private void processGetByMileageRequest() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🛣️ Mileage Request Filter");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━");

        //ask user for mileage range to filter
        System.out.print("👉 Enter Minimum Mileage: ");
        double minMileage = checkDoubleInput();
        System.out.print("👉 Enter Maximum Mileage: ");
        double maxMileage = checkDoubleInput();

        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);

        //display vehicles filtered by mileage range
        if(filteredVehicles.isEmpty()) {
            System.out.println("📭 No vehicles found in that mileage range");
        }
        else {
            System.out.println("\n📊 Displaying Filtered Mileage Range");
            displayVehicles(filteredVehicles);
        }
    }

    //display vehicles filtered by type
    private void processGetByVehicleTypeRequest() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🚘 Vehicle Type Request Filter");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
//        scanner.nextLine(); //eat white space

        //ask user for vehicle type to filter
        System.out.print("👉 Enter Vehicle Type: ");
        String vehicleType = checkStringInput();

        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByType(vehicleType);

        //display vehicles filtered by type
        if(filteredVehicles.isEmpty()) {
            System.out.println("📭 No vehicles found in that type");
        }
        else {
            System.out.println("\n📊 Displaying Filtered Vehicle Type");
            displayVehicles(filteredVehicles);
        }
    }

    //print all vehicles
    private void processGetAllVehiclesRequest() {
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        System.out.println("\n📋 Displaying All Vehicles");
        displayVehicles(vehicles);
    }

    //add vehicles to csv and dealership list
    private void processAddVehicleRequest() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("➕ Vehicle Add Request");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.print("👉 Enter VIN: ");
        int vin = checkIntInput();

        System.out.print("👉 Enter Year (e.g, 2021): ");
        int year = checkIntInput();

        System.out.print("👉 Enter Make: ");
        String make = checkStringInput();

        System.out.print("👉 Enter Model: ");
        String model = checkStringInput();

        System.out.print("👉 Enter Vehicle Type: ");
        String vehicleType = checkStringInput();

        System.out.print("👉 Enter Color: ");
        String color = checkStringInput();

        System.out.print("👉 Enter Odometer: ");
        int odometer = checkIntInput();

        System.out.print("👉 Enter Price: ");
        double price = checkDoubleInput();

        Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(newVehicle);
        DealershipFileManager.saveDealership(dealership);
        System.out.println("✅ Vehicle added successfully!");
    }

    //remove vehicles to csv and dealership list
    private void processRemoveVehicleRequest() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("➖ Vehicle Remove Request");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.print("👉 Enter VIN: ");
        int vin = checkIntInput();

        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        Vehicle vehicleToRemove = null;

        //find vehicle using VIN
        for(Vehicle vehicle: vehicles) {
            if(vehicle.getVin() == vin) {
                vehicleToRemove = vehicle;
                break;
            }
        }

        //check if there is such vehicle and display results
        if(vehicleToRemove == null) {
            System.out.println("📭 No vehicles found with that VIN");
        }
        else {
            dealership.removeVehicle(vehicleToRemove);
            DealershipFileManager.saveDealership(dealership);
            System.out.println("✅ Vehicle removed successfully!");
        }
    }


    //helper method to print vehicles in array list
    private static void displayVehicles(ArrayList<Vehicle> vehicles) {
        for(Vehicle vehicle: vehicles) {
            System.out.print(vehicle);
        }
    }


    //print mainMenu
    private static void printMenu() {
        System.out.println("\n\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("⚜️  ADA Vehicle Operations Terminal ⚜️");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        System.out.println("🚗 Press [1] ➤ Get by price");
        System.out.println("🚙 Press [2] ➤ Get by make/model");
        System.out.println("🚕 Press [3] ➤ Get by year range");
        System.out.println("🚘 Press [4] ➤ Get by color");
        System.out.println("🏎️ Press [5] ➤ Get by mileage range");
        System.out.println("🚐 Press [6] ➤ Get by type (car, truck, SUV, van)");
        System.out.println("🛻 Press [7] ➤ Get ALL vehicles");
        System.out.println("➕ Press [8] ➤ Add vehicle");
        System.out.println("➖ Press [9] ➤ Remove vehicle");
        System.out.println("🏁 Press [0] ➤ Exit");
    }


    /* Check input validations */

    //validate int input - data type
    private static int checkIntInput() {
        while(true) {
            String userInput = scanner.nextLine().trim();

            //don't allow -0 as an option
            if(userInput.equalsIgnoreCase("-0")) {
                System.out.print("🚫 You can't enter 0 as a value... Try again: ");
                continue; //go back to top of loop
            }

            try {
                if(Integer.parseInt(userInput) < 0) {
                    System.out.print("⚠️ You can't put a negative value... Try again!: ");
                }
                else {
                    return Integer.parseInt(userInput);
                }
            } catch (NumberFormatException e) {
                System.out.print("⚠️ Oops, that's not a valid number! Please try again: ");
                writeErrorsToLogsFile(e);
            }

        }
    }

    //validate string input not empty
    public static String checkStringInput() {
        while(true) {
            String userInput = scanner.nextLine().trim();

            if(!userInput.isEmpty()) {
                return userInput;
            }
            else {
                System.out.print("⚠️ You left it blank... Try again!: ");
            }
        }
    }

    //validate double input - data type
    private static double checkDoubleInput() {
        while(true) {
            String userInput = scanner.nextLine().trim();

            try {
                if(Double.parseDouble(userInput) < 0) {
                    System.out.print("⚠️ You can't put a negative value... Try again!: ");
                }
                else {
                    return Double.parseDouble(userInput);
                }
            } catch (NumberFormatException e) {
                System.out.print("⚠️ Oops, that's not a valid number! Please try again: ");
                writeErrorsToLogsFile(e);
            }
        }
    }

    /* Logs Methods */

    //write to log file of error/crashes
    public static void writeErrorsToLogsFile(Exception e) {
        try {
            LocalDateTime timeStamp = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatedTimeStamp = timeStamp.format(formatter);

            //write to logs the error
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("exceptions.log",true));
            bufferedWriter.write("Time of occurrence: " + formatedTimeStamp + "\t" + e.getMessage() + "\n");
            bufferedWriter.close();
        } catch (IOException ex) {
            writeErrorsToLogsFile(e);
        }
    }

    //make the logs empty
    private static void clearLogsFile() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("exceptions.log", false));
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
