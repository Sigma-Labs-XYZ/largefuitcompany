package uk.co.sigmalabs.model.discounts;

import uk.co.sigmalabs.model.Basket;
import uk.co.sigmalabs.model.Discount;
import uk.co.sigmalabs.model.DiscountPoint;
import uk.co.sigmalabs.model.DiscountValue;

public class BigSpenderDiscount implements Discount {

    private final int totalRequired;
    private final int discountPercentage;

    public BigSpenderDiscount(int totalRequired, int discountPercentage) {
        this.totalRequired = totalRequired;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public boolean canApply(Basket basket) {
        return basket.getTotal() > totalRequired;
    }

    @Override
    public DiscountValue getDiscountValue(Basket basket) {
        int basketTotal = basket.getSubTotal() - basket.getStandardDiscountsValue();
        int discount = (basketTotal / 100) * discountPercentage;
        return new DiscountValue(
            discount,
            "Big Spender Discount -" + discount + "p"
        );
    }

    @Override
    public DiscountPoint getDiscountPoint() {
        return DiscountPoint.AFTER_OTHER_DISCOUNTS;
    }
}
