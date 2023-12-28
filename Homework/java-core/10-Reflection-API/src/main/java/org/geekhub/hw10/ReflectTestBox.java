package org.geekhub.hw10;

import org.geekhub.hw10.annotation.AfterMethod;
import org.geekhub.hw10.annotation.BeforeMethod;
import org.geekhub.hw10.annotation.Test;
import org.geekhub.hw10.exception.SettingTestsException;

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
        try {
            Object classInstance = clazz.getDeclaredConstructor().newInstance();
            runBeforeMethods(clazz, classInstance);
            runTests(clazz, classInstance);
            runAfterMethods(clazz, classInstance);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new SettingTestsException(e.getMessage(), e);
        }
    }

    private void runBeforeMethods(Class<?> clazz, Object classInstance) {
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeMethod.class)) {
                try {
                    method.invoke(classInstance);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.printf("An error has occurred during the run of before-test methods: %s", e.getMessage());
                }
            }
        }
    }

    private void runTests(Class<?> clazz, Object classInstance) {
        System.out.printf("%n-- Running tests in %s class --", clazz.getName());
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                totalTests++;
                System.out.printf("%n%n- Running Test: %s -", method.getName());
                try {
                    method.invoke(classInstance);
                    testPassed++;
                    System.out.printf("%nPassed! :)");
                } catch (AssertionError e) {
                    testFailed++;
                    System.out.printf("%nFailed. :[");
                } catch (InvocationTargetException | IllegalAccessException e) {
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

    private void runAfterMethods(Class<?> clazz, Object classInstance) {
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(AfterMethod.class)) {
                try {
                    method.invoke(classInstance);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    System.out.printf("An error has occurred during the run of after-test methods: %s", e.getMessage());
                }
            }
        }
    }
}
