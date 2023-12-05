package org.geekhub.learnit;

import java.util.Scanner;

public class ExecutorService {

    private static final String COMMAND_CREATE = "create";
    private static final String COMMAND_GET_LAST = "last";
    private static final String COMMAND_SPECIFIC = "specific";
    private static final String COMMAND_AVERAGE = "average";
    private static final String COMMAND_EXIT = "exit";

    private final StudentService studentService;
    private final Scanner scanner;
    private boolean isRunning;

    public ExecutorService(StudentService studentService, Scanner scanner, boolean status) {
        this.studentService = studentService;
        this.scanner = scanner;
        isRunning = status;
    }

    public void start() {
        while(isRunning) {
            System.out.println("Enter option: ");
            System.out.printf("To add new student > %s%n", COMMAND_CREATE);
            System.out.printf("To get last created student scores > %s%n", COMMAND_GET_LAST);
            System.out.printf("To get specific student info > %s%n", COMMAND_SPECIFIC);
            System.out.printf("To get average students scores > %s%n", COMMAND_AVERAGE);
            System.out.printf("To close the application > %s%n", COMMAND_EXIT);
            switch (scanner.nextLine()) {
                case COMMAND_CREATE -> studentService.addStudent();
                case COMMAND_GET_LAST -> studentService.getLastCreatedStudentScores();
                case COMMAND_SPECIFIC -> studentService.getSpecificStudentInfo();
                case COMMAND_AVERAGE -> studentService.getAverageStudentsScores();
                case COMMAND_EXIT -> stop();
                default -> System.out.println("Ooops! Something went wrong :(");
            }
        }
    }

    private void stop() {
        isRunning = false;
    }
}
