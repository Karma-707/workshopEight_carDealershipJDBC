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
