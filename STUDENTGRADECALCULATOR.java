package Task2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class STUDENTGRADECALCULATOR {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new STUDENTGRADECALCULATOR().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Student Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 5, 5));

        JLabel subjectsLabel = new JLabel("Number of subjects:");
        JTextField subjectsField = new JTextField();
        JButton enterMarksButton = new JButton("Enter Marks");
        JButton calculateButton = new JButton("Calculate");
        JTextArea resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);

        panel.add(subjectsLabel);
        panel.add(subjectsField);
        panel.add(enterMarksButton);
        panel.add(calculateButton);
        panel.add(new JScrollPane(resultArea));

        frame.add(panel);
        frame.setVisible(true);

        Student student = new Student();
        
        enterMarksButton.addActionListener(e -> {
            int numSubjects;
            try {
                numSubjects = Integer.parseInt(subjectsField.getText());
                student.inputMarks(numSubjects);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number of subjects.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (int i = 0; i < numSubjects; i++) {
                String markStr = JOptionPane.showInputDialog(frame, "Enter marks for subject " + (i + 1) + " (out of 100):");
                try {
                    double mark = Double.parseDouble(markStr);
                    student.setMark(i, mark);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid mark for subject " + (i + 1) + ".", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });

        calculateButton.addActionListener(e -> {
            student.calculateTotal();
            student.calculateAverage();
            student.assignGrade();
            resultArea.setText(student.displayResults());
        });
    }
}

class Student {
    private double[] marks;
    private double totalMarks;
    private double averagePercentage;
    private char grade;

    public void inputMarks(int numSubjects) {
        marks = new double[numSubjects];
    }

    public void setMark(int index, double mark) {
        marks[index] = mark;
    }

    public void calculateTotal() {
        totalMarks = 0;
        for (double mark : marks) {
            totalMarks += mark;
        }
    }

    public void calculateAverage() {
        averagePercentage = totalMarks / marks.length;
    }

    public void assignGrade() {
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
    }

    public String displayResults() {
        return String.format("Total Marks: %.2f\nAverage Percentage: %.2f%%\nGrade: %c", totalMarks, averagePercentage, grade);
    }
}