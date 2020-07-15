package ru.skillbox.lader;

public class EntityClient extends Client {
    public EntityClient(double bill) {
        super(bill);
    }

    public void depositBill(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount must be above zero");
        }
        this.bill += amount;
    }

    public void withdrawBill(double amount) {
        if (Double.compare(this.bill, (amount + (amount * 0.01))) >= 0) {
            this.bill -= (amount + (amount * 0.01));
        } else {
            throw new IllegalArgumentException("amount must be less than the bill");
        }
    }
}
