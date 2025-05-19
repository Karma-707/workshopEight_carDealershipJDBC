package com.ps;

public class LeaseContract extends Contract{
    private double expectedEndingValue;
    private double leaseFee;

    //constructor
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleChosen) {
        super(date, customerName, customerEmail, vehicleChosen);
        this.expectedEndingValue = vehicleChosen.getPrice() * 0.5;
        this.leaseFee = vehicleChosen.getPrice() * 0.07;
    }

    @Override
    public double getTotalPrice() {
        /* total price formula
         * Total Price = base price + lease fee - expected ending value
         * */
        double basePrice = getVehicleChosen().getPrice();
        double totalPrice = (basePrice - this.expectedEndingValue) + this.leaseFee;

        return totalPrice;
    }

    @Override
    public double getMonthlyPayment() {
        /*
        * Monthly payment based on
        * All leases are financed at 4.0% for 36 months
        * */

        double principal = getTotalPrice(); //total price of vehicle
        double interestRate = 4.0 / 100;
        int months = 36;

        return Contract.calcMonthlyPayment(principal, interestRate, months);
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
