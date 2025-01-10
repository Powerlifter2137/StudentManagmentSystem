import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTextField studentIdField, nameField, ageField, gradeField;
    private JTextArea outputArea;
    private JButton addButton, removeButton, updateButton, displayButton, averageButton;

    public MainFrame() {
        // Ustawienia podstawowe
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel wejściowy
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        inputPanel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField();
        inputPanel.add(studentIdField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        inputPanel.add(gradeField);

        addButton = new JButton("Add Student");
        removeButton = new JButton("Remove Student");
        updateButton = new JButton("Update Student");
        displayButton = new JButton("Display All Students");
        averageButton = new JButton("Calculate Average");

        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        inputPanel.add(updateButton);
        inputPanel.add(displayButton);
        inputPanel.add(averageButton);

        add(inputPanel, BorderLayout.NORTH);

        // Panel wyjściowy
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // Obsługa zdarzeń
        addActionListeners();
    }

    private void addActionListeners() {
        addButton.addActionListener(e -> handleAddStudent());
        removeButton.addActionListener(e -> handleRemoveStudent());
        updateButton.addActionListener(e -> handleUpdateStudent());
        displayButton.addActionListener(e -> handleDisplayAllStudents());
        averageButton.addActionListener(e -> handleCalculateAverage());
    }

    private void handleAddStudent() {
        String studentID = studentIdField.getText();
        String name = nameField.getText();
        String ageText = ageField.getText();
        String gradeText = gradeField.getText();

        try {
            int age = Integer.parseInt(ageText);
            double grade = Double.parseDouble(gradeText);

            if (age <= 0 || grade < 0.0 || grade > 100.0) {
                outputArea.append("Error: Invalid age or grade range.\n");
                return;
            }

            Student student = new Student(studentID, name, age, grade);
            StudentManagerImpl manager = new StudentManagerImpl();
            manager.addStudent(student);
            outputArea.append("Student added successfully.\n");
        } catch (NumberFormatException ex) {
            outputArea.append("Error: Invalid input format.\n");
        } catch (Exception ex) {
            outputArea.append("Error: " + ex.getMessage() + "\n");
        }
    }

    private void handleRemoveStudent() {
        String studentID = studentIdField.getText();

        try {
            StudentManagerImpl manager = new StudentManagerImpl();
            manager.removeStudent(studentID);
            outputArea.append("Student removed successfully.\n");
        } catch (Exception ex) {
            outputArea.append("Error: " + ex.getMessage() + "\n");
        }
    }

    private void handleUpdateStudent() {
        String studentID = studentIdField.getText();
        String name = nameField.getText();
        String ageText = ageField.getText();
        String gradeText = gradeField.getText();

        try {
            int age = Integer.parseInt(ageText);
            double grade = Double.parseDouble(gradeText);

            if (age <= 0 || grade < 0.0 || grade > 100.0) {
                outputArea.append("Error: Invalid age or grade range.\n");
                return;
            }

            Student student = new Student(studentID, name, age, grade);

            StudentManagerImpl manager = new StudentManagerImpl();
            manager.updateStudent(student);

            outputArea.append("Student updated successfully.\n");
        } catch (NumberFormatException ex) {
            outputArea.append("Error: Invalid input format.\n");
        } catch (Exception ex) {
            outputArea.append("Error: " + ex.getMessage() + "\n");
        }
    }

    private void handleDisplayAllStudents() {
        try {
            StudentManagerImpl manager = new StudentManagerImpl();
            for (Student student : manager.displayAllStudents()) {
                outputArea.append(student.toString() + "\n");
            }
        } catch (Exception ex) {
            outputArea.append("Error: " + ex.getMessage() + "\n");
        }
    }

    private void handleCalculateAverage() {
        try {
            StudentManagerImpl manager = new StudentManagerImpl();
            double average = manager.calculateAverageGrade();
            outputArea.append("Average grade: " + average + "\n");
        } catch (Exception ex) {
            outputArea.append("Error: " + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
