package uk.co.sigmalabs.model.discounts;

import uk.co.sigmalabs.model.Basket;
import uk.co.sigmalabs.model.Discount;
import uk.co.sigmalabs.model.DiscountValue;
import uk.co.sigmalabs.model.Fruit;

public class FixedValueDiscount extends AbstractQuantityDiscount implements Discount {

    private final Fruit fruit;
    private final int requiredQuantity;
    public FixedValueDiscount(Fruit fruit, int requiredQuantity, int discount) {
        super(fruit, discount);
        this.fruit = fruit;
        this.requiredQuantity = requiredQuantity;
    }

    @Override
    public boolean canApply(Basket basket) {
        return hasFruit(basket, fruit, requiredQuantity);
    }

    @Override
    public DiscountValue getDiscountValue(Basket basket) {
        return discountValue;
    }
}
