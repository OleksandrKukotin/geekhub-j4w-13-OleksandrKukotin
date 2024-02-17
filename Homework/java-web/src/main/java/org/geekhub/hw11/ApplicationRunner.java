package org.geekhub.hw11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {
    SecurityAutoConfiguration.class,
    DataSourceAutoConfiguration.class,
    FlywayAutoConfiguration.class
})
public class ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);
    }
}
