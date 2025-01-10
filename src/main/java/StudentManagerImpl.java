import java.sql.*;
import java.util.ArrayList;

public class StudentManagerImpl implements StudentManager {
    private static final String DB_URL = "jdbc:sqlite:src/main/resources/students.db";

    public StudentManagerImpl() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                    "studentID TEXT PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "age INTEGER NOT NULL, " +
                    "grade REAL NOT NULL)";
            statement.execute(createTableSQL);
        }
    }

    @Override
    public void addStudent(Student student) throws Exception {
        String insertSQL = "INSERT INTO students (studentID, name, age, grade) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, student.getStudentID());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setDouble(4, student.getGrade());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void removeStudent(String studentID) throws Exception {
        String deleteSQL = "DELETE FROM students WHERE studentID = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setString(1, studentID);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("No student found with ID: " + studentID);
            }
        }
    }

    @Override
    public void updateStudent(Student student) throws Exception {
        String updateSQL = "UPDATE students SET name = ?, age = ?, grade = ? WHERE studentID = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setDouble(3, student.getGrade());
            preparedStatement.setString(4, student.getStudentID());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("No student found with ID: " + student.getStudentID());
            }
        }
    }

    @Override
    public ArrayList<Student> displayAllStudents() throws Exception {
        String querySQL = "SELECT * FROM students";
        ArrayList<Student> students = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(querySQL)) {
            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getString("studentID"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getDouble("grade")
                ));
            }
        }
        return students;
    }

    @Override
    public double calculateAverageGrade() throws Exception {
        String querySQL = "SELECT AVG(grade) AS averageGrade FROM students";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(querySQL)) {
            if (resultSet.next()) {
                return resultSet.getDouble("averageGrade");
            } else {
                throw new Exception("No students found in the database.");
            }
        }
    }
}
