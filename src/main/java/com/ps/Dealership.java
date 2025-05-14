package com.ps;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    //Constructors
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    //get vehicles by price range input
    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {
        //make new array list and put filter in it n return that
        ArrayList<Vehicle> filteredVehicles = new ArrayList<>();

        for(Vehicle vehicle: inventory) {
            double vehiclePrice = vehicle.getPrice();
            //add vehicle to array list if within user range
            if((vehiclePrice >= min) && (vehiclePrice <= max)) {
                filteredVehicles.add(vehicle);
            }
        }

        return filteredVehicles;
    }

    //get vehicles by make/model
    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> filteredVehicles = new ArrayList<>();

        for(Vehicle vehicle: inventory) {
            String vehicleMake = vehicle.getMake().toLowerCase();
            String vehicleModel = vehicle.getModel().toLowerCase();

            if (!make.isEmpty() && model.isEmpty()) {
                if(vehicleMake.contains(make.toLowerCase())) {
                    filteredVehicles.add(vehicle);
                }
            } else if (make.isEmpty() && !model.isEmpty()) {
                if(vehicleModel.contains(model.toLowerCase())) {
                    filteredVehicles.add(vehicle);
                }
            }
            else {
                if(vehicleMake.contains(make.toLowerCase()) && vehicleModel.contains(model.toLowerCase())) {
                    filteredVehicles.add(vehicle);
                }
            }
        }
        return filteredVehicles;
    }

    //get vehicle by year range
    public ArrayList<Vehicle> getVehiclesByYear(double min, double max) {
        ArrayList<Vehicle> filteredVehicles = new ArrayList<>();

        for(Vehicle vehicle: inventory) {
            double vehicleYear = vehicle.getYear();
            //add vehicle if within range
            if((vehicleYear >= min) && (vehicleYear <= max)) {
                filteredVehicles.add(vehicle);
            }
        }

        return filteredVehicles;
    }

    //get vehicle by color
    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> filteredVehicles = new ArrayList<>();

        for(Vehicle vehicle: inventory) {
            String vehicleColor = vehicle.getColor().toLowerCase();

            if (vehicleColor.contains(color.toLowerCase())) {
                filteredVehicles.add(vehicle);
            }

        }
        return filteredVehicles;
    }

    //get vehicle by mileage/aka odometer
    public ArrayList<Vehicle> getVehiclesByMileage(double min, double max) {
        ArrayList<Vehicle> filteredVehicles = new ArrayList<>();

        for(Vehicle vehicle: inventory) {
            double vehicleMileage = vehicle.getOdometer();
            //add vehicle if within range
            if((vehicleMileage >= min) && (vehicleMileage <= max)) {
                filteredVehicles.add(vehicle);
            }
        }
        return filteredVehicles;
    }

    //get vehicle by type
    public ArrayList<Vehicle> getVehiclesByType(String vehicleType) {
        ArrayList<Vehicle> filteredVehicles = new ArrayList<>();

        for(Vehicle vehicle: inventory) {
            String type = vehicle.getVehicleType().toLowerCase();

            if(type.contains(vehicleType.toLowerCase())) {
                filteredVehicles.add(vehicle);
            }
        }
        return filteredVehicles;
    }


    //return vehicles list
    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }

    //add vehicle to inventory
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    //remove vehicle from inventory
    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }

    //getter
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
