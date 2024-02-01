package org.geekhub.hw11;

import org.flywaydb.core.Flyway;
import org.geekhub.hw11.repository.DatasourceProvider;
import org.geekhub.hw11.service.encryption.EncryptionService;
import org.geekhub.hw11.service.logging.LoggingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
    }
}
