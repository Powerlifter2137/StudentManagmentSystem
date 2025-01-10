import java.util.ArrayList;

public interface StudentManager {
    void addStudent(Student student) throws Exception;
    void removeStudent(String studentID) throws Exception;
    void updateStudent(Student student) throws Exception;
    ArrayList<Student> displayAllStudents() throws Exception;
    double calculateAverageGrade() throws Exception;
}
