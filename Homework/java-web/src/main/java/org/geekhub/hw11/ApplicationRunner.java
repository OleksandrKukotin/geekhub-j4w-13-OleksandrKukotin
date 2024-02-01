package org.geekhub.hw11;

import org.flywaydb.core.Flyway;
import org.geekhub.hw11.repository.DataSourceProvider;
import org.geekhub.hw11.service.encryption.EncryptionService;
import org.geekhub.hw11.service.logging.LoggingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        context.registerShutdownHook();
        DataSourceProvider dataSourceProvider = context.getBean(DataSourceProvider.class);
        Flyway flyway = Flyway.configure()
            .dataSource(dataSourceProvider.create())
            .locations("postgres/migrations")
            .load();
        flyway.migrate();
        String messageToEncrypt = "Incredible message for testing my app";

        LoggingService logger = context.getBean(LoggingService.class);
        logger.showMessagesLog();
        EncryptionService encryptionService = context.getBean(EncryptionService.class);
        encryptionService.encrypt(messageToEncrypt);
        logger.save();
    }
}
