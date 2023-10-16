package uk.co.sigmalabs.repository;

import java.util.Map;
import java.util.Optional;

import uk.co.sigmalabs.model.Fruit;

public class InMemoryFruitRepository implements FruitRepository {

    private static final Map<String, Fruit> fruits = Map.of(
        "apple", new Fruit("Apple", 80),
        "orange", new Fruit("Orange", 75),
        "mango", new Fruit("Mango", 250),
        "cherry", new Fruit("Cherry", 24)
    );

    @Override
    public Optional<Fruit> getFruitByName(String name) {
        Fruit fruit = fruits.get(name.toLowerCase());
        return Optional.ofNullable(fruit);
    }
}
