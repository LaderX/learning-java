package ru.skillbox.lader;

public class IeClient extends Client {

    public IeClient(double bill) {
        super(bill);
    }

    public void depositBill(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount must be above zero");
        }
        if (amount < 1000d) {
            this.bill += (amount - (amount * 0.01));
        } else {
            this.bill += (amount - (amount * 0.005));
        }
    }

    public void withdrawBill(double amount) {
        if (Double.compare(this.bill, amount) >= 0) {
            this.bill -= amount;
        } else {
            throw new IllegalArgumentException("amount must be less than than the bill");
        }
    }

}
