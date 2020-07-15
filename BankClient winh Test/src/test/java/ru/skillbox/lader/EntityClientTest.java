package ru.skillbox.lader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityClientTest {
    EntityClient testEC;

    @BeforeEach
    public void setClientBill() {
        testEC = new EntityClient(1000d);
    }


    @Test
    @DisplayName("Throw an exception if amount less than zero")
    void depositBillThrow() {
        double expected = 1000d;
        Throwable thrown = assertThrows(Exception.class,
                () -> testEC.depositBill(-1d), "Errore message");
        assertEquals("amount must be above zero", thrown.getMessage());
        assertEquals(expected, testEC.getBill(), 0.0d);
    }

    @Test
    @DisplayName("Successful deposit physical bill")
    void depositBillSuccess() {
        double expected = 1500d;
        this.testEC.depositBill(500d);
        assertEquals(expected, testEC.getBill(), 0.0d);
    }

    @Test
    @DisplayName("Throw an exception if amount greater than the bill")
    void withdrawBillThrow() {
        double expected = 1000d;
        Throwable thrown = assertThrows(Exception.class,
                () -> testEC.withdrawBill(1500d), "Errore message");
        assertEquals("amount must be less than than the bill", thrown.getMessage());
        assertEquals(expected, testEC.getBill(), 0.0d);
    }

    @Test
    @DisplayName("Successful withdraw physical bill")
    void withdrawBillSuccess() {
        double expected = 495d;
        this.testEC.withdrawBill(500d);
        assertEquals(expected, testEC.getBill(), 0.0d);
    }
}