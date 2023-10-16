package uk.co.sigmalabs.repository;

import java.util.List;

import uk.co.sigmalabs.model.Basket;
import uk.co.sigmalabs.model.Discount;
import uk.co.sigmalabs.model.DiscountPoint;

public interface DiscountRepository {

    static DiscountRepository getInstance() {
        return new InMemoryDiscountRepository();
    }

    List<Discount> getApplicableDiscounts(Basket basket);
    List<Discount> getApplicableDiscounts(Basket basket, DiscountPoint discountPoint);
}
