package org.geekhub.hw2;

import java.util.Scanner;

public class ApplicationStarter {

    public static final int DEFAULT_GRADE = -1;
    public static final int NAME_INDEX = 0;
    private static final String[] SUBJECTS = {"Memology", "Cybersport", "History"};
    private static final int NUMBER_OF_SUBJECTS = SUBJECTS.length;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: The application have no arguments");
            System.exit(1);
        }
        final Scanner scanner = new Scanner(System.in);
        String[][] studentsData = collectStudentsData(Integer.parseInt(args[0]), scanner);
        printAveragesByStudents(studentsData);
        printAveragesBySubjects(studentsData);
        scanner.close();
    }

    private static String[][] collectStudentsData(int numberOfStudents, Scanner scanner) {
        String[][] students = new String[numberOfStudents][NUMBER_OF_SUBJECTS + 1];
        for (int i = 0; i < numberOfStudents; i++) {
            String studentName = "";
            System.out.println("Enter information about student #" + (i + 1));
            while (studentName.isEmpty()) {
                System.out.printf(String.format("Student's name:%s", " "));
                studentName = scanner.nextLine();
            }
            students[i][NAME_INDEX] = studentName;
            collectStudentGrades(students[i], scanner);
            System.out.println();
        }
        return students;
    }

    private static void collectStudentGrades(String[] studentData, Scanner scanner) {
        for (int i = 1; i <= NUMBER_OF_SUBJECTS; i++) {
            float grade = DEFAULT_GRADE;
            while (grade < 0 || grade > 100) {
                System.out.printf(String.format("%s Grade (0-100): ", SUBJECTS[i - 1]));
                grade = scanner.nextFloat();
            }
            studentData[i] = String.valueOf(grade);
        }
    }

    private static void printAveragesByStudents(String[][] data) {
        System.out.println("Average grades for Each Student: ");
        for (int i = 0; i < data.length; i++) {
            System.out.printf(String.format("%s: ", data[i][NAME_INDEX]));
            System.out.println(calculateAverageForStudent(data[i]));
        }
        System.out.println();
    }

    private static float calculateAverageForStudent(String[] studentData) {
        float average = 0;
        for (int i = 1; i < studentData.length; i++) {
            average += Float.parseFloat(studentData[i]);
        }
        return average / NUMBER_OF_SUBJECTS;
    }

    private static void printAveragesBySubjects(String[][] data) {
        System.out.println("Average grades For Each Subject: ");
        for (int i = 1; i <= NUMBER_OF_SUBJECTS; i++) {
            System.out.printf(String.format("%s: ", SUBJECTS[i - 1]));
            System.out.println(calculateAverageForSubject(data, i));
        }
        System.out.println();
    }

    private static float calculateAverageForSubject(String[][] data, int subjectIndex) {
        float average = 0;
        for (int i = 0; i < data.length; i++) {
            average += Float.parseFloat(data[i][subjectIndex]);
        }
        return average / data.length;
    }
}
