package com.ps;

public class LeaseContract extends Contract{
    private double expectedEndingValue;
    private double leaseFee;


    //constructor
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleChosen) {
        super(date, customerName, customerEmail, vehicleChosen);
    }

    @Override
    public double getTotalPrice() {
        return 0;
    }

    @Override
    public double getMonthlyPayment() {
        /*
        * Monthly payment based on
        * All leases are financed at 4.0% for 36 months
        * */

        return 0;
    }



    //getter
    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    //setter
    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }


}
