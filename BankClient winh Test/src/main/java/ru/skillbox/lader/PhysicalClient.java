package ru.skillbox.lader;

public class PhysicalClient extends Client {

    public PhysicalClient(double bill) {
        super(bill);
    }

    public void depositBill(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount must be above zero");
        }
        this.bill += amount;
    }

    public void withdrawBill(double amount) {
        if (Double.compare(this.bill, amount) >= 0) {
            this.bill -= amount;
        } else {
            throw new IllegalArgumentException("amount must be less than than the bill");
        }
    }

}
