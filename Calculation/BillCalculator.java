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

    public void calculate(double[] itemPrices, int[] quantities, int itemCount, String currentDateTime) 
	    {
        total = 0;
        for (int i = 0; i < itemCount; i++) {
            total += itemPrices[i] * quantities[i];
        }
        
        discount = 0;
        if (rb2.isSelected()) discount = 0.05;
        else if (rb3.isSelected()) discount = 0.10;
        else if (rb4.isSelected()) discount = 0.20;

        double totalDiscount = total - (total * discount);
		
		String bill = ta1.getText();
        bill += "\n======================";
        bill += "\nSubtotal: " + total + "\n";

        if (discount > 0) {
        bill += "Discount: " + (discount * 100) + "%\n";
        bill += "Total after discount: " + totalDiscount + "\n";
        } else {
        bill += "Total: " + total + "\n";
        }

        bill += "Date/Time: " + currentDateTime + "\n";

        ta1.setText(bill);
        ta2.append("Order at " + currentDateTime + ":\n" + bill + "\n\n");

    }
}