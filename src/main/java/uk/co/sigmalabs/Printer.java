package uk.co.sigmalabs;

import java.text.DecimalFormat;

import uk.co.sigmalabs.model.Basket;

public class Printer {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private static final String LINE_BREAK = "\n";

    public void printBasketOutput(Basket basket) {
        StringBuilder stringBuilder = new StringBuilder();

        basket.getItems().forEach(b -> stringBuilder.append(b).append(LINE_BREAK));
        stringBuilder.append("Sub-total ")
            .append(toPounds(basket.getSubTotal()))
            .append(LINE_BREAK)
            .append(LINE_BREAK);

        basket.getDiscountText().forEach(d -> stringBuilder.append(d).append(LINE_BREAK));

        if(!basket.getDiscounts().isEmpty()) {
            stringBuilder.append(LINE_BREAK);
        }

        stringBuilder
            .append("Total ")
            .append(toPounds(basket.getTotal()));

        System.out.print(stringBuilder);
    }

    private String toPounds(int in) {
        double amount = (double) in /100;
        return "£" + decimalFormat.format(amount);
    }
}
