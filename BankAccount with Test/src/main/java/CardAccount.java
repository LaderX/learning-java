public class CardAccount extends BankAccount {

    public CardAccount(double accountMoney) {
        super(accountMoney);
    }

    @Override
    public boolean withdrawMoney(double amountWithdrawal) {
        if (amountWithdrawal + (amountWithdrawal * 0.01) <= this.accountMoney) {
            this.accountMoney = this.accountMoney - (amountWithdrawal + (amountWithdrawal * 0.01));
            return true;
        } else {
            return false;
        }
    }
}
