package uk.co.sigmalabs.model.discounts;

import uk.co.sigmalabs.model.Basket;
import uk.co.sigmalabs.model.BasketItem;
import uk.co.sigmalabs.model.DiscountValue;
import uk.co.sigmalabs.model.Fruit;

/**
 * Parent discount for counting items
 */
abstract public class AbstractQuantityDiscount {


    protected final DiscountValue discountValue;

    protected AbstractQuantityDiscount(Fruit fruit, int discount) {
        discountValue = new DiscountValue(
            discount,
            fruit.getName() + " Discount -" + discount + "p"
        );
    }

    /**
     * Test if the basket has the right amount of fruit
     * @param basket
     * @param fruit
     * @param requiredQuantity
     * @return
     */
    protected boolean hasFruit(Basket basket, Fruit fruit, int requiredQuantity) {
        int itemCount = basket
            .getItems()
            .stream()
            .filter(i -> i.isSameFruit(fruit))
            .findFirst()
            .map(BasketItem::getCount)
            .orElse(0);

        return itemCount >= requiredQuantity;
    }
}
