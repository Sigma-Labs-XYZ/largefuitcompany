package uk.co.sigmalabs.model;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Simple fruit class that can be purchased at the large fruit company
 */
public class Fruit {

    private static final DecimalFormat moneyFormat = new DecimalFormat("0.00");

    /**
     * The name of the fruit
     */
    private final String name;

    /**
     * Cost in pence
     */
    private final int cost;

    /**
     * Create an immutable fruit
     * @param name Name of the fruit
     * @param cost Price in pence, must be more than 0
     */
    public Fruit(String name, int cost) {
        this.name = name;
        this.cost = cost;

        if(cost < 0) {
            throw new IllegalArgumentException("Cost cannot be less than 0");
        }
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    /**
     * Get the cost as string with a £ sign
     * @return String representation in pounds
     */
    public String getPoundsCost() {
        float pounds = (float) getCost() / 100;

        return "£" + moneyFormat.format(pounds);
    }

    @Override
    public String toString() {
        return getName() + " - " + getPoundsCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Fruit fruit = (Fruit) o;

        return Objects.equals(name, fruit.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
