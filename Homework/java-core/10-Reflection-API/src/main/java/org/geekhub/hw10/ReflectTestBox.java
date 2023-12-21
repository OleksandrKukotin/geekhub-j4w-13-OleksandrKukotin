package org.geekhub.hw10;

import org.geekhub.hw10.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTestBox {

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
        System.out.printf("Running tests in %s class", clazz.getName());
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                System.out.println("Running Test: " + method.getName());
                method.invoke(clazz.getDeclaredConstructor().newInstance());
            }
        }
    }
}
