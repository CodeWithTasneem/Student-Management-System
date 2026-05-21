import java.util.ArrayList;

public class StudentManagementSystem {
    private ArrayList<Student> students = new ArrayList<>();

    public void addNewStudent(Student s) { students.add(s); }

    public Student searchForStudent(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) return s;
        }
        return null;
    }

    public void removeStudent(String id) {
        students.removeIf(s -> s.getId().equals(id));
    }

    public String getAllStudentsData() {
        if (students.isEmpty()) return "No students recorded.";
        StringBuilder sb = new StringBuilder();
        for (Student s : students) sb.append(s.getStudentSummary());
        return sb.toString();
    }

    public String getHighestGPAInfo() {
        if (students.isEmpty()) return "List is empty.";
        Student highest = students.get(0);
        for (Student s : students) {
            if (s.calculateGPA() > highest.calculateGPA()) highest = s;
        }
        return "Top Student: " + highest.getName() + " | GPA: " + String.format("%.2f", highest.calculateGPA());
    }
}