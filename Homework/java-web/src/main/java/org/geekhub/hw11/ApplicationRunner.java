package org.geekhub.hw11;

import org.flywaydb.core.Flyway;
import org.geekhub.hw11.config.ApplicationConfig;
import org.geekhub.hw11.config.DatasourceConfig;
import org.geekhub.hw11.model.LogEntry;
import org.geekhub.hw11.service.EncryptionHistoryService;
import org.geekhub.hw11.service.EncryptionService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class ApplicationRunner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class,
            DatasourceConfig.class);
        context.registerShutdownHook();

        Flyway flyway = context.getBean(Flyway.class);
        flyway.migrate();

        String message = "Lorem ipsum dolor sit amet, I don't sure what's next...";
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("""
                Please, choose an encoding algorithm:
                    1 - Caesar cipher
                    2 - Vigenere cipher
                """);
            boolean isChosenRight = false;
            while (!isChosenRight) {
                System.out.printf("%n> ");
                switch(scanner.nextInt()) {
                    case 1 -> {
                        context.getEnvironment().addActiveProfile("Caesar");
                        isChosenRight = true;
                    }
                    case 2 -> {
                        context.getEnvironment().addActiveProfile("Vigenere");
                        isChosenRight = true;
                    }
                    default -> System.out.printf("Try again, please, 1 (Caesar cypher) or 2 (Vigenere cipher)%n> ");
                }
            }
        }
        EncryptionHistoryService logger = context.getBean(EncryptionHistoryService.class);
        EncryptionService encryptionService = context.getBean(EncryptionService.class);
        String encrypted = encryptionService.encrypt(message);
        logger.saveLogEntry(new LogEntry(Instant.now(), message, encryptionService.getUsedCodec(), encrypted, 2));
        logger.getAllLogsPaginated(1, 10);
        System.out.println();
        logger.getAllLogsPaginatedByUserId(1, 1, 10);
        System.out.println();
        logger.getLogsByDate(Instant.now().minus(1, ChronoUnit.DAYS));
        System.out.println();
        logger.getLogsByEncryptor("cipher holder");
    }
}
