package uk.co.sigmalabs.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FruitTest {

    @Test
    void createSimpleFruit() {
        Fruit apple = new Fruit("Apple", 145);

        assertEquals("Apple", apple.getName());
        assertEquals(145, apple.getCost());
        assertEquals("£1.45", apple.getPoundsCost());
        assertEquals("Apple - £1.45", apple + "");
    }

    @Test
    void createFruitWithInvalidMoney() {
        assertThrows(IllegalArgumentException.class, () -> new Fruit("Apple", -5));
    }
}