import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class BankAccountTest {

    BankAccount testBankAccount;

    @BeforeEach
    public void accountSet() {
        testBankAccount = new BankAccount(1000);
    }

    @Test
    @DisplayName("Get balance")
    public void getBalance() {
        double expected = 1000d;
        assertEquals(expected, testBankAccount.getBalance(), 0.0);
    }

    @Test
    @DisplayName("Successful money deposit test")
    public void depositMoneySuccess() throws OverMoneyException {
        double expected = 1500d;
        testBankAccount.depositMoney(500d);
        assertEquals(expected, testBankAccount.getBalance(), 0.0);

        assertTrue(testBankAccount.depositMoney(500d));
    }

    @Test
    @DisplayName("Throw an exception due to too much deposit")
    public void depositMoneyMuchMoney() {
        Throwable thrown = assertThrows(Exception.class,
                () -> testBankAccount.depositMoney(9223372036854775307d), "Errore message");
        assertEquals("Слишком много денег", thrown.getMessage());
    }

    @Test
    @DisplayName("Successful withdrawal")
    public void successfulWithdrawal() {
        double expected = 500d;
        testBankAccount.withdrawMoney(500d);
        assertEquals(expected, testBankAccount.getBalance(), 0.0);
        assertTrue(testBankAccount.withdrawMoney(500d));
    }

    @Test
    @DisplayName("Unsuccessful withdrawal, remove more than is on the account")
    public void unsuccessfulWithdrawal() {
        assertFalse(testBankAccount.withdrawMoney(1500d));
    }

    @Test
    @DisplayName("Successful sending between two accounts")
    public void sendingBetweenAccountsTrue() throws OverMoneyException {
        double expectedFirstAcc = 500d;
        double expectedSecondAcc = 1500d;
        BankAccount secondAcc = new BankAccount(1000);
        assertTrue(testBankAccount.send(secondAcc, 500d));
        assertEquals(expectedSecondAcc, secondAcc.getBalance());
        assertEquals(expectedFirstAcc, testBankAccount.getBalance());
    }

    @Test
    @DisplayName("Unsuccessful sending between two accounts")
    public void sendingBetweenAccountsFalse() throws OverMoneyException {
        double expectedFirstAcc = 1000d;
        double expectedSecondAcc = 1000d;
        BankAccount secondAcc = new BankAccount(1000);
        assertFalse(testBankAccount.send(secondAcc, 1500d));
        assertEquals(expectedSecondAcc, secondAcc.getBalance());
        assertEquals(expectedFirstAcc, testBankAccount.getBalance());
    }
}
