package com.ps;

public abstract class Contract {
    private String Date;
    private String customerName;
    private String customerEmail;
    private String vehicleSold;
    private double totalPrice;
    private double monthlyPayment;

    //constructor
    public Contract(String date, String customerName, String customerEmail, String vehicleSold, double totalPrice, double monthlyPayment) {
        Date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
    }

    //getters
    public String getDate() {
        return Date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getVehicleSold() {
        return vehicleSold;
    }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

    public static double calcMonthlyPayment() {
        return 0;
    }



    //setters
    public void setDate(String date) {
        Date = date;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setVehicleSold(String vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}
