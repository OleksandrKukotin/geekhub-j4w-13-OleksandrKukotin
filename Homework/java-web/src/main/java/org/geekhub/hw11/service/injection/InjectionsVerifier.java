package org.geekhub.hw11.service.injection;

import org.geekhub.hw11.annotation.Injectable;
import org.geekhub.hw11.exception.PropertiesNotFoundException;

import java.lang.reflect.Field;
import java.util.Properties;

public class InjectionsVerifier {

    private final Properties properties;
    private final ClassSearchService classSearcher;

    public InjectionsVerifier(Properties properties, ClassSearchService classSearcher) {
        this.properties = properties;
        this.classSearcher = classSearcher;
    }

    public void verify() {
        for (Package aPackage : ClassLoader.getSystemClassLoader().getDefinedPackages()) {
            System.out.printf("%n-- Package %s verifying --%n", aPackage.getName());
            for(Class clazz : classSearcher.findAllClassesUsingClassLoader(aPackage.getName())) {
                verify(clazz);
            }
        }
    }

    public void verify(Class clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Injectable.class)) {
                Injectable annotation = field.getAnnotation(Injectable.class);
                Object propertiesValue = properties.get(annotation.value());
                if (propertiesValue == null) {
                    String errorMessage = String.format(
                        "Property '%s' from the %s class is not found in the application.property file",
                        annotation.value(), clazz.getName());
                    System.out.printf("- %s class isn't ok!%n", clazz.getName());
                    throw new PropertiesNotFoundException(errorMessage, new IllegalArgumentException());
                }
            }
        }
        System.out.printf("- %s class is completely ok!%n", clazz.getName());
    }
}
