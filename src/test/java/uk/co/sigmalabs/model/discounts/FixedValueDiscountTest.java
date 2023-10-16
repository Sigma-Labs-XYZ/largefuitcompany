package uk.co.sigmalabs.model.discounts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.co.sigmalabs.model.Basket;
import uk.co.sigmalabs.model.DiscountValue;
import uk.co.sigmalabs.model.Fruit;

class FixedValueDiscountTest {

    private Fruit fruit;
    private FixedValueDiscount fixedValueDiscount;

    @BeforeEach
    void setUp() {
        fruit = new Fruit("Tomato", 1000);
        fixedValueDiscount = new FixedValueDiscount(fruit, 2, 30);

    }

    @Test
    void canApplyToTomato() {
        Basket basket = new Basket();
        basket.addFruit(fruit);
        basket.addFruit(fruit);
        basket.addFruit(fruit);

        assertTrue(fixedValueDiscount.canApply(basket));
    }

    @Test
    void cannotApplyToOneTomato() {
        Basket basket = new Basket();
        basket.addFruit(fruit);

        assertFalse(fixedValueDiscount.canApply(basket));
    }

    @Test
    void cannotApplyToWrongFruit() {
        Basket basket = new Basket();
        basket.addFruit(new Fruit("Apple", 1));
        basket.addFruit(new Fruit("Mango", 1));
        basket.addFruit(new Fruit("Mango", 1));
        basket.addFruit(new Fruit("Mango", 1));

        assertFalse(fixedValueDiscount.canApply(basket));
    }

    @Test
    void getValue() {
        DiscountValue value = fixedValueDiscount.getDiscountValue(new Basket());

        assertEquals(30, value.getValue());
        assertEquals("Tomato Discount -30p", value.getText());
    }
}