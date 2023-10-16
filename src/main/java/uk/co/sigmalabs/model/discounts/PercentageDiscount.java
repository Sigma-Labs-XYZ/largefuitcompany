package uk.co.sigmalabs.model.discounts;


import java.util.Optional;

import uk.co.sigmalabs.model.Basket;
import uk.co.sigmalabs.model.BasketItem;
import uk.co.sigmalabs.model.Discount;
import uk.co.sigmalabs.model.DiscountValue;
import uk.co.sigmalabs.model.Fruit;

/**
 * Apply a percentage discount on specific products
 */
public class PercentageDiscount implements Discount {

    private final Fruit fruit;
    private final int percentage;

    public PercentageDiscount(Fruit fruit, int percentage) {
        this.fruit = fruit;
        this.percentage = percentage;
    }

    @Override
    public boolean canApply(Basket basket) {
        return basket
            .getItems()
            .stream()
            .anyMatch(b -> b.isSameFruit(fruit));
    }

    @Override
    public DiscountValue getDiscountValue(Basket basket) {
        Optional<BasketItem> item = basket.getItems().stream().filter(i -> i.isSameFruit(fruit)).findFirst();

        if(item.isEmpty()) {
            throw new RuntimeException("Basket item does not exist");
        }
       
        int discountValue = (item.get().getCost() / 100) * percentage;
        
        return new DiscountValue(
            discountValue,
            "Discounted " + fruit.getName() + " -" + discountValue + "p"
        );
    }
}
