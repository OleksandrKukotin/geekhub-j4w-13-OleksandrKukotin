package org.geekhub.hw11;

import org.geekhub.hw11.exception.FileException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ApplicationRunner {

    public static void main(String[] args) {
        Path logFilePath = Path.of("logFile.txt");
        if (!Files.exists(logFilePath)) {
            try {
                Files.createFile(logFilePath);
            } catch (IOException e) {
                throw new FileException(e.getMessage(), e);
            }
        }
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        context.registerShutdownHook();
    }
}
