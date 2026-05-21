public class Subject {
    private String subjectName;
    private int creditHours;
    private double grade;

    public Subject(String subjectName, int creditHours, double grade) {
        this.subjectName = subjectName;
        this.creditHours = creditHours;
        this.grade = grade;
    }

    public String getSubjectName() { return subjectName; }
    public int getCreditHours() { return creditHours; }
    public double getGrade() { return grade; }
}