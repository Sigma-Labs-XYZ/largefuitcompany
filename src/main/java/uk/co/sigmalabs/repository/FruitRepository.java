package uk.co.sigmalabs.repository;

import java.util.Optional;

import uk.co.sigmalabs.model.Fruit;

public interface FruitRepository {

    static FruitRepository getRepository() {
        return new InMemoryFruitRepository();
    }

    /**
     * Get a Fruit object
     * @param name name of the fruit
     * @return
     */
    Optional<Fruit> getFruitByName(String name);
}
