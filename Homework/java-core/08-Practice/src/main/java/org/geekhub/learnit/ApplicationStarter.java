package org.geekhub.learnit;

import java.util.ArrayList;
import java.util.Scanner;

public class ApplicationStarter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService studentService = new StudentService(new ArrayList<>(), scanner);
        ExecutorService executor = new ExecutorService(studentService, scanner, true);
        executor.start();
        scanner.close();
    }
}
