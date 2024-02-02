package org.geekhub.hw11;

import org.flywaydb.core.Flyway;
import org.geekhub.hw11.model.LogEntry;
import org.geekhub.hw11.repository.DatasourceProvider;
import org.geekhub.hw11.service.encryption.EncryptionService;
import org.geekhub.hw11.service.logging.LoggingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Instant;

public class ApplicationRunner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        context.registerShutdownHook();

        DatasourceProvider datasourceProvider = context.getBean(DatasourceProvider.class);
        Flyway flyway = Flyway.configure()
            .dataSource(datasourceProvider.create())
            .locations("postgres/migrations")
            .load();
        flyway.migrate();

        String message = "Lorem ipsum dolor sit amet, I don't sure what's next...";
        LoggingService logger = context.getBean(LoggingService.class);
        EncryptionService encryptionService = context.getBean(EncryptionService.class);
        String encrypted = encryptionService.encrypt(message);
        logger.saveLogEntry(new LogEntry(Instant.now(), message, "cipher holder", encrypted));
    }
}
