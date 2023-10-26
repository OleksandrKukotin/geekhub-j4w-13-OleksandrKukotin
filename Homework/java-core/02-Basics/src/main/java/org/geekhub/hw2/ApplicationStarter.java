package org.geekhub.hw2;

import java.util.Scanner;

public class ApplicationStarter {

    private static final String[] SUBJECTS = {"Memology", "Cybersport", "History"};
    public static final int DEFAULT_GRADE = -1;
    public static final int NAME_INDEX = 0;
    private static final Scanner scanner = new Scanner(System.in);

    private static void printAverages() {
        // To implement after the collectStudentsData() method
    }

    private static String[][] collectStudentsData(int numberOfStudents) {
        String[][] students = new String[numberOfStudents][SUBJECTS.length];
        for (int i = 1; i <= numberOfStudents; i++) {
            System.out.println("Enter information about student #" + i);
            String studentName = "";
            while (studentName.isEmpty()) {
                System.out.println("Student's name: ");
                studentName = scanner.next();
            }
            students[i][NAME_INDEX] = studentName;
            for (int j = 1; j <= SUBJECTS.length; j++) {
                float grade = DEFAULT_GRADE;
                while(grade < 0 || grade > 100) {
                    System.out.println(SUBJECTS[j-1] + " Grade (0-100): ");
                    grade = scanner.nextFloat();
                }
                students[i][j] = String.valueOf(grade);
            }
        }
        return students;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: The application have no arguments");
            System.exit(1);
        }
        System.out.println(collectStudentsData(Integer.parseInt(args[0])));
    }
}
