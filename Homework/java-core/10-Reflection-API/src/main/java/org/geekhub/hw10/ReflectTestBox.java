package org.geekhub.hw10;

import org.geekhub.hw10.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTestBox {

    static int totalTests = 0;
    static int testPassed = 0;
    static int testFailed = 0;

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        System.out.println("""
            ========================================
                ReflectTestBox Testing framework
            ========================================
            """);
        runTests(SampleTest.class);
    }

    private static void runTests(Class<?> clazz) throws InstantiationException,
        IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        System.out.printf("%n-- Running tests in %s class --", clazz.getName());
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                totalTests++;
                System.out.printf("%n- Running Test: %s -", method.getName());
                try {
                    method.invoke(clazz.getDeclaredConstructor().newInstance());
                    testPassed++;
                } catch (AssertionError e) {
                    testFailed++;
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
