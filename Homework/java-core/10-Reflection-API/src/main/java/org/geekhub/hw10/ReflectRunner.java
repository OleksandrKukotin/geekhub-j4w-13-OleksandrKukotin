package org.geekhub.hw10;

import org.geekhub.hw10.annotation.Test;

import java.lang.reflect.Method;

public class ReflectRunner {

    public static void main(String[] args) {
        ReflectTestBox testBox = new ReflectTestBox();
        ClassSearchService classSearcher = new ClassSearchService();

        for (Package aPackage : ClassLoader.getSystemClassLoader().getDefinedPackages()) {
            for (Class<?> clazz : classSearcher.findAllClassesUsingClassLoader(aPackage.getName())) {
                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(Test.class)) {
                        testBox.run(clazz);
                        break;
                    }
                }
            }
        }
    }
}
