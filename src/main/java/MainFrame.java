import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTextField studentIdField, nameField, ageField, gradeField;
    private JTextArea outputArea;
    private JButton addButton, removeButton, updateButton, displayButton, averageButton;

    public MainFrame() {
        // Ustawienia podstawowe
        setTitle("Student Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null); // Wyśrodkowanie okna

        // Ustawienie stylu Nimbus
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {
        }

        // Panel wejściowy
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Student ID:"), gbc);

        gbc.gridx = 1;
        studentIdField = new JTextField(20);
        inputPanel.add(studentIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(20);
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Age:"), gbc);

        gbc.gridx = 1;
        ageField = new JTextField(20);
        inputPanel.add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Grade:"), gbc);

        gbc.gridx = 1;
        gradeField = new JTextField(20);
        inputPanel.add(gradeField, gbc);

        add(inputPanel, BorderLayout.NORTH);

        // Panel przycisków
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        updateButton = new JButton("Update");
        displayButton = new JButton("Display");
        averageButton = new JButton("Average");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(averageButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Panel wyjściowy
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Output"));

        outputArea = new JTextArea(15, 50);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        outputPanel.add(scrollPane, BorderLayout.CENTER);

        add(outputPanel, BorderLayout.SOUTH);

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