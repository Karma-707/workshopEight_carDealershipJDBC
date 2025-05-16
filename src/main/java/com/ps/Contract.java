package com.ps;

public abstract class Contract {
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleChosen;

    //constructor
    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleChosen) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleChosen = vehicleChosen;
    }

    public static double calcMonthlyPayment(double principal, double interestRate, int months) {
        /* monthly payment formula
         * M = P * ( r(1+r)^n ) / ( (1+r)^n - 1 )
         * M = Monthly payment
         * P = Principal (loan amount)
         * r = Monthly interest rate (annual rate divided by 12)
         * n = Total number of payments (loan term in months)
         * */

//        double interestRate; //annual loan rate
//        int months; //loan term in months
        double monthlyPayment; //monthly payment
        double monthlyInterestRate; //monthly interest rate

//
//        if(principal >= 10_000) {
//            interestRate = 4.25 / 100; //change to decimal
//            months = 48;
//        }
//        else {
//            interestRate = 5.25 / 100; //change to decimal
//            months = 24;
//        }
        monthlyInterestRate = interestRate / 12;
        monthlyPayment = principal * ( monthlyInterestRate * Math.pow(1 + monthlyInterestRate, months) / ( Math.pow(1 + monthlyInterestRate, months) - 1) );

        return monthlyPayment;
    }

    //getters
    public String getDate() {
        return date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Vehicle getVehicleChosen() {
        return vehicleChosen;
    }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

    //setters
    public void setDate(String date) {
        this.date = date;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setVehicleChosen(Vehicle vehicleChosen) {
        this.vehicleChosen = vehicleChosen;
    }

}
