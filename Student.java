import java.util.ArrayList;

public class Student {
    private String id;
    private String name;
    private String major;
    private ArrayList<Subject> subjects;

    public Student(String name, String id, String major) {
        this.name = name;
        this.id = id;
        this.major = major;
        this.subjects = new ArrayList<>();
    }

    public void addSubject(Subject s) { subjects.add(s); }

    public void removeSubject(int index) {
        if (index >= 0 && index < subjects.size()) {
            subjects.remove(index);
        }
    }

    public double calculateGPA() {
        if (subjects.isEmpty()) return 0;
        double totalWeightedGrades = 0;
        int totalHours = 0;
        for (Subject s : subjects) {
            totalWeightedGrades += (s.getGrade() * s.getCreditHours());
            totalHours += s.getCreditHours();
        }
        return totalHours == 0 ? 0 : totalWeightedGrades / totalHours;
    }

    public String getStudentSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append(" | Name: ").append(name).append("\n");
        sb.append("Major: ").append(major).append("\n");
        sb.append("Subjects List:\n");
        for (int i = 0; i < subjects.size(); i++) {
            Subject s = subjects.get(i);
            sb.append("  ").append(i + 1).append(". ").append(s.getSubjectName())
              .append(" (Hours: ").append(s.getCreditHours())
              .append(", Grade: ").append(s.getGrade()).append(")\n");
        }
        sb.append("GPA: ").append(String.format("%.2f", calculateGPA())).append("\n");
        sb.append("---------------------------\n");
        return sb.toString();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public ArrayList<Subject> getSubjects() { return subjects; }
}