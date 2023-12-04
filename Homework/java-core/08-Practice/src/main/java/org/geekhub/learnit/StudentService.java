package org.geekhub.learnit;

import org.geekhub.learnit.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentService {

    private List<Student> students;
    private final Scanner scanner;

    public StudentService(List<Student> students, Scanner scanner) {
        this.students = students;
        this.scanner = scanner;
    }

    //TODO: Implement all of the methods below
    public void addStudent() {
//        System.out.println("Enter name:");
//        String name = scanner.nextLine();
//        try {
//            System.out.println("Enter subject. Empty if exit");
//            String subjectName = scanner.nextLine();
//            if (subjectName.isEmpty()) {
//                throw new Exception("Empty subject name.");
//            } else {
//                System.out.println("Please enter from 0-100 to set " + subjectName + " score.");
//                int subjectScore = Integer.parseInt(scanner.nextLine());
//                if (subjectScore > 0 && subjectScore <= 100) {
//                    scores.put(subjectName, (double) subjectScore);
//                } else {
//                    throw new Exception("Score is less than 0 or higher than 100");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Student studentnew = new Student(name, new HashMap<>(scores));
//        students.add(studentnew);
    }

    public void getLastCreatedStudentScores() {
//        if (!students.isEmpty()) {
//            StringBuilder string = new StringBuilder().append("Student: ").append(student.name()).append("scores: ");
//            for (Map.Entry scores2 : scores.entrySet()) {
//                string.append("%nSubject :").append(scores2.getKey()).append(" scores: ").append(scores2.getValue());
//            }
//            System.out.println(string.toString());
//        }
    }

    public void getSpecificStudentInfo() {
//        System.out.println("In which student you're interested in?");
//        int i = 0;
//        int i1 = 0;
//        for (Student student1 : students) {
//            String string = new StringBuilder().append("Student: ").append(student1.name()).append(" index: ").append(i++).toString();
//            System.out.println(string);
//        }
//
//        System.out.println("Enter index:");
//        i1 = Integer.parseInt(scanner.nextLine());
//        Student student1 = students.get(i1);
//        if (student1 != null) {
//            StringBuilder string = new StringBuilder().append("Student: ").append(student1.name()).append(" scores: ");
//            for (Map.Entry scores2 : student1.scores().entrySet()) {
//                string.append("%nSubject :").append(scores2.getKey()).append(" scores: ").append(scores2.getValue());
//            }
//            System.out.println(string.toString());
//        } else {
//            try {
//                throw new Exception("No students in the list");
//            } catch (Throwable e) {
//                isRunning = false;
//                System.out.println("Error!");
//            }
//        }
    }

    public void getAverageStudentsScores() {
//        if (!students.isEmpty()) {
//            for (Student student2 : students) {
//                Map<String, Double> scores3 = student2.scores();
//
//                double averageScore = scores3.values().stream()
//                    .mapToDouble(Double::doubleValue)
//                    .average()
//                    .orElse(0);
//                System.out.println("Student " + student2.name() + " score is " + averageScore);
//            }
//            break;
//        }
//        System.out.println("No student found!");
    }
}
