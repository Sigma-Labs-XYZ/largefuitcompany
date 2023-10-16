package uk.co.sigmalabs.model;

public class BasketItem {

    private final Fruit fruit;
    private int count;

    public BasketItem(Fruit fruit) {
        this.fruit = fruit;
        this.count = 1;
    }

    public boolean isSameFruit(Fruit testFruit) {
        return testFruit.equals(this.fruit);
    }

    public void increment() {
        count++;
    }

    public int getCost() {
        return count * fruit.getCost();
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return count + " x " + fruit.getName();
    }
}
