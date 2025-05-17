package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
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
                    isFinanced = "NO";
                }

                String firstLine = String.format("SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f|%s|%.2f\n",
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
            Vehicle chosenVehicle = leaseContract.getVehicleChosen();

            //write to lease contract file
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("contracts.csv", true));

                String firstLine = String.format("LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f\n",
                        leaseContract.getDate(),
                        leaseContract.getCustomerName(),
                        leaseContract.getCustomerEmail(),
                        chosenVehicle.getVin(),
                        chosenVehicle.getYear(),
                        chosenVehicle.getMake(),
                        chosenVehicle.getModel(),
                        chosenVehicle.getVehicleType(),
                        chosenVehicle.getColor(),
                        chosenVehicle.getOdometer(),
                        chosenVehicle.getPrice(),
                        leaseContract.getExpectedEndingValue(),
                        leaseContract.getLeaseFee(),
                        leaseContract.getTotalPrice(),
                        leaseContract.getMonthlyPayment()
                );
                bufferedWriter.write(firstLine);

                bufferedWriter.close();
            } catch (IOException e) {
                UserInterface.writeErrorsToLogsFile(e);
            }
        }




    }
}
