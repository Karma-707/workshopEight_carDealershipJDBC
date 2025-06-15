package com.ps.core.contract;

import com.ps.core.Vehicle;

public class SalesContract extends Contract {
    private int salesContractId; //using from DB
    private double salePrice; // From vehicle.getPrice()

    private double salesTax;
    private double recordingFee;
    private double processingFee;
    private boolean isFinanced;


    //constructor
    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleChosen, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicleChosen);
        this.salePrice = vehicleChosen.getPrice();

        this.salesTax = 0.05;
        this.recordingFee = 100;

        if(vehicleChosen.getPrice() < 10_000) {
            this.processingFee = 295;
        }
        else {
            this.processingFee = 495;
        }

        this.isFinanced = isFinanced;
    }

    // Overloaded constructor for loading from DB
    public SalesContract(int salesContractId, String date, String customerName, String customerEmail,
                         Vehicle vehicleChosen, double salePrice, double recordingFee, double processingFee, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicleChosen);
        this.salesContractId = salesContractId;

        this.salePrice = salePrice;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.isFinanced = isFinanced;
    }






    //get total price
    @Override
    public double getTotalPrice() {
        /* total price formula
        * Total Price = base price + (base price * sales tax) + recording fee + processing fee
        * */
        double basePrice = getVehicleChosen().getPrice();
        double totalPrice = basePrice + (basePrice * this.salesTax) + this.recordingFee + this.processingFee;

        return totalPrice;
    }

    //get monthly payments
    @Override
    public double getMonthlyPayment() {
        //check if finance --> loan option
        if(!isFinanced) {
            return 0;
        }

        double principal = getTotalPrice(); //total price of vehicle
        double interestRate = 0; //annual loan rate
        int months = 0; //loan term in months

        if(principal >= 10_000) {
            interestRate = 4.25 / 100; //change to decimal
            months = 48;
        }
        else {
            interestRate = 5.25 / 100; //change to decimal
            months = 24;
        }

        return Contract.calcMonthlyPayment(principal, interestRate, months);
    }


    //calculate sales tax
    public double calcSalesTax() {
        Vehicle vehicle = getVehicleChosen();
        double vehiclePrice = vehicle.getPrice();
        return vehiclePrice * 0.05;
    }



    //getters & setters
    public int getSalesContractId() {
        return salesContractId;
    }

    public void setSalesContractId(int salesContractId) {
        this.salesContractId = salesContractId;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }
}
