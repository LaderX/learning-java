package ru.skillbox.lader;

public abstract class Client {

    protected double bill;

    public Client(double bill) {
        this.bill = bill;
    }

    public double getBill() {
        return bill;
    }

    public abstract void depositBill(double amount);

    public abstract void withdrawBill(double amount);

}
