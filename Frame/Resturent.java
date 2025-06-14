package Frame;
import Calculation.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Resturent extends JFrame implements MouseListener, ActionListener {
    private String[] itemNames = new String[100];
    private double[] itemPrices = new double[100];
    private int[] quantities = new int[100];
    private int itemCount = 0;

    private JPanel panel;
    private JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12;
    private JTextField t1, t2, t3;
    private JTextArea ta1, ta2;
    private JButton b1, b2, b3, b4, b5, b6, b7;
    private JRadioButton rb1, rb2, rb3, rb4;
    private ButtonGroup bg;
    private Font f1, f2, f3, f4;
    private Color c1, c2, c3, c4;
    private ImageIcon i1;
    private BillCalculator billCalculator;

    public Resturent() {
        super("Restaurant Management System");
        super.setBounds(300, 150, 1300, 800);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    

        //Button color
        c1 = new Color(255, 0, 0);//red
        c2 = new Color(255, 255, 255);//White
        c3 = new Color(153, 230, 255); // light blue
        c4 = new Color(128, 191, 255); // medium light blue.

        panel = new JPanel();
        panel.setBackground(c3);
        panel.setLayout(null);

        f1 = new Font("Comic Sans", Font.BOLD, 40);
        f2 = new Font("Cambria", Font.BOLD, 18);
        f3 = new Font("Cambria", Font.BOLD, 30);
		f4 = new Font ("Comic Sans", Font.BOLD, 15);

        l1 = new JLabel("Restaurant Management System");
        l1.setFont(f1);
        l1.setForeground(c1);
        l1.setBounds(350, 10, 700, 50);
        panel.add(l1);

        l2 = new JLabel("Enter Food Name");
        l2.setFont(f2);
        l2.setBounds(20, 70, 170, 40);
        panel.add(l2);

        t1 = new JTextField();
        t1.setFont(f2);
        t1.setBounds(200, 70, 180, 40);
        panel.add(t1);

        l3 = new JLabel("Quantity");
        l3.setFont(f2);
        l3.setBounds(20, 120, 150, 40);
        panel.add(l3);

        t2 = new JTextField();
        t2.setFont(f2);
        t2.setBounds(200, 120, 180, 40);
        panel.add(t2);

        t3 = new JTextField();
        t3.setFont(f2);
        t3.setBounds(20, 240, 180, 40);
        panel.add(t3);

        ta1 = new JTextArea();
		JScrollPane scrollPane1 = new JScrollPane(ta1);
        scrollPane1.setBounds(20, 490, 350, 200);
        ta1.setFont(f4);
		panel.add(scrollPane1);
        l10 = new JLabel("Total Bill");
        l10.setFont(f2);
        l10.setBounds(20, 450, 200, 40);
        panel.add(l10);

        ta2 = new JTextArea();
		JScrollPane scrollPane2 = new JScrollPane(ta2);
        scrollPane2.setBounds(970, 120, 290, 600);
        ta2.setFont(f4);
		panel.add(scrollPane2);
        l8 = new JLabel("Order History");
        l8.setFont(f3);
        l8.setBounds(970, 70, 200, 40);
        panel.add(l8);
        
        //Button
        l3 = new JLabel("Discount");
        l3.setFont(f2);
        l3.setBounds(150, 360, 150, 40);
        panel.add(l3);

        rb1 = new JRadioButton("0%");
        rb1.setBackground(c4);
        rb1.setOpaque(false);
        rb1.setBounds(20, 410, 80, 30); //(x, y, width, height)
        rb1.setFont(f2);
        panel.add(rb1);

        rb2 = new JRadioButton("5%");
        rb2.setOpaque(false);
        rb2.setBounds(100, 410, 90, 30); //(x, y, width, height)
        rb2.setFont(f2);
        panel.add(rb2);

        rb3 = new JRadioButton("10%");
        rb3.setOpaque(false);
        rb3.setBounds(190, 410, 90, 30); //(x, y, width, height)
        rb3.setFont(f2);
        panel.add(rb3);

        rb4 = new JRadioButton("20%");
        rb4.setOpaque(false);
        rb4.setBounds(280, 410, 90, 30); //(x, y, width, height)
        rb4.setFont(f2);
        panel.add(rb4);

        bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        bg.add(rb3);
        bg.add(rb4);

        b1 = new JButton("Add to Cart");
        b1.setBounds(20, 180, 150, 40);
        b1.setBackground(c4);
        b1.setFont(f2);
        b1.addActionListener(this);
        panel.add(b1);

        b2 = new JButton("Order");
        b2.setBounds(200, 180, 180, 40);
        b2.setBackground(c4);
        b2.setFont(f2);
        b2.addMouseListener(this);
        b2.addActionListener(this);
        panel.add(b2);

        b3 = new JButton("Remove Item");
        b3.setBounds(200, 240, 180, 40);
        b3.setBackground(c4);
        b3.setFont(f2);
		b3.addActionListener(this);
        panel.add(b3);

        b4 = new JButton("Exit");
        b4.setBackground(c1);
        b4.setForeground(c2);
        b4.setBounds(20, 700, 150, 40);
        b4.setFont(f2);
        b4.addActionListener(this);
        panel.add(b4);

        b5 = new JButton("Payment");
        b5.setBounds(20, 300, 180, 40);
        b5.setBackground(c4);
        b5.setFont(f2);
        b5.addMouseListener(this);
        panel.add(b5);

        b6 = new JButton("Save to File");
        b6.setBounds(200, 300, 180, 40);
        b6.setBackground(c4);
        b6.setFont(f2);
		b6.addActionListener(this);
        panel.add(b6);

        b7 = new JButton("Reset");
        b7.setBounds(220, 700, 150, 40);
        b7.setBackground(c4);
        b7.setFont(f2);
        b7.addActionListener(this);
        panel.add(b7);

        // ITem List
        l6 = new JLabel("Menu");
        l6.setFont(f3);
        l6.setBounds(600, 70, 170, 40);
        panel.add(l6);

        //item 1
        i1 = new ImageIcon("Picture/Burger.png");
        l7 = new JLabel(i1);
        l7.setBounds(420, 120, 100, 100);
        panel.add(l7);
        l6 = new JLabel("Burger- 120/-");
        l6.setFont(f2);
        l6.setBounds(420, 200, 170, 40);
        panel.add(l6);

        //item 2
        i1 = new ImageIcon("Picture/Pizza.png");
        l7 = new JLabel(i1);
        l7.setBounds(620, 120, 100, 100);
        panel.add(l7);
        l6 = new JLabel("Pizza- 300/-");
        l6.setFont(f2);
        l6.setBounds(620, 200, 170, 40);
        panel.add(l6);

        //item 3
        i1 = new ImageIcon("Picture/Sandwich.png");
        l7 = new JLabel(i1);
        l7.setBounds(820, 120, 100, 100);
        panel.add(l7);
        l6 = new JLabel("Sandwich- 150/-");
        l6.setFont(f2);
        l6.setBounds(820, 200, 170, 40);
        panel.add(l6);

        //item 4
        i1 = new ImageIcon("Picture/HotDog.png");
        l7 = new JLabel(i1);
        l7.setBounds(420, 250, 100, 100);
        panel.add(l7);
        l6 = new JLabel("HotDog-100/-");
        l6.setFont(f2);
        l6.setBounds(420, 340, 170, 40);
        panel.add(l6);

        //item 5
        i1 = new ImageIcon("Picture/ChickenFry.png");
        l7 = new JLabel(i1);
        l7.setBounds(620, 250, 100, 100);
        panel.add(l7);
        l6 = new JLabel("Chicken Fry- 180/-");
        l6.setFont(f2);
        l6.setBounds(610, 340, 170, 40);
        panel.add(l6);

        //item 6
        i1 = new ImageIcon("Picture/FruitsSalad.png");
        l7 = new JLabel(i1);
        l7.setBounds(820, 250, 100, 100);
        panel.add(l7);
        l6 = new JLabel("Fruits Salad- 130/-");
        l6.setFont(f2);
        l6.setBounds(800, 340, 170, 40);
        panel.add(l6);

        //item 7
        i1 = new ImageIcon("Picture/FrenchFries.png");
        l7 = new JLabel(i1);
        l7.setBounds(420, 380, 100, 100);
        panel.add(l7);
        l6 = new JLabel("FrenchFries- 90/-");
        l6.setFont(f2);
        l6.setBounds(420, 470, 170, 40);
        panel.add(l6);

        //item 8
        i1 = new ImageIcon("Picture/Coffee.png");
        l7 = new JLabel(i1);
        l7.setBounds(620, 380, 100, 100);
        panel.add(l7);
        l6 = new JLabel("Coffee- 80/-");
        l6.setFont(f2);
        l6.setBounds(620, 470, 170, 40);
        panel.add(l6);

        //item 9
        i1 = new ImageIcon("Picture/Tea.png");
        l7 = new JLabel(i1);
        l7.setBounds(820, 380, 100, 100);
        panel.add(l7);
        l6 = new JLabel("Tea- 50/-");
        l6.setFont(f2);
        l6.setBounds(830, 470, 170, 40);
        panel.add(l6);

        //item 10
        i1 = new ImageIcon("Picture/ChickenBiryany.png");
        l7 = new JLabel(i1);
        l7.setBounds(420, 520, 100, 100);
        panel.add(l7);
        l6 = new JLabel("Biryany- 190/-");
        l6.setFont(f2);
        l6.setBounds(420, 610, 170, 40);
        panel.add(l6);

        //item 11
        i1 = new ImageIcon("Picture/FriedRice.png");
        l7 = new JLabel(i1);
        l7.setBounds(620, 520, 100, 100);
        panel.add(l7);
        l6 = new JLabel("FriedRice- 250/-");
        l6.setFont(f2);
        l6.setBounds(620, 610, 170, 40);
        panel.add(l6);

        //item 12
        i1 = new ImageIcon("Picture/ColdDrinks.png");
        l7 = new JLabel(i1);
        l7.setBounds(820, 520, 100, 100);
        panel.add(l7);
        l6 = new JLabel("ColdDrinks- 50/-");
        l6.setFont(f2);
        l6.setBounds(820, 610, 170, 40);
        panel.add(l6);
        
        
        billCalculator = new BillCalculator(rb2, rb3, rb4, ta1, ta2);

        super.add(panel);
    }

    private double getItemPrice(String itemName) {
        switch (itemName.toLowerCase()) {
            case "burger":
                return 120;
            case "pizza":
                return 300;
            case "sandwich":
                return 150;
            case "hotdog":
                return 100;
            case "chicken fry":
                return 180;
            case "fruits salad":
                return 130;
            case "frenchfries":
                return 90;
            case "coffee":
                return 80;
            case "tea":
                return 50;
            case "biryany":
                return 190;
            case "friedrice":
                return 250;
            case "colddrinks":
                return 50;
            default:
                return 0;
        }
    }

    private String getCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private void cart() {
        StringBuilder cart = new StringBuilder();
        double total = 0;
        double subtotal = 0;

        for (int i = 0; i < itemCount; i++) {
            double itemTotal = itemPrices[i] * quantities[i];
            subtotal += itemTotal;
            cart.append(itemNames[i]).append(" X ").append(quantities[i]).append(" = ").append(itemTotal).append("\n");
            total += itemTotal;
        }
        ta1.setText(cart.toString());
    }

    public void mouseClicked(MouseEvent me) {}
    public void mousePressed(MouseEvent me) {
        if (me.getSource() == b5) {
            if (itemCount == 0) {
                JOptionPane.showMessageDialog(this, "Cart is empty. Add items first.");
            } else {
                JOptionPane.showMessageDialog(this, "Payment Received");
                itemCount = 0;
                ta1.setText(" ");
            }
        }
    }
    public void mouseReleased(MouseEvent me) {}
    public void mouseEntered(MouseEvent me) {}
    public void mouseExited(MouseEvent me) {}

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) { // add to cart
            String itemName = t1.getText();
            String quantityStr = t2.getText();

            if (itemName.isEmpty() || quantityStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter item name & Quantity");
            } else {
                double price = getItemPrice(itemName.toLowerCase());
                int quantity = Integer.parseInt(quantityStr);
                itemNames[itemCount] = itemName;
                itemPrices[itemCount] = price;
                quantities[itemCount] = quantity;
                itemCount++;

                JOptionPane.showMessageDialog(this, "Added to cart Successfully");
                t1.setText("");
                t2.setText("");
                cart();
            }
        }
		if (ae.getSource() == b2) // Order
		{  
            String s1 = ta1.getText();

            if (s1.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter item name & Quantity");
            } else {
                b2.setText("Order Confirmed");
                b2.setBackground(Color.GREEN);
                billCalculator.calculate(itemPrices, quantities, itemCount, getCurrentDateTime());
                t1.setText("");
                t2.setText("");
            }
        }
		if (ae.getSource() == b3)  // remove item
        {
            String itemRemove = t3.getText();
            if(itemRemove.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please enter item name to remove");
            }
            else
            {
                boolean found = false;
                for (int i = 0; i < itemCount; i++)
                {
                    if (itemNames[i].equalsIgnoreCase(itemRemove))
                    {
                        for (int j = i; j < itemCount - 1; j++) {
                            itemNames[j] = itemNames[j+1];
                            itemPrices[j] = itemPrices[j+1];
                            quantities[j] = quantities[j+1];
                        }
                        itemCount--;
                        found = true;
                        break;
                    }
                }
                if (found)
                {
                    cart();
                    JOptionPane.showMessageDialog(this, "Item removed from cart");
                    t3.setText("");
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this, "Item not found in cart");
                }
            
            }
        }
        if (ae.getSource() == b4) {
            System.exit(0);
        }
		if (ae.getSource() == b6)
		{
			try {
				BufferedWriter writer = new BufferedWriter (new FileWriter ("./Data/order_history.txt",true));
				writer.write(ta2.getText());
				writer.close();
				JOptionPane.showMessageDialog(this, "Order history seved to file");
			}
			catch(IOException e)
			{
				JOptionPane.showMessageDialog(this, "Error saving to file");
			}
		}
        
        
        
        if (ae.getSource() == b7) {
            t1.setText("");
            t2.setText("");
            ta1.setText("");
            ta2.setText("");
            b2.setText("Order");
            b2.setBackground(c4);
            itemCount = 0;
        }
    }
}