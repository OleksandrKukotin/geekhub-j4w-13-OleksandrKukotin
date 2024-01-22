package org.geekhub.hw11.service.injection;

import org.geekhub.hw11.annotation.Injectable;
import org.geekhub.hw11.exception.FileException;
import org.geekhub.hw11.exception.InjectionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

public class InjectableService {

    public void injectTo(Object object, Properties properties) {
        Class<?> clazz = object.getClass();
        try {
            for (Field field : clazz.getDeclaredFields()) {
                Injectable annotation = field.getAnnotation(Injectable.class);
                if (annotation != null) {
                    field.setAccessible(true);
                    Object annotationValue = properties.get(annotation.value());
                    switch (field.getType().getName()) {
                        case "String" -> field.set(object, annotationValue);
                        case "int" -> field.set(object, Integer.parseInt(annotationValue.toString()));
                        default -> field.set(object, annotationValue);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new InjectionException(e.getMessage(), e);
        }
    }

    public Properties loadAvailableProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = InjectableService.class.getResourceAsStream("/application.properties")) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8))) {
                properties.load(reader);
            }
        } catch (IOException e) {
            throw new FileException(e.getMessage(), e);
        }

        return properties;
    }
}
