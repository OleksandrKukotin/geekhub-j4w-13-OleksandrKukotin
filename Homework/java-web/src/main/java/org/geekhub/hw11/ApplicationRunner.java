package org.geekhub.hw11;

import org.flywaydb.core.Flyway;
import org.geekhub.hw11.config.ApplicationConfig;
import org.geekhub.hw11.config.DatasourceConfig;
import org.geekhub.hw11.model.LogEntry;
import org.geekhub.hw11.service.EncryptionHistoryService;
import org.geekhub.hw11.service.EncryptionService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Instant;

public class ApplicationRunner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class,
            DatasourceConfig.class);
        context.registerShutdownHook();
        context.getBean(Flyway.class);

        String message = "Lorem ipsum dolor sit amet, I don't sure what's next...";

        EncryptionHistoryService encryptionHistory = context.getBean(EncryptionHistoryService.class);
        EncryptionService encryptionService = context.getBean(EncryptionService.class);
        String encrypted = encryptionService.encrypt(message);
        encryptionHistory.saveLogEntry(new LogEntry(0,
            Instant.now(),
            message,
            encryptionService.getUsedCodec(),
            encrypted,
            1));
        encryptionHistory.getAllLogsPaginated(1, 10);
//        encryptionHistory.getAllLogsPaginatedByUserId(1, 1, 10);
//        encryptionHistory.getLogsByDate(Instant.now().minus(1, ChronoUnit.DAYS));
//        encryptionHistory.getLogsByEncryptor("cipher holder");
    }
}
