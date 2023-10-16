package uk.co.sigmalabs.model;

public interface Discount {

    /**
     * Test if the discount be applied
     * @param basket
     * @return
     */
    boolean canApply(Basket basket);

    DiscountValue getDiscountValue(Basket basket);

    default DiscountPoint getDiscountPoint() {
        return DiscountPoint.STANDARD;
    }
}
