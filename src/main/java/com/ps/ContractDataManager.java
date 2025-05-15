package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ContractDataManager {
    public static void saveContract(Contract contract) {

        if(contract instanceof SalesContract) {
            //TODO: write to sales contract file
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("contracts.csv"));

                String firstLine = String.format("SALE|%s|%s|%s\n",
                        contract.getDate(),
                        contract.getCustomerName(),
                        contract.getCustomerEmail(),
                        contract.getVehicleChosen().getVin(),
                        contract.getVehicleChosen().getYear(),
                        contract.getVehicleChosen().getMake(),
                        contract.getVehicleChosen().getModel(),
                        contract.getVehicleChosen().getVehicleType(),
                        contract.getVehicleChosen().getColor(),
                        contract.getVehicleChosen().getOdometer(),
                        contract.getVehicleChosen().getPrice(),
                        ((SalesContract) contract).getSalesTax()


                );
                bufferedWriter.write(firstLine);

                ArrayList<Vehicle> vehicles = dealership.getAllVehicles();

                for(Vehicle vehicle: vehicles) {
                    String vehicleLine = String.format("%d|%d|%s|%s|%s|%s|%d|%.2f\n",
                            vehicle.getVin(),
                            vehicle.getYear(),
                            vehicle.getMake(),
                            vehicle.getModel(),
                            vehicle.getVehicleType(),
                            vehicle.getColor(),
                            vehicle.getOdometer(),
                            vehicle.getPrice()
                    );
                    bufferedWriter.write(vehicleLine);
                }

                bufferedWriter.close();
            } catch (IOException e) {
                UserInterface.writeErrorsToLogsFile(e);
            }
        }
        else if (contract instanceof LeaseContract) {
            //TODO: write to lease contract file

        }




    }
}
