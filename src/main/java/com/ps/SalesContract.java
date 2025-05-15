package com.ps;

public class SalesContract extends Contract{
    private double salesTax;
    private int recordingFee;
    private int processingFee;
    private boolean isFinance;

    //constructor
    public SalesContract(String date, String customerName, String customerEmail, String vehicleSold, double totalPrice, double monthlyPayment, int processingFee, boolean isFinance) {
        super(date, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        this.salesTax = 0.05;
        this.recordingFee = 100;

        if(totalPrice < 10_000) {
            this.processingFee = 295;
        }
        else {
            this.processingFee = 495;
        }

        this.isFinance = isFinance;
    }


    //get total price
    @Override
    public double getTotalPrice() {
        /* total price formula
        * Total Price = base price + (base price * sales tax) + recording fee + processing fee
        * */
        //TODO: fix the function, find base price; is that in vehicles? - made them static
        double totalPrice = 0;
//        double basePrice = Vehicle.getPrice();
//        totalPrice = basePrice + (basePrice * this.salesTax) + this.recordingFee + this.processingFee;

        return totalPrice;
    }

    //get monthly payments
    @Override
    public double getMonthlyPayment() {
        //check if finance --> loan option
        if(!isFinance) {
            return 0;
        }

        /* monthly payment formula
        * M = P * ( r(1+r)^n ) / ( (1+r)^n - 1 )
        * M = Monthly payment
        * P = Principal (loan amount)
        * r = Monthly interest rate (annual rate divided by 12)
        * n = Total number of payments (loan term in months)
        * */
        double principal = getTotalPrice(); //total price of vehicles
        double interestRate = 0; //annual loan rate
        int months = 0; //loan term in months
        double monthlyPayment = 0; //monthly payment
        double monthlyInterestRate = 0; //monthly interest rate

        if(principal >= 10_000) {
            interestRate = 4.25 / 100; //change to decimal
            months = 48;
        }
        else {
            interestRate = 5.25 / 100; //change to decimal
            months = 24;
        }
        monthlyInterestRate = interestRate / 12;
        monthlyPayment = principal * ( monthlyInterestRate * Math.pow(1 + monthlyInterestRate, months) / ( Math.pow(1 + monthlyInterestRate, months) - 1) );

        return monthlyPayment;
    }

    //getters
    public double getSalesTax() {
        return salesTax;
    }

    public int getRecordingFee() {
        return recordingFee;
    }

    public int getProcessingFee() {
        return processingFee;
    }

    public boolean isFinance() {
        return isFinance;
    }

    //setters
    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public void setRecordingFee(int recordingFee) {
        this.recordingFee = recordingFee;
    }

    public void setProcessingFee(int processingFee) {
        this.processingFee = processingFee;
    }

    public void setFinance(boolean finance) {
        isFinance = finance;
    }
}
