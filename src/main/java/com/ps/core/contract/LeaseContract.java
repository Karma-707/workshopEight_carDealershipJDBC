package com.ps.core.contract;

import com.ps.core.Vehicle;
import java.time.LocalDate;

public class LeaseContract extends Contract {
    private int leaseContractId; //using from DB
    private LocalDate leaseStart;
    private LocalDate leaseEnd;
    private double monthlyPayment;

    private double expectedEndingValue;
    private double leaseFee;

    //constructor
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleChosen) {
        super(date, customerName, customerEmail, vehicleChosen);
        this.expectedEndingValue = vehicleChosen.getPrice() * 0.5;
        this.leaseFee = vehicleChosen.getPrice() * 0.07;
    }

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleChosen,
                         LocalDate leaseStart, LocalDate leaseEnd) {
        super(date, customerName, customerEmail, vehicleChosen);
        this.leaseStart = leaseStart;
        this.leaseEnd = leaseEnd;

        this.expectedEndingValue = vehicleChosen.getPrice() * 0.5;
        this.leaseFee = vehicleChosen.getPrice() * 0.07;
        this.monthlyPayment = getMonthlyPayment();
    }

    // Overloaded constructor to fetch from DB
    public LeaseContract(int leaseContractId, String leaseName, String customerEmail, Vehicle vehicleChosen,
                         LocalDate leaseStart, LocalDate leaseEnd, double expectedEndingValue,
                         double leaseFee, double monthlyPayment) {
        super(leaseStart.toString(), leaseName, customerEmail, vehicleChosen);  // date = leaseStart as String, customerName = leaseName
        this.leaseContractId = leaseContractId;
        this.leaseStart = leaseStart;
        this.leaseEnd = leaseEnd;

        this.expectedEndingValue = expectedEndingValue;
        this.leaseFee = leaseFee;
        this.monthlyPayment = monthlyPayment;
    }

    public LeaseContract(int leaseContractId, String customerName, String customerEmail, Vehicle vehicleChosen,
                         LocalDate leaseStart, LocalDate leaseEnd) {
        super(String.valueOf(leaseStart), customerName, customerEmail, vehicleChosen);
        this.leaseStart = leaseStart;
        this.leaseEnd = leaseEnd;

        this.expectedEndingValue = vehicleChosen.getPrice() * 0.5;
        this.leaseFee = vehicleChosen.getPrice() * 0.07;
        this.monthlyPayment = getMonthlyPayment();
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



    //getters & setters
    public int getLeaseContractId() {
        return leaseContractId;
    }

    public void setLeaseContractId(int leaseContractId) {
        this.leaseContractId = leaseContractId;
    }

    public LocalDate getLeaseStart() {
        return leaseStart;
    }

    public void setLeaseStart(LocalDate leaseStart) {
        this.leaseStart = leaseStart;
    }

    public LocalDate getLeaseEnd() {
        return leaseEnd;
    }

    public void setLeaseEnd(LocalDate leaseEnd) {
        this.leaseEnd = leaseEnd;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }



}
