package uk.co.sigmalabs.model.discounts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.co.sigmalabs.model.Basket;
import uk.co.sigmalabs.model.DiscountValue;
import uk.co.sigmalabs.model.Fruit;

class PercentageDiscountTest {

    private Fruit fruit;
    private PercentageDiscount percentageDiscount;

    @BeforeEach
    void setUp() {
        fruit = new Fruit("Tomato", 1000);
        percentageDiscount = new PercentageDiscount(fruit, 30);

    }

    @Test
    void applyToTomato() {
        Basket basket = new Basket();
        basket.addFruit(fruit);

        assertTrue(percentageDiscount.canApply(basket));
    }

    @Test
    void applyNotApplyToOtherFruit() {
        Basket basket = new Basket();
        basket.addFruit(new Fruit("Apple", 1));
        basket.addFruit(new Fruit("Banana", 1));

        assertFalse(percentageDiscount.canApply(basket));
    }

    @Test
    void getDiscount() {
        Basket basket = new Basket();
        basket.addFruit(fruit);

        DiscountValue discountValue = percentageDiscount.getDiscountValue(basket);

        assertEquals(300, discountValue.getValue());
        assertEquals("Discounted Tomato -300p", discountValue.getText());
    }

    @Test
    void handleNoFruit() {
        assertThrows(RuntimeException.class, () -> percentageDiscount.getDiscountValue(new Basket()));
    }
}