package uk.co.sigmalabs.repository;

import java.util.List;
import java.util.stream.Collectors;

import uk.co.sigmalabs.model.Basket;
import uk.co.sigmalabs.model.Discount;
import uk.co.sigmalabs.model.DiscountPoint;
import uk.co.sigmalabs.model.Fruit;
import uk.co.sigmalabs.model.discounts.BigSpenderDiscount;
import uk.co.sigmalabs.model.discounts.FixedValueDiscount;
import uk.co.sigmalabs.model.discounts.MixedItemFreeFruitDiscount;
import uk.co.sigmalabs.model.discounts.PercentageDiscount;

public class InMemoryDiscountRepository implements DiscountRepository {

    private static List<Discount> discounts;

    @Override
    public List<Discount> getApplicableDiscounts(Basket basket) {
        return getApplicableDiscounts(basket, DiscountPoint.STANDARD);
    }
    @Override
    public List<Discount> getApplicableDiscounts(Basket basket, DiscountPoint discountPoint) {
        return getAllDiscounts()
            .stream()
            .filter(d -> discountPoint.equals(d.getDiscountPoint()))
            .filter(d -> d.canApply(basket))
            .collect(Collectors.toList());
    }

    private List<Discount> getAllDiscounts() {
        if(discounts != null) {
            return discounts;
        }

        FruitRepository fruitRepository = FruitRepository.getRepository();
        Fruit apple = fruitRepository.getFruitByName("apple").orElseThrow(() -> new RuntimeException("Apples not found"));
        Fruit cherry = fruitRepository.getFruitByName("cherry").orElseThrow(() -> new RuntimeException("Cherries not found"));
        Fruit mango = fruitRepository.getFruitByName("mango").orElseThrow(() -> new RuntimeException("Mango not found"));

        discounts = List.of(
            new PercentageDiscount(apple, 10),
            new PercentageDiscount(mango, 25),
            new FixedValueDiscount(cherry, 4, 50),
            new MixedItemFreeFruitDiscount(mango, 3, apple),
            new BigSpenderDiscount(500, 2)
        );

        return discounts;
    }
}
