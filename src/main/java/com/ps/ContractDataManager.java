package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ContractDataManager {
    public static void saveContract(Contract contract) {

        if(contract instanceof SalesContract salesContract) {
            Vehicle chosenVehicle = salesContract.getVehicleChosen();

            //write to sales contract file
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("contracts.csv", true));
                String isFinanced;
                if(salesContract.isFinanced()) {
                    isFinanced = "YES";
                }
                else {
                    isFinanced = "No";
                }

                String firstLine = String.format("SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%f|%f|%d|%d|%f|%s|%f\n",
                        salesContract.getDate(),
                        salesContract.getCustomerName(),
                        salesContract.getCustomerEmail(),
                        chosenVehicle.getVin(),
                        chosenVehicle.getYear(),
                        chosenVehicle.getMake(),
                        chosenVehicle.getModel(),
                        chosenVehicle.getVehicleType(),
                        chosenVehicle.getColor(),
                        chosenVehicle.getOdometer(),
                        chosenVehicle.getPrice(),
                        salesContract.calcSalesTax(),
                        salesContract.getRecordingFee(),
                        salesContract.getProcessingFee(),
                        salesContract.getTotalPrice(),
                        isFinanced,
                        salesContract.getMonthlyPayment()
                );
                bufferedWriter.write(firstLine);

                bufferedWriter.close();
            } catch (IOException e) {
                UserInterface.writeErrorsToLogsFile(e);
            }
        }
        else if (contract instanceof LeaseContract leaseContract) {
            //TODO: write to lease contract file
//            Vehicle chosenVehicle = salesContract.getVehicleChosen();
//
//            //write to lease contract file
//            try {
//                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("contracts.csv", true));
//                String isFinanced;
//                if(salesContract.isFinanced()) {
//                    isFinanced = "YES";
//                }
//                else {
//                    isFinanced = "No";
//                }
//
//                String firstLine = String.format("SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%f|%f|%d|%d|%f|%s|%f\n",
//                        salesContract.getDate(),
//                        salesContract.getCustomerName(),
//                        salesContract.getCustomerEmail(),
//                        chosenVehicle.getVin(),
//                        chosenVehicle.getYear(),
//                        chosenVehicle.getMake(),
//                        chosenVehicle.getModel(),
//                        chosenVehicle.getVehicleType(),
//                        chosenVehicle.getColor(),
//                        chosenVehicle.getOdometer(),
//                        chosenVehicle.getPrice(),
//                        salesContract.calcSalesTax(),
//                        salesContract.getRecordingFee(),
//                        salesContract.getProcessingFee(),
//                        salesContract.getTotalPrice(),
//                        isFinanced,
//                        salesContract.getMonthlyPayment()
//                );
//                bufferedWriter.write(firstLine);
//
//                bufferedWriter.close();
//            } catch (IOException e) {
//                UserInterface.writeErrorsToLogsFile(e);
//            }
        }




    }
}
