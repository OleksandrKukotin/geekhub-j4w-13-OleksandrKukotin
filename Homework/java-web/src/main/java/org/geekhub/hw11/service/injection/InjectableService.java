package org.geekhub.hw11.service.injection;

import org.geekhub.hw11.annotation.Injectable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class InjectableService {

    private final ClassSearchService classSearcher;

    public InjectableService(ClassSearchService classSearcher) {
        this.classSearcher = classSearcher;
    }

    public void injectTo(Object object) {
        Class clazz = object.getClass();
        try (InputStream inputStream = InjectableService.class.getResourceAsStream("/application.properties")) {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                Properties properties = new Properties();
                properties.load(reader);
                for (Field field : clazz.getDeclaredFields()) {
                    Injectable annotation = field.getAnnotation(Injectable.class);
                    if (annotation != null) {
                        field.setAccessible(true);
                        if (field.getType().isInstance(String.class)) {
                            field.set(object, properties.get(annotation.value()));
                        } else if (field.getType().isInstance(int.class)) {
                            field.set(object, properties.get(Integer.parseInt(properties.getProperty(annotation.value()))));
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
