package uk.co.sigmalabs.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Basket {

    private final List<BasketItem> basketItems = new ArrayList<>();
    private List<Discount> discounts;

    public void addFruit(Fruit fruit) {
        Optional<BasketItem> existingItem = basketItems.stream().filter(b -> b.isSameFruit(fruit)).findFirst();

        if(existingItem.isPresent()) {
            existingItem.get().increment();
        } else {
            basketItems.add(new BasketItem(fruit));
        }
    }

    public boolean containsFruit(Fruit fruit) {
        return false;
    }

    public List<BasketItem> getItems() {
        return basketItems;
    }

    public List<Discount> getDiscounts() {
        if(discounts == null) {
            return List.of();
        }

        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public List<String> getDiscountText() {
        return getDiscounts()
            .stream()
            .map(d -> d.getDiscountValue(this))
            .map(DiscountValue::getText)
            .collect(Collectors.toList());
    }

    /**
     * Get the total basket cost
     * @return
     */
    public int getSubTotal() {
        return basketItems
            .stream()
            .mapToInt(BasketItem::getCost)
            .sum();
    }

    public int getStandardDiscountsValue() {
        return discounts
            .stream()
            .filter(d -> DiscountPoint.STANDARD.equals(d.getDiscountPoint()))
            .map(d -> d.getDiscountValue(this))
            .mapToInt(DiscountValue::getValue)
            .sum();
    }

    public int getTotal() {
        int discountValue = getDiscountValues()
            .stream()
            .mapToInt(DiscountValue::getValue)
            .sum();

        return getSubTotal() - discountValue;
    }


    private List<DiscountValue> getDiscountValues() {
        return discounts
            .stream()
            .map(d -> d.getDiscountValue(this))
            .collect(Collectors.toList());
    }
}
