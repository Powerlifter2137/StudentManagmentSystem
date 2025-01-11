import java.util.ArrayList;
// Interfejs do zarządzania studentami
public interface StudentManager {
    //dodawanie studenta do bazy
    void addStudent(Student student) throws Exception;
    // Usuwanie studenta z bazy
    void removeStudent(String studentID) throws Exception;
    //Akutalizacja danych studenta
    void updateStudent(Student student) throws Exception;
    //Pobiera i zwraca liste studentow
    ArrayList<Student> displayAllStudents() throws Exception;
    //Do obliczania sredniej
    double calculateAverageGrade() throws Exception;
}
