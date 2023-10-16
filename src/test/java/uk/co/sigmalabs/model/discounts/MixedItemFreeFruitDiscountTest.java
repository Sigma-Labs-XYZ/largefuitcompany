package uk.co.sigmalabs.model.discounts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.co.sigmalabs.model.Basket;
import uk.co.sigmalabs.model.DiscountValue;
import uk.co.sigmalabs.model.Fruit;

class MixedItemFreeFruitDiscountTest {

    private Fruit fruit;
    private Fruit freeFruit;

    private MixedItemFreeFruitDiscount mixedItemFreeFruitDiscount;

    @BeforeEach
    void setUp() {
        fruit = new Fruit("Tomato", 1000);
        freeFruit = new Fruit("Pear", 200);
        mixedItemFreeFruitDiscount = new MixedItemFreeFruitDiscount(fruit, 2, freeFruit);
    }

    @Test
    void canApplyToMixedBasket() {
        Basket basket = new Basket();
        basket.addFruit(fruit);
        basket.addFruit(fruit);
        basket.addFruit(freeFruit);

        assertTrue(mixedItemFreeFruitDiscount.canApply(basket));
    }

    @Test
    void canNotApplyToMixedBasketWithoutFree() {
        Basket basket = new Basket();
        basket.addFruit(fruit);
        basket.addFruit(fruit);

        assertFalse(mixedItemFreeFruitDiscount.canApply(basket));
    }

    @Test
    void getValue() {
        DiscountValue discountValue = mixedItemFreeFruitDiscount.getDiscountValue(new Basket());

        assertEquals(200, discountValue.getValue());
        assertEquals("Pear Discount -200p", discountValue.getText());
    }
}