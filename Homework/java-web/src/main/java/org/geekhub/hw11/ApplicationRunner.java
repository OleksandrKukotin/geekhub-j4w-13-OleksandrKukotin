package org.geekhub.hw11;

import org.flywaydb.core.Flyway;
import org.geekhub.hw11.model.LogEntry;
import org.geekhub.hw11.repository.PostgresqlDatasourceProvider;
import org.geekhub.hw11.service.encryption.EncryptionService;
import org.geekhub.hw11.service.logging.EncryptionsHistoryService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Instant;

public class ApplicationRunner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        context.registerShutdownHook();

        PostgresqlDatasourceProvider postgresqlDatasourceProvider = context.getBean(PostgresqlDatasourceProvider.class);
        Flyway flyway = Flyway.configure()
            .dataSource(postgresqlDatasourceProvider.create())
            .locations("postgres/migrations")
            .load();
        flyway.migrate();

        String message = "Lorem ipsum dolor sit amet, I don't sure what's next...";
        EncryptionsHistoryService logger = context.getBean(EncryptionsHistoryService.class);
        EncryptionService encryptionService = context.getBean(EncryptionService.class);
        String encrypted = encryptionService.encrypt(message);
        logger.saveLogEntry(new LogEntry(Instant.now(), message, encryptionService.getUsedCodec(), encrypted, 2));
        logger.getAllLogsPaginated(1, 10);
    }
}
