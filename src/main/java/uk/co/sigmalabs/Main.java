package uk.co.sigmalabs;

import uk.co.sigmalabs.model.Basket;
import uk.co.sigmalabs.model.Discount;
import uk.co.sigmalabs.model.DiscountPoint;
import uk.co.sigmalabs.model.Fruit;
import uk.co.sigmalabs.repository.DiscountRepository;
import uk.co.sigmalabs.repository.FruitRepository;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        FruitRepository fruitRepository = FruitRepository.getRepository();
        DiscountRepository discountRepository = DiscountRepository.getInstance();
        Basket basket = new Basket();

        for (String f: args) {
            Optional<Fruit> fruit = fruitRepository.getFruitByName(f);

            fruit.ifPresent(basket::addFruit);
        }

        List<Discount> discounts = discountRepository.getApplicableDiscounts(basket);
        basket.setDiscounts(discounts);

        List<Discount> postBasketDiscounts = discountRepository.getApplicableDiscounts(basket, DiscountPoint.AFTER_OTHER_DISCOUNTS);
        basket.getDiscounts().addAll(postBasketDiscounts);

        Printer printer = new Printer();
        printer.printBasketOutput(basket);

    }
}