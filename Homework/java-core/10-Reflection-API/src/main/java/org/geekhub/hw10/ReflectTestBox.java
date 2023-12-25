package org.geekhub.hw10;

import org.geekhub.hw10.annotation.AfterMethod;
import org.geekhub.hw10.annotation.BeforeMethod;
import org.geekhub.hw10.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTestBox {

    private int totalTests = 0;
    private int testPassed = 0;
    private int testFailed = 0;

    public void run(Class<?> clazz) {
        System.out.println("""
            =========================================================
                        ReflectTestBox Testing framework
            =========================================================
            """);
        runBeforeMethods(clazz);
        runTests(clazz);
        runAfterMethods(clazz);
    }

    private void runBeforeMethods(Class<?> clazz) {
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeMethod.class)) {
                try {
                    method.invoke(clazz.getDeclaredConstructor().newInstance());
                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                         IllegalAccessException e) {
                    System.out.printf("An error has occurred during the run of before-test methods: %s", e.getMessage());
                }
            }
        }
    }

    private void runTests(Class<?> clazz) {
        System.out.printf("%n-- Running tests in %s class --", clazz.getName());
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                totalTests++;
                System.out.printf("%n%n- Running Test: %s -", method.getName());
                try {
                    method.invoke(clazz.getDeclaredConstructor().newInstance());
                    testPassed++;
                    System.out.printf("%nPassed! :)");
                } catch (AssertionError e) {
                    testFailed++;
                    System.out.printf("%nFailed. :[");
                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                         IllegalAccessException e) {
                    testFailed++;
                    System.out.printf("%nError has occurred: %n%s", e);
                }
            }
        }
        System.out.println("""

            =========================================================
                                      Summary
            =========================================================
            """);
        System.out.println("Total tests run: " + totalTests);
        System.out.println("Passed tests: " + testPassed);
        System.out.println("Failed tests: " + testFailed);
        System.out.println("=========================================================");
    }

    private void runAfterMethods(Class<?> clazz) {
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(AfterMethod.class)) {
                try {
                    method.invoke(clazz.getDeclaredConstructor().newInstance());
                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                         IllegalAccessException e) {
                    System.out.printf("An error has occurred during the run of after-test methods: %s", e.getMessage());
                }
            }
        }
    }
}
