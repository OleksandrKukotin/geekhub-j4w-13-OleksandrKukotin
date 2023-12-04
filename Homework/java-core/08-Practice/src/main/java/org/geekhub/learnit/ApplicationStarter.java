package org.geekhub.learnit;

import java.util.ArrayList;
import java.util.Scanner;

public class ApplicationStarter {

    public static void main(String[] args) {
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        StudentService studentService = new StudentService(new ArrayList<>(), scanner);

        while (isRunning) {
            System.out.println("Enter option: ");
            System.out.println("To add new student > create");
            System.out.println("To get last created student scores > last");
            System.out.println("To get specific student info > special");
            System.out.println("To get average students scores > average");
            System.out.println("To close the application > exit");
            switch (scanner.nextLine()) {
                case "create" -> studentService.addStudent();
                case "last" -> studentService.getLastCreatedStudentScores();
                case "special" -> studentService.getSpecificStudentInfo();
                case "average" -> studentService.getAverageStudentsScores();
                case "exit" -> isRunning = false;
                default -> System.out.println("Ooops! Something went wrong :(");
            }
        }
        scanner.close();
    }
}
