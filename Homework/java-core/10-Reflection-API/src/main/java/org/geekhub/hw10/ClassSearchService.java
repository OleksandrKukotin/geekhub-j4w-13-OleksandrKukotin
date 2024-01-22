package org.geekhub.hw10;

import org.geekhub.hw10.exception.FileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassSearchService {

    public Set<Class<?>> findAllClassesUsingClassLoader(String packageName) {
        BufferedReader reader;
        try (InputStream stream = ClassLoader.getSystemClassLoader()
            .getResourceAsStream(packageName.replaceAll("[.]", "/"))) {
            reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(stream)));
        } catch (IOException e) {
            throw new FileException(e.getMessage(), e);
        }
        return reader.lines()
            .filter(line -> line.endsWith(".class"))
            .map(line -> getClass(line, packageName))
            .collect(Collectors.toSet());
    }

    private Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            throw new FileException(e.getMessage(), e);
        }
    }
}
