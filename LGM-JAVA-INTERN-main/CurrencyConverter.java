import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.*;

public class CurrencyConverter extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 1500;
    private static final int HEIGHT = 500;

    private JLabel amountLabel;
    private JTextField amountTextField;
    private JLabel currencyLabel;
    private JComboBox<String> currencyComboBox;
    private JButton convertButton;
    private JLabel resultLabel;

    private double USDToEUR = 0.83;
    private double USDToJPY = 109.61;
    private double USDToGBP = 0.72;

    public CurrencyConverter() {
        super("Currency Converter");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.PINK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        amountLabel = new JLabel("Amount in USD:");
        gbc.gridx = 0;
        panel.add(amountLabel, gbc);

        amountTextField = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(amountTextField, gbc);

        currencyLabel = new JLabel("Currency:");
        gbc.gridx = 0;
        panel.add(currencyLabel, gbc);

        String[] currencies = { "EUR", "JPY", "GBP" };
        currencyComboBox = new JComboBox<String>(currencies);
        gbc.gridx = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(currencyComboBox, gbc);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(convertButton, gbc);

        resultLabel = new JLabel("");
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(resultLabel, gbc);

        add(panel);
        pack();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String usdAmountString = amountTextField.getText();
        String currency = (String) currencyComboBox.getSelectedItem();

        if (usdAmountString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an amount in USD.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double usdAmount = Double.parseDouble(usdAmountString);
        double convertedAmount = 0.0;

        if (currency.equalsIgnoreCase("EUR")) {
            convertedAmount = usdAmount * USDToEUR;
        } else if (currency.equalsIgnoreCase("JPY")) {
            convertedAmount = usdAmount * USDToJPY;
        } else if (currency.equalsIgnoreCase("GBP")) {
            convertedAmount = usdAmount * USDToGBP;
        } else {
            JOptionPane.showMessageDialog(this, "Invalid currency entered.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String formattedAmount = NumberFormat.getCurrencyInstance().format(convertedAmount);
        resultLabel.setText(formattedAmount);
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}
