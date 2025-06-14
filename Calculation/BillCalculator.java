package Calculation;
import java.lang.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BillCalculator {
    private double total;
    private double discount;
    private JRadioButton rb2, rb3, rb4;
    private JTextArea ta1, ta2;

    public BillCalculator(JRadioButton rb2, JRadioButton rb3, JRadioButton rb4, JTextArea ta1, JTextArea ta2) {
        this.rb2 = rb2;
        this.rb3 = rb3;
        this.rb4 = rb4;
        this.ta1 = ta1;
        this.ta2 = ta2;
    }

    public void calculate(double[] itemPrices, int[] quantities, int itemCount, String currentDateTime) {
        total = 0;
        for (int i = 0; i < itemCount; i++) {
            total += itemPrices[i] * quantities[i];
        }
        
        discount = 0;
        if (rb2.isSelected()) discount = 0.05;
        else if (rb3.isSelected()) discount = 0.10;
        else if (rb4.isSelected()) discount = 0.20;

        double totalDiscount = total - (total * discount);

        StringBuilder bill = new StringBuilder(ta1.getText());
        bill.append("\n======================");
        bill.append("\nSubtotal: ").append(total).append("\n");
        if (discount > 0) {
            bill.append("Discount: ").append(discount * 100).append("%\n");
            bill.append("Total after discount: ").append(totalDiscount).append("\n");
        } else {
            bill.append("Total: ").append(total).append("\n");
        }
        bill.append("Date/Time: ").append(currentDateTime).append("\n");

        ta1.setText(bill.toString());
        ta2.append("Order at " + currentDateTime + ":\n" + bill.toString() + "\n\n");
    }
}