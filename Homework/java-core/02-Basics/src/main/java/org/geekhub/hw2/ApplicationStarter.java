package org.geekhub.hw2;

import java.util.Scanner;

public class ApplicationStarter {

    private static final String[] SUBJECTS = {"Memology", "Cybersport", "History"};
    private static final int NUMBER_OF_SUBJECTS = SUBJECTS.length;
    public static final int DEFAULT_GRADE = -1;
    public static final int NAME_INDEX = 0;
    private static final Scanner scanner = new Scanner(System.in);

    private static void printAveragesByStudents(String[][] data) {
        System.out.println("Average grades for Each Student: ");
        for (int i = 0; i < data.length; i++) {
            float average = 0;
            System.out.printf(data[i][NAME_INDEX] + ": ");
            for (int j = 1; j < data[i].length; j++) {
                average += Float.parseFloat(data[i][j]);
            }
            System.out.println(average / NUMBER_OF_SUBJECTS);
        }
        System.out.println();
    }
    private static void printAveragesBySubjects(String[][] data) {
        System.out.println("Average grades For Each Subject: ");
        for (int i = 1; i <= NUMBER_OF_SUBJECTS; i++) {
            System.out.printf(SUBJECTS[i-1] + ": ");
            float average = 0;
            for (int j = 0; j < data.length; j++) {
                average += Float.parseFloat(data[j][i]);
            }
            System.out.println(average / data.length);
        }
        System.out.println();
    }

    private static String[][] collectStudentsData(int numberOfStudents) {
        String[][] students = new String[numberOfStudents][NUMBER_OF_SUBJECTS + 1];
        for (int i = 0; i < numberOfStudents; i++) {
            String studentName = "";
            System.out.println("Enter information about student #" + (i + 1));
            while (studentName.isEmpty()) {
                System.out.printf("Student's name: ");
                studentName = scanner.nextLine();
            }
            students[i][NAME_INDEX] = studentName;
            for (int j = 1; j <= NUMBER_OF_SUBJECTS; j++) {
                float grade = DEFAULT_GRADE;
                while(grade < 0 || grade > 100) {
                    System.out.printf(SUBJECTS[j - 1] + " Grade (0-100): ");
                    grade = scanner.nextFloat();
                }
                students[i][j] = String.valueOf(grade);
            }
            System.out.println();
        }
        return students;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: The application have no arguments");
            System.exit(1);
        }
        String[][] studentsData = collectStudentsData(Integer.parseInt(args[0]));
        printAveragesByStudents(studentsData);
        printAveragesBySubjects(studentsData);
    }
}
