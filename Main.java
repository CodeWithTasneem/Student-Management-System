import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StudentManagementSystem system = new StudentManagementSystem();
        int choice;

        do {
            System.out.println("\n--- Code Crafters: Student Management System ---");
            System.out.println("1. Add New Student");
            System.out.println("2. Add Subject to Student");
            System.out.println("3. Display All Students");
            System.out.println("4. Search for Student (by ID)");
            System.out.println("5. Remove Student");
            System.out.println("6. Remove Subject from Student");
            System.out.println("7. Show Student with Highest GPA");
            System.out.println("8. Convert Numeric Grade to Letter");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            while (!input.hasNextInt()) { // Input Validation (أميرة سالم)
                System.out.println("Invalid input. Please enter a number.");
                input.next();
            }
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = validateName(input);
                    System.out.print("ID: ");
                    String id = input.nextLine();
                    System.out.print("Major: ");
                    String major = input.nextLine();
                    system.addNewStudent(new Student(name, id, major));
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String sId = input.nextLine();
                    Student s = system.searchForStudent(sId);
                    if (s != null) {
                        System.out.print("Subject Name: ");
                        String subName = validateName(input);
                        System.out.print("Hours: ");
                        int hours = input.nextInt();
                        System.out.print("Grade (0-100): ");
                        double grade = validateGrade(input);
                        s.addSubject(new Subject(subName, hours, grade));
                    } else System.out.println("Student not found.");
                    break;
                case 3: system.displayAllStudents(); break;
                case 4:
                    System.out.print("ID: ");
                    Student found = system.searchForStudent(input.nextLine());
                    if (found != null) found.displayStudentInfo();
                    else System.out.println("Not found.");
                    break;
                case 5:
                    System.out.print("ID to remove: ");
                    system.removeStudent(input.nextLine());
                    break;
                case 6:
                    System.out.print("Student ID: ");
                    String stId = input.nextLine();
                    System.out.print("Subject Name: ");
                    String subToRemove = input.nextLine();
                    system.removeSubjectFromStudent(stId, subToRemove);
                    break;
                case 7: system.showHighestGPA(); break;
                case 8:
                    System.out.print("Enter Grade: ");
                    double g = validateGrade(input);
                    System.out.println("Letter Grade: " + convertNumericToLetter(g));
                    break;
            }
        } while (choice != 9);
    }

    // التحقق من صحة البيانات (أميرة سالم) 
    public static String validateName(Scanner input) {
        String name;
        do {
            name = input.nextLine().trim();
            if (name.isEmpty()) System.out.print("Name cannot be empty! Re-enter: ");
        } while (name.isEmpty());
        return name;
    }

    public static double validateGrade(Scanner input) {
        double grade;
        while (true) {
            if (input.hasNextDouble()) {
                grade = input.nextDouble();
                if (grade >= 0 && grade <= 100) return grade;
            }
            System.out.print("Invalid Grade (0-100 only). Re-enter: ");
            if (!input.hasNextDouble()) input.next();
        }
    }

    // دالة التقديرات (محمد منصور) 
    public static String convertNumericToLetter(double grade) {
        if (grade >= 90) return "A";
        else if (grade >= 80) return "B";
        else if (grade >= 70) return "C";
        else if (grade >= 60) return "D";
        else return "F";
    }
}