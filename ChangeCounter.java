import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeCounter extends JFrame {
    private JTextField inputField;
    private JTextArea resultArea;
    private JButton calculateButton;

    public ChangeCounter() {
        setTitle("XYZ Supermarket");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new CardLayout());

        // Create and set up the panels
        JPanel inputPanel = createInputPanel();
        JPanel resultPanel = createResultPanel();

        // Add panels to the frame
        add(inputPanel, "Input");
        add(resultPanel, "Result");

        // Show the input panel initially
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Input");
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel inputLabel = new JLabel("Change:");
        inputField = new JTextField(11);
        calculateButton = new JButton("Calculate");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(inputLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(inputField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(calculateButton, gbc);

        // Add action listener to button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateChange();
            }
        });

        return panel;
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        panel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        panel.add(backButton, BorderLayout.SOUTH);

        // Add action listener to back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Input");
            }
        });

        return panel;
    }

    private void calculateChange() {
        try {
            String changeInput = inputField.getText();
            double change = Double.parseDouble(changeInput);

            // Convert the change to cents
            int changeInCents = (int) Math.round(change * 100);

            int fiveHundred = 0;
            int twoHundred = 0;
            int oneHundred = 0;
            int fifty = 0;
            int twenty = 0;
            int ten = 0;
            int five = 0;
            int one = 0;
            int tenCents = 0;
            int fiveCents = 0;
            int oneCent = 0;

            if (changeInCents >= 50000) {
                fiveHundred = changeInCents / 50000;
                changeInCents -= fiveHundred * 50000;
            }
            if (changeInCents >= 20000) {
                twoHundred = changeInCents / 20000;
                changeInCents -= twoHundred * 20000;
            }
            if (changeInCents >= 10000) {
                oneHundred = changeInCents / 10000;
                changeInCents -= oneHundred * 10000;
            }
            if (changeInCents >= 5000) {
                fifty = changeInCents / 5000;
                changeInCents -= fifty * 5000;
            }
            if (changeInCents >= 2000) {
                twenty = changeInCents / 2000;
                changeInCents -= twenty * 2000;
            }
            if (changeInCents >= 1000) {
                ten = changeInCents / 1000;
                changeInCents -= ten * 1000;
            }
            if (changeInCents >= 500) {
                five = changeInCents / 500;
                changeInCents -= five * 500;
            }
            if (changeInCents >= 100) {
                one = changeInCents / 100;
                changeInCents -= one * 100;
            }
            if (changeInCents >= 10) {
                tenCents = changeInCents / 10;
                changeInCents -= tenCents * 10;
            }
            if (changeInCents >= 5) {
                fiveCents = changeInCents / 5;
                changeInCents -= fiveCents * 5;
            }
            if (changeInCents >= 1) {
                oneCent = changeInCents;
                changeInCents -= oneCent;
            }

            // Construct the result string
            String result = "Change Count: " + changeInput + "\n";

            result += "Peso:\n500: " + (fiveHundred > 0 ? fiveHundred : "--") + "\n";
            result += "200: " + (twoHundred > 0 ? twoHundred : "--") + "\n";
            result += "100: " + (oneHundred > 0 ? oneHundred : "--") + "\n";
            result += "50: " + (fifty > 0 ? fifty : "--") + "\n";
            result += "20: " + (twenty > 0 ? twenty : "--") + "\n";
            result += "10: " + (ten > 0 ? ten : "--") + "\n";
            result += "5: " + (five > 0 ? five : "--") + "\n";
            result += "1: " + (one > 0 ? one : "--") + "\n\nCents:\n";
            result += "10c: " + (tenCents > 0 ? tenCents : "--") + "\n";
            result += "5c: " + (fiveCents > 0 ? fiveCents : "--") + "\n";
            result += "1c: " + (oneCent > 0 ? oneCent : "--") + "\n";

            // Display the result
            resultArea.setText(result);
            ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Result");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
       new ChangeCounter().setVisible(true);
    }
}
