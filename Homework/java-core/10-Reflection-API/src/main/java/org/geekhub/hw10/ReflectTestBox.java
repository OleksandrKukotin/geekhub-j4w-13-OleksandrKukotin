package org.geekhub.hw10;

import org.geekhub.hw10.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTestBox {

    private static int totalTests = 0;
    private static int testPassed = 0;
    private static int testFailed = 0;

    public static void main(String[] args) {
        System.out.println("""
            ========================================
                ReflectTestBox Testing framework
            ========================================
            """);
        runTests(SampleTest.class);
    }

    private static void runTests(Class<?> clazz) {
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
                } catch (Exception e) {
                    testFailed++;
                    System.out.printf("%nError: %s", e);
                }
            }
        }
        System.out.println("""

            ========================================
                             Summary
            ========================================
            """);
        System.out.println("Total tests run: " + totalTests);
        System.out.println("Passed tests: " + testPassed);
        System.out.println("Failed tests: " + testFailed);
    }
}
