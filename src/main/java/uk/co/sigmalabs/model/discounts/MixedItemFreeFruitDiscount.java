package uk.co.sigmalabs.model.discounts;

import uk.co.sigmalabs.model.Basket;
import uk.co.sigmalabs.model.Discount;
import uk.co.sigmalabs.model.DiscountValue;
import uk.co.sigmalabs.model.Fruit;

/**
 * Discount if you by a number of one item and get another fee
 */
public class MixedItemFreeFruitDiscount extends AbstractQuantityDiscount implements Discount {

    private final Fruit fruit;
    private final int requiredQuantity;
    private final Fruit freeFruit;

    public MixedItemFreeFruitDiscount(Fruit fruit, int requiredQuantity, Fruit freeFruit) {
        super(freeFruit, freeFruit.getCost());
        this.fruit = fruit;
        this.requiredQuantity = requiredQuantity;
        this.freeFruit = freeFruit;
    }

    @Override
    public boolean canApply(Basket basket) {
        return hasFruit(basket, fruit, requiredQuantity) &&
            hasFruit(basket, freeFruit, 1);
    }

    @Override
    public DiscountValue getDiscountValue(Basket basket) {
        return discountValue;
    }
}
