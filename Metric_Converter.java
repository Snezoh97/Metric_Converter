import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Metric_Converter extends JFrame {
    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
    private JTextField quantityField;
    private JLabel resultLabel;

    public Metric_Converter() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Unit Converter");
        setLayout(null);

        JLabel fromLabel = new JLabel("Convert From:");
        fromLabel.setBounds(20, 20, 100, 25);
        add(fromLabel);

        fromComboBox = new JComboBox<>();
        fromComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Feet", "Pounds", "Fahrenheit"}));
        fromComboBox.setBounds(130, 20, 120, 25);
        add(fromComboBox);

        JLabel toLabel = new JLabel("Convert To:");
        toLabel.setBounds(20, 60, 100, 25);
        add(toLabel);

        toComboBox = new JComboBox<>();
        toComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Meters", "Kgs", "Celsius"}));
        toComboBox.setBounds(130, 60, 120, 25);
        add(toComboBox);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(20, 100, 100, 25);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(130, 100, 120, 25);
        add(quantityField);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(100, 140, 100, 25);
        add(convertButton);

        resultLabel = new JLabel("Result:");
        resultLabel.setBounds(20, 180, 230, 25);
        resultLabel.setForeground(Color.BLACK);
        add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                convertButtonActionPerformed(evt);
            }
        });

        setSize(300, 250);
        setLocationRelativeTo(null);
    }

    private void convertButtonActionPerformed(ActionEvent evt) {
        // Get the selected units and quantity
        String fromUnit = fromComboBox.getSelectedItem().toString();
        String toUnit = toComboBox.getSelectedItem().toString();
        String quantityText = quantityField.getText();

        if (quantityText.isEmpty()) {
            resultLabel.setText("Please enter a quantity!");
            return;
        }

        try {
            double quantity = Double.parseDouble(quantityText);

            // Perform the conversion based on user input
            double result;

            if (fromUnit.equalsIgnoreCase("Feet") && toUnit.equalsIgnoreCase("Meters")) {
                result = quantity * 0.3048;
            } else if (fromUnit.equalsIgnoreCase("Pounds") && toUnit.equalsIgnoreCase("Kgs")) {
                result = quantity * 0.453592;
            } else if (fromUnit.equalsIgnoreCase("Fahrenheit") && toUnit.equalsIgnoreCase("Celsius")) {
                result = (quantity - 32) * 5 / 9;
            } else {
                resultLabel.setText("Invalid conversion!");
                return;
            }

            // Format the result with two decimal places
            String formattedResult = String.format("%.2f", result);

            // Determine the appropriate symbol for the converted unit
            String symbol;
            if (toUnit.equalsIgnoreCase("Meters")) {
                symbol = "m";
            } else if (toUnit.equalsIgnoreCase("Kgs")) {
                symbol = "kg";
            } else if (toUnit.equalsIgnoreCase("Celsius")) {
                symbol = "°C";
            } else {
                symbol = "";
            }

            // Display the formatted result with the symbol
            resultLabel.setText("Result: " + formattedResult + symbol);
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid quantity!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Metric_Converter().setVisible(true);
        });
    }
}