package org.geekhub.hw11;

import org.geekhub.hw11.exception.FileException;
import org.geekhub.hw11.service.encryption.EncryptionService;
import org.geekhub.hw11.service.logging.LoggingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ApplicationRunner {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class)) {
            Path pathToLog = Paths.get("logs.csv");
            if (Files.notExists(pathToLog)) {
                Files.createFile(pathToLog);
            }
            String messageToEncrypt = "Incredible message for testing my app";

            context.registerShutdownHook();
            LoggingService logger = context.getBean(LoggingService.class);
            logger.showMessagesLog();
            EncryptionService encryptionService = context.getBean(EncryptionService.class);
            encryptionService.encrypt(messageToEncrypt);
            logger.save();
        } catch (IOException e) {
            throw new FileException(e.getMessage(), e);
        }
    }
}
