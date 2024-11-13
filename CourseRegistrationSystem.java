package TASK3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;
    int slotsAvailable;

    Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.slotsAvailable = capacity;
    }
}

class Student {
    String studentID;
    String name;
    ArrayList<Course> registeredCourses;

    Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    void registerCourse(Course course) {
        if (!registeredCourses.contains(course) && course.slotsAvailable > 0) {
            registeredCourses.add(course);
            course.slotsAvailable--;
        }
    }

    void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.slotsAvailable++;
        }
    }
}

public class CourseRegistrationSystem extends JFrame {
    ArrayList<Course> courses;
    ArrayList<Student> students;
    JTextArea courseListArea;
    JTextArea studentArea;
    JTextField studentIDField;
    JTextField studentNameField;
    JTextField courseCodeField;

    public CourseRegistrationSystem() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
        setupUI();
        initializeSampleData();
    }

    private void setupUI() {
        setTitle("Course Registration System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(2, 1));

        JPanel coursePanel = new JPanel(new BorderLayout());
        courseListArea = new JTextArea(10, 30);
        coursePanel.add(new JLabel("Available Courses"), BorderLayout.NORTH);
        coursePanel.add(new JScrollPane(courseListArea), BorderLayout.CENTER);
        mainPanel.add(coursePanel);

        JPanel studentPanel = new JPanel(new BorderLayout());
        studentArea = new JTextArea(10, 30);
        studentPanel.add(new JLabel("Student Registration"), BorderLayout.NORTH);
        studentPanel.add(new JScrollPane(studentArea), BorderLayout.CENTER);
        mainPanel.add(studentPanel);

        add(mainPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        studentIDField = new JTextField(5);
        studentNameField = new JTextField(10);
        courseCodeField = new JTextField(5);
        
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new RegisterListener());

        JButton dropButton = new JButton("Drop");
        dropButton.addActionListener(new DropListener());

        controlPanel.add(new JLabel("Student ID"));
        controlPanel.add(studentIDField);
        controlPanel.add(new JLabel("Name"));
        controlPanel.add(studentNameField);
        controlPanel.add(new JLabel("Course Code"));
        controlPanel.add(courseCodeField);
        controlPanel.add(registerButton);
        controlPanel.add(dropButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    private void initializeSampleData() {
        courses.add(new Course("CSE101", "Intro to CS", "Basics of computer science", 30, "MWF 9AM"));
        courses.add(new Course("MATH123", "Calculus I", "Introduction to calculus", 25, "TTh 11AM"));
        courses.add(new Course("PHYS201", "Physics I", "Fundamentals of physics", 20, "MWF 1PM"));
        refreshCourseList();
    }

    private void refreshCourseList() {
        courseListArea.setText("");
        for (Course course : courses) {
            courseListArea.append(course.courseCode + ": " + course.title + " (" + course.slotsAvailable + " slots available)\n");
            courseListArea.append("Schedule: " + course.schedule + "\n\n");
        }
    }

    private void refreshStudentArea(Student student) {
        studentArea.setText("Student ID: " + student.studentID + "\nName: " + student.name + "\nRegistered Courses:\n");
        for (Course course : student.registeredCourses) {
            studentArea.append(course.courseCode + " - " + course.title + "\n");
        }
    }

    private Student findOrCreateStudent(String studentID, String name) {
        for (Student student : students) {
            if (student.studentID.equals(studentID)) {
                return student;
            }
        }
        Student newStudent = new Student(studentID, name);
        students.add(newStudent);
        return newStudent;
    }

    private Course findCourse(String courseCode) {
        for (Course course : courses) {
            if (course.courseCode.equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    class RegisterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String studentID = studentIDField.getText();
            String name = studentNameField.getText();
            String courseCode = courseCodeField.getText();
            Student student = findOrCreateStudent(studentID, name);
            Course course = findCourse(courseCode);

            if (course != null) {
                student.registerCourse(course);
                refreshCourseList();
                refreshStudentArea(student);
            } else {
                JOptionPane.showMessageDialog(null, "Course not found");
            }
        }
    }

    class DropListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String studentID = studentIDField.getText();
            String courseCode = courseCodeField.getText();
            Student student = findOrCreateStudent(studentID, studentNameField.getText());
            Course course = findCourse(courseCode);

            if (course != null) {
                student.dropCourse(course);
                refreshCourseList();
                refreshStudentArea(student);
            } else {
                JOptionPane.showMessageDialog(null, "Course not found");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CourseRegistrationSystem app = new CourseRegistrationSystem();
            app.setVisible(true);
        });
    }
}
