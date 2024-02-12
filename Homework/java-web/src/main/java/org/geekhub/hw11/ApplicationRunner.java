package org.geekhub.hw11;

import org.flywaydb.core.Flyway;
import org.geekhub.hw11.config.ApplicationConfig;
import org.geekhub.hw11.config.DatasourceConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class,
            DatasourceConfig.class);
        context.registerShutdownHook();
        context.getBean(Flyway.class);


    }
}
