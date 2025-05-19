package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
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
                case 10: //Sell/Lease a vehicle
                    sellOrLeaseVehicleMenu();
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
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("➖ Vehicle Remove Request");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━");
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

    //sell or lease menu
    private void sellOrLeaseVehicleMenu() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🚗 Buy or Lease Request");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━");

        System.out.println("🚗 Press [1]  ➤ Buy a vehicle");
        System.out.println("🏠 Press [2]  ➤ Lease a vehicle");
        System.out.println("🔙 Press [0]  ➤ Back to main menu");

        System.out.print("👉 Enter your command: ");
        int sellLeaseCommand = checkIntInput();

        do {
            switch (sellLeaseCommand) {
                case 1: //Sales Contract - buy car
                    processSalesContactRequest();
                    return;
                case 2: //Lease Contact - lease car
                    processLeaseContractRequest();
                    return;
                case 0: //Back to main menu
                    System.out.println("Going back to main menu");
                    return;
                default:
                    System.out.print("⚠️ Invalid choice, please try again: ");
                    sellLeaseCommand = checkIntInput();
            }
        } while(sellLeaseCommand != 0);
    }

    //write up sales contract
    private void processSalesContactRequest() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━");
        System.out.println("📄 Sales Contract");
        System.out.println("━━━━━━━━━━━━━━━━━━");

//        System.out.println("✨ You will be prompted to provide the VIN of the vehicle you'd like to purchase,");
//        System.out.println("✨ and to choose whether you'd like to finance it. 🚗💰");

        System.out.print("👉 Enter Your Name: ");
        String customerName = checkStringInput();

        System.out.print("👉 Enter Email: ");
        String customerEmail = checkStringInput();

        System.out.print("👉 Enter VIN: ");
        int vin = checkIntInput();

        Vehicle foundVehicle = dealership.getVehicleByVin(vin);

        //display vehicles filtered by vin
        if(foundVehicle == null) {
            System.out.println("📭 No vehicles found with that VIN");
            return; //exit out of method
        }
        else {
            System.out.println("🚗 You've chosen the following vehicle:");
            System.out.println(foundVehicle);
        }

        System.out.print("💰 Would you like to finance this vehicle? (Yes/No): ");
        boolean isFinanced = checkYesOrNoInput();

        //format current date
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDate.format(formatter);

        //create sales contract
        SalesContract salesContract = new SalesContract(formattedDate, customerName, customerEmail, foundVehicle, isFinanced);

        //save to contracts.csv
        ContractFileManager.saveContract(salesContract);

//        System.out.println("Successfully purchased - check paper work");
        //print receipt to user
        printReceipt(salesContract, foundVehicle);

        //remove vehicle after purchase
        dealership.removeVehicle(foundVehicle);
        DealershipFileManager.saveDealership(dealership);
    }

    //write up lease contract
    private void processLeaseContractRequest() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━");
        System.out.println("📄 Lease Contract");
        System.out.println("━━━━━━━━━━━━━━━━━━");

        System.out.print("👉 Enter Your Name: ");
        String customerName = checkStringInput();

        System.out.print("👉 Enter Email: ");
        String customerEmail = checkStringInput();

        System.out.print("👉 Enter VIN: ");
        int vin = checkIntInput();

        Vehicle foundVehicle = dealership.getVehicleByVin(vin);

        //display vehicles filtered by vin
        if(foundVehicle == null) {
            System.out.println("📭 No vehicles found with that VIN");
            return; //exit out of method
        }
        else {
            System.out.println("🚗 You've chosen the following vehicle:");
            System.out.println(foundVehicle);
        }

        //format current date
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDate.format(formatter);

        //create lease contract
        LeaseContract leaseContract = new LeaseContract(formattedDate, customerName, customerEmail, foundVehicle);

        //save to contracts.csv
        ContractFileManager.saveContract(leaseContract);

