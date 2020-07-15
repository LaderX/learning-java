import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DepositAccount extends BankAccount {

    private Calendar lastDeposit;

//    public DepositAccount(double accountMoney) {
//        super(accountMoney);
//    }

    public DepositAccount(double accountMoney,Calendar lastDeposit) {
        super(accountMoney);
        this.lastDeposit = lastDeposit;
    }

    public Calendar getLastDeposit() {
        return lastDeposit;
    }

    public void сorrectLastDeposit(Calendar lastDeposit) {
        this.lastDeposit = lastDeposit;
    }

    @Override
    public boolean withdrawMoney(double amountWithdrawal) {
        Calendar today = new GregorianCalendar();
        Calendar checkDay = (Calendar) today.clone();
        checkDay.add(Calendar.DAY_OF_YEAR, -30);
        if (lastDeposit.before(checkDay)) {
            if (amountWithdrawal <= this.accountMoney) {
                this.accountMoney = this.accountMoney - amountWithdrawal;
                return true;
            } else {
                return false;
            }
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy - EEE");
            dateFormat.setTimeZone(this.getLastDeposit()
                                       .getTimeZone());
            System.out.println("Вы положили деньги " + dateFormat.format(this.getLastDeposit().getTime()) + " сейчас " +
                    dateFormat.format(today.getTime()) + " 30 дней еще не прошло");
            return false;
        }
    }
}
