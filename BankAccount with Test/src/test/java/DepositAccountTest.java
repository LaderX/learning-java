import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DepositAccountTest {

    DepositAccount testDepositAccount;

    @BeforeEach
    public void accountSet() {
        testDepositAccount = new DepositAccount(1000, setLastDepositDay(-31));
    }

    private Calendar setLastDepositDay(int dayBeforeToday) {
        Calendar lastDeposit = new GregorianCalendar();
        lastDeposit.add(Calendar.DAY_OF_YEAR, dayBeforeToday);
        return lastDeposit;
    }

    private void correctLastDepositDay(int dayBeforeToday) {
        Calendar lastDeposit = new GregorianCalendar();
        lastDeposit.add(Calendar.DAY_OF_YEAR, dayBeforeToday);
        testDepositAccount.сorrectLastDeposit(lastDeposit);
    }

    @Test
    @DisplayName("Get balance")
    public void getBalanceTest() {
        double expected = 1000d;

        assertEquals(expected, testDepositAccount.getBalance(), 0.0);
    }


    @Test
    @DisplayName("Successful money deposit test")
    public void depositMoneySuccess() throws OverMoneyException {
        double expected = 1500d;

        assertTrue(testDepositAccount.depositMoney(500d));
        assertEquals(expected, testDepositAccount.getBalance(), 0.0);
    }

    @Test
    @DisplayName("Throw an exception due to too much deposit")
    public void depositMoneyMuchMoney() {
        Throwable thrown = assertThrows(Exception.class,
                () -> testDepositAccount.depositMoney(9223372036854775307d), "Errore message");
        assertEquals("Слишком много денег", thrown.getMessage());
    }

    @Test
    @DisplayName("Successful withdrawal")
    public void successfulWithdrawal() {
        assertTrue(testDepositAccount.withdrawMoney(500d));
        assertEquals(500d, testDepositAccount.getBalance(), 0.0);
    }

    @Test
    @DisplayName("Unsuccessful withdrawal, not enough money in the account")
    public void unsuccessfulWithdrawalNotEnoughMoney() {
        assertFalse(testDepositAccount.withdrawMoney(1500d));
        assertEquals(1000, testDepositAccount.getBalance(), 0.0);
    }

    @Test
    @DisplayName("Unsuccessful withdrawal, few days have passed")
    public void unsuccessfulWithdrawalFewDays() {
        correctLastDepositDay(-29);

        assertFalse(testDepositAccount.withdrawMoney(500d));
        assertEquals(1000, testDepositAccount.getBalance(), 0.0);
    }

    @Test
    @DisplayName("Successful sending between two accounts")
    public void sendingBetweenAccountsTrue() throws OverMoneyException {
        double expectedFirstAcc = 500d;
        double expectedSecondAcc = 1500d;
        BankAccount secondAcc = new BankAccount(1000);

        assertTrue(testDepositAccount.send(secondAcc, 500d));
        assertEquals(expectedSecondAcc, secondAcc.getBalance());
        assertEquals(expectedFirstAcc, testDepositAccount.getBalance());
    }

    @Test
    @DisplayName("Unsuccessful sending between two accounts")
    public void sendingBetweenAccountsFalse() throws OverMoneyException {
        double expectedFirstAcc = 1000d;
        double expectedSecondAcc = 1000d;
        correctLastDepositDay(-29);

        BankAccount secondAcc = new BankAccount(1000);
        assertFalse(testDepositAccount.send(secondAcc, 500d));
        assertEquals(expectedSecondAcc, secondAcc.getBalance());
        assertEquals(expectedFirstAcc, testDepositAccount.getBalance());
    }

}