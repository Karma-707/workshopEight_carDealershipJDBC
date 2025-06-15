package com.ps.core.contract;

import com.ps.core.Vehicle;

import java.time.LocalDate;

public abstract class Contract {
    private LocalDate date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleChosen;

    //constructor
    public Contract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleChosen) {
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

        double monthlyPayment; //monthly payment
        double monthlyInterestRate; //monthly interest rate

        monthlyInterestRate = interestRate / 12;
        monthlyPayment = principal * ( (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, months)) / ( Math.pow(1 + monthlyInterestRate, months) - 1) );

        return monthlyPayment;
    }


    //abstract methods
    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();


    //getters & setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleChosen() {
        return vehicleChosen;
    }

    public void setVehicleChosen(Vehicle vehicleChosen) {
        this.vehicleChosen = vehicleChosen;
    }
}
