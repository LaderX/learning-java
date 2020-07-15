public class BankAccount {

    // TODO: Replace double type with money type.
    protected double accountMoney;

    public BankAccount(double accountMoney) {
        this.accountMoney = accountMoney;
    }

    public boolean withdrawMoney(double amountWithdrawal) {
        if (amountWithdrawal <= this.accountMoney) {
            this.accountMoney = this.accountMoney - amountWithdrawal;
            return true;
        } else {
            return false;
        }
    }

    public boolean depositMoney(double amountDeposit) throws OverMoneyException {
        if (amountDeposit > 1000000000) {
            throw new OverMoneyException("Слишком много денег");
        }
        this.accountMoney = this.accountMoney + amountDeposit;
        return true;
    }

    public double getBalance() {
        return accountMoney;
    }

    public boolean send(BankAccount receiver, double amount) throws OverMoneyException {
        if (Double.compare(this.getBalance(), amount) >= 0 && amount <= 1000000000) {
            //Можно сделать проще оставив только вызов 2 методов, но банковская тематика вызывает пароною и желание перестраховаться)
            boolean withdrawal = this.withdrawMoney(amount);
            boolean deposit = false;
            if(withdrawal){
                deposit = receiver.depositMoney(amount);
            }
            if (withdrawal && deposit) {
                return true;
            } else {
                System.out.println("Сбой в системе");
                return false;
            }
        } else {
            System.out.println("Недостаточно средств или слишком большой перевод");
            return false;
        }
    }
}
