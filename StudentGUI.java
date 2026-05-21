import javax.swing.*;
import java.awt.*;

public class StudentGUI extends JFrame {
    private StudentManagementSystem system = new StudentManagementSystem();

    public StudentGUI() {
        setTitle("Student Management System - BAUT");
        setSize(550, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 248, 255));
        setLayout(null);

        JButton btnAdd = new JButton("1. Add/Assign Subject | اضافة مادة");
        JButton btnDisplay = new JButton("2. Display All Students | عرض الطلاب");
        JButton btnHighest = new JButton("3. Highest GPA Student | اعلى معدل");
        JButton btnRemoveSub = new JButton("4. Remove Subject | حذف مادة"); // الزر الذي كان لا يعمل
        JButton btnRemoveStu = new JButton("5. Remove Student | حذف طالب");

        btnAdd.setBounds(50, 30, 430, 40);
        btnDisplay.setBounds(50, 80, 430, 40);
        btnHighest.setBounds(50, 130, 430, 40);
        btnRemoveSub.setBounds(50, 180, 430, 40);
        btnRemoveStu.setBounds(50, 230, 430, 40);

        add(btnAdd); add(btnDisplay); add(btnHighest); add(btnRemoveSub); add(btnRemoveStu);

        // إضافة طالب أو مادة
        btnAdd.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Enter Student ID:");
            if (id == null || id.isEmpty()) return;
            Student s = system.searchForStudent(id);
            if (s == null) {
                String name = JOptionPane.showInputDialog("New Student Name:");
                String major = JOptionPane.showInputDialog("Major:");
                s = new Student(name, id, major);
                system.addNewStudent(s);
            }
            try {
                String subName = JOptionPane.showInputDialog("Subject Name:");
                double grade = Double.parseDouble(JOptionPane.showInputDialog("Grade (0-100):"));
                int hours = Integer.parseInt(JOptionPane.showInputDialog("Credit Hours:"));
                if (grade < 0 || grade > 100) JOptionPane.showMessageDialog(this, "Invalid Grade!");
                else {
                    s.addSubject(new Subject(subName, hours, grade));
                    JOptionPane.showMessageDialog(this, "Success!");
                }
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Input Error!"); }
        });

        // عرض كل الطلاب
        btnDisplay.addActionListener(e -> {
            JTextArea area = new JTextArea(system.getAllStudentsData());
            area.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(area), "Records", 1);
        });

        // أعلى معدل
        btnHighest.addActionListener(e -> JOptionPane.showMessageDialog(this, system.getHighestGPAInfo()));

        // --- ميزة حذف مادة معينة (المعدلة) ---
        btnRemoveSub.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("أدخل ID الطالب:");
            Student s = system.searchForStudent(id);
            if (s != null) {
                if (s.getSubjects().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "هذا الطالب ليس لديه مواد مسجلة.");
                    return;
                }
                
                // عرض المواد المتاحة للحذف
                StringBuilder list = new StringBuilder("اختر رقم المادة لحذفها:\n");
                for (int i = 0; i < s.getSubjects().size(); i++) {
                    list.append((i + 1)).append(". ").append(s.getSubjects().get(i).getSubjectName()).append("\n");
                }
                
                String input = JOptionPane.showInputDialog(list.toString());
                try {
                    int index = Integer.parseInt(input) - 1;
                    if (index >= 0 && index < s.getSubjects().size()) {
                        s.removeSubject(index); // حذف المادة من قائمة الطالب
                        JOptionPane.showMessageDialog(this, "تم حذف المادة بنجاح.");
                    } else {
                        JOptionPane.showMessageDialog(this, "رقم غير صحيح!");
                    }
                } catch (Exception ex) { JOptionPane.showMessageDialog(this, "برجاء إدخال رقم المادة فقط."); }
            } else {
                JOptionPane.showMessageDialog(this, "الطالب غير موجود!");
            }
        });

        // حذف طالب بالكامل
        btnRemoveStu.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("ID to remove:");
            if (system.searchForStudent(id) != null) {
                system.removeStudent(id);
                JOptionPane.showMessageDialog(this, "Student Deleted!");
            } else JOptionPane.showMessageDialog(this, "Not Found!");
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentGUI();
    }
}