//        System.out.println("Successfully purchased - check paper work");

        //print receipt to user
        printReceipt(leaseContract, foundVehicle);

        //remove vehicle after purchase
        dealership.removeVehicle(foundVehicle);
        DealershipFileManager.saveDealership(dealership);
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
        System.out.println("➖ Press [10] ➤ Buy/Lease vehicle");
        System.out.println("🏁 Press [0] ➤ Exit");
    }

    //print receipt of sale purchase/lease
    private void printReceipt(Contract contract, Vehicle foundVehicle) {
        if(contract instanceof SalesContract salesContract) {
            String isFinanced;
            if(salesContract.isFinanced()) {
                isFinanced = "YES";
            }
            else {
                isFinanced = "NO";
            }

            String firstLine = String.format(
                    "\n━━━━━━━━━━━━━━━━\n" +
                    "🧾 SALE RECEIPT\n" +
                    "━━━━━━━━━━━━━━━━\n" +
                    "📅 Date:          %s\n" +
                    "👤 Customer:      %s\n" +
                    "📧 Email:         %s\n\n" +

                    "🚗 Vehicle Details:\n" +
                    "   🔢 VIN:         %d\n" +
                    "   📆 Year:        %d\n" +
                    "   🏷️ Make:        %s\n" +
                    "   🚘 Model:       %s\n" +
                    "   🚙 Type:        %s\n" +
                    "   🎨 Color:       %s\n" +
                    "   🛣️ Odometer:    %,d miles\n" +
                    "   💵 Price:       $%.2f\n\n" +

                    "📋 Fees & Taxes:\n" +
                    "   🧾 Sales Tax:   $%.2f\n" +
                    "   📋 Rec. Fee:    $%.2f\n" +
                    "   📦 Proc. Fee:   $%.2f\n\n" +

                    "💰 Total Price:   $%.2f\n" +
                    "💳 Financed:      %s\n" +
                    "📆 Monthly Pmt:   $%.2f\n" +
                    "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                    "✅ Thank you for your purchase!\n",
                    salesContract.getDate(),
                    salesContract.getCustomerName(),
                    salesContract.getCustomerEmail(),

                    foundVehicle.getVin(),
                    foundVehicle.getYear(),
                    foundVehicle.getMake(),
                    foundVehicle.getModel(),
                    foundVehicle.getVehicleType(),
                    foundVehicle.getColor(),
                    foundVehicle.getOdometer(),
                    foundVehicle.getPrice(),

                    salesContract.calcSalesTax(),
                    salesContract.getRecordingFee(),
                    salesContract.getProcessingFee(),

                    salesContract.getTotalPrice(),
                    isFinanced,
                    salesContract.getMonthlyPayment()
            );

            System.out.println(firstLine);

        }
        else if (contract instanceof LeaseContract leaseContract) {

            String firstLine = String.format(
                    "\n━━━━━━━━━━━━━━━━━\n" +
                    "📄 LEASE RECEIPT\n" +
                    "━━━━━━━━━━━━━━━━━\n" +
                    "📅 Date:          %s\n" +
                    "👤 Customer:      %s\n" +
                    "📧 Email:         %s\n\n" +

                    "🚗 Vehicle Details:\n" +
                    "   🔢 VIN:         %d\n" +
                    "   📆 Year:        %d\n" +
                    "   🏷️ Make:        %s\n" +
                    "   🚘 Model:       %s\n" +
                    "   🚙 Type:        %s\n" +
                    "   🎨 Color:       %s\n" +
                    "   🛣️ Odometer:    %,d miles\n" +
                    "   💵 Price:       $%.2f\n\n" +

                    "📋 Lease Details:\n" +
                    "   📉 End Value:   $%.2f\n" +
                    "   📦 Lease Fee:   $%.2f\n\n" +

                    "💰 Total Price:   $%.2f\n" +
                    "📆 Monthly Pmt:   $%.2f\n" +
                    "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                    "✅ Thank you for leasing with us!\n",

                    leaseContract.getDate(),
                    leaseContract.getCustomerName(),
                    leaseContract.getCustomerEmail(),

                    foundVehicle.getVin(),
                    foundVehicle.getYear(),
                    foundVehicle.getMake(),
                    foundVehicle.getModel(),
                    foundVehicle.getVehicleType(),
                    foundVehicle.getColor(),
                    foundVehicle.getOdometer(),
                    foundVehicle.getPrice(),

                    leaseContract.getExpectedEndingValue(),
                    leaseContract.getLeaseFee(),

                    leaseContract.getTotalPrice(),
                    leaseContract.getMonthlyPayment()
            );
            System.out.println(firstLine);


        }
    }



    /* Check input validations */
    //validate only yes or no option
    public static boolean checkYesOrNoInput() {
        while (true) {
            String userInput = scanner.nextLine().trim().toLowerCase();

            if(userInput.equals("yes") || userInput.equals("y")) {
                return true;
            }
            else if(userInput.equals("no") || userInput.equals("n")) {
                return false;
            }
            else {
                System.out.print("⚠️ Invalid input, try again: ");
            }
        }
    }

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

    //TODO: maybe add a method for string only separate from string with anything even numbers

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
