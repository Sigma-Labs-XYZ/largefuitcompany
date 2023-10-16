package uk.co.sigmalabs.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import uk.co.sigmalabs.model.Fruit;

class InMemoryFruitRepositoryTest {

    private FruitRepository fruitRepository = new InMemoryFruitRepository();

    @ParameterizedTest
    @ValueSource(strings = {"Apple", "Orange", "Mango", "Cherry"})
    void getFruit(String name) {
        Optional<Fruit> fruit = fruitRepository.getFruitByName(name);

        assertTrue(fruit.isPresent());
        assertEquals(name, fruit.get().getName());
    }

    @Test
    void getMissingFruit() {
        assertFalse(fruitRepository.getFruitByName("Pork").isPresent());
    }
}