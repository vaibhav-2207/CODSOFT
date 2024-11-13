

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGameGUI {

    private int number;
    private JTextField guessField;
    private JTextArea feedbackArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGameGUI().createAndShowGUI());
    }

    private void createAndShowGUI() {
        // Generate a random number between 1 and 100
        number = (int) (Math.random() * 100) + 1;

        // Create the main frame
        JFrame frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout(10, 10));

        // Create components
        JLabel instructionLabel = new JLabel("Guess a number between 1 and 100:");
        guessField = new JTextField(10);
        JButton guessButton = new JButton("Submit Guess");
        feedbackArea = new JTextArea(5, 20);
        feedbackArea.setEditable(false);
        
        // Set up the panel for user input
        JPanel inputPanel = new JPanel();
        inputPanel.add(instructionLabel);
        inputPanel.add(guessField);
        inputPanel.add(guessButton);

        // Add components to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(feedbackArea), BorderLayout.CENTER);

        // Add button action listener
        guessButton.addActionListener(new GuessButtonListener());

        // Show the frame
        frame.setVisible(true);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int userGuess = Integer.parseInt(guessField.getText());

                if (userGuess < 1 || userGuess > 100) {
                    feedbackArea.append("Please enter a number between 1 and 100.\n");
                } else if (userGuess < number) {
                    feedbackArea.append("Too low! Try again.\n");
                } else if (userGuess > number) {
                    feedbackArea.append("Too high! Try again.\n");
                } else {
                    feedbackArea.append("Congratulations! You've guessed the number: " + number + "\n");
                }

            } catch (NumberFormatException ex) {
                feedbackArea.append("Please enter a valid integer.\n");
            }

            // Clear the text field after each guess
            guessField.setText("");
        }
    }
}