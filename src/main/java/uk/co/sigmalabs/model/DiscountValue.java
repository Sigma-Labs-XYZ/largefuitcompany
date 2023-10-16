package uk.co.sigmalabs.model;

public class DiscountValue {

    private final int value;

    private final String text;

    public DiscountValue(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
