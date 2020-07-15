import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardAccountTest {

    CardAccount testCardAccount;

    @BeforeEach
    public void accountSet() {
        testCardAccount = new CardAccount(1000);
    }

    @Test
    @DisplayName("Get balance")
    public void testGetBalance() {
        double expected = 1000d;
        assertEquals(expected, testCardAccount.getBalance(), 0.0);
    }

    @Test
    @DisplayName("Successful money deposit test")
    public void depositMoneySuccess() throws OverMoneyException {
        double expected = 1500d;
        testCardAccount.depositMoney(500d);
        assertEquals(expected, testCardAccount.getBalance(), 0.0);
        assertTrue(testCardAccount.depositMoney(500d));
    }

    @Test
    @DisplayName("Throw an exception due to too much deposit")
    public void depositMoneyMuchMoney() {
        Throwable thrown = assertThrows(Exception.class,
                () -> testCardAccount.depositMoney(9223372036854775307d),
                "Errore message");
        assertEquals("Слишком много денег", thrown.getMessage());
    }

    @Test
    @DisplayName("Successful withdrawal")
    public void successfulWithdrawal() {
        double expected = 495d;
        assertTrue(testCardAccount.withdrawMoney(500d));
        assertEquals(expected, testCardAccount.getBalance(), 0.0);
    }

    @Test
    @DisplayName("Unsuccessful withdrawal, not enough money in the account")
    public void testWithdrawMoneyFalse() {
        double expected = 1000d;
        assertFalse(testCardAccount.withdrawMoney(1500d));
        assertEquals(expected, testCardAccount.getBalance(), 0.0);
    }

    @Test
    @DisplayName("Successful sending between two accounts")
    public void sendingBetweenAccountsTrue() throws OverMoneyException {
        double expectedFirstAcc = 495d;
        double expectedSecondAcc = 1500d;
        BankAccount secondAcc = new BankAccount(1000);
        assertTrue(testCardAccount.send(secondAcc, 500d));
        assertEquals(expectedSecondAcc, secondAcc.getBalance());
        assertEquals(expectedFirstAcc, testCardAccount.getBalance());
    }

    @Test
    @DisplayName("Unsuccessful sending between two accounts")
    public void sendingBetweenAccountsFalse() throws OverMoneyException {
        double expectedFirstAcc = 1000d;
        double expectedSecondAcc = 1000d;
        BankAccount secondAcc = new BankAccount(1000);
        assertFalse(testCardAccount.send(secondAcc, 1500d));
        assertEquals(expectedSecondAcc, secondAcc.getBalance());
        assertEquals(expectedFirstAcc, testCardAccount.getBalance());
    }
}
