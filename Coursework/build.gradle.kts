allprojects {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web:3.2.4")
        implementation("org.springframework.boot:spring-boot-starter-jdbc:3.2.4")
        implementation("org.springframework.boot:spring-boot-starter-security:3.2.4")
        implementation("org.springframework.boot:spring-boot-starter-thymeleaf:3.2.4")
        implementation("org.springframework.boot:spring-boot-starter-actuator:3.2.4")
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

        implementation("org.postgresql:postgresql:42.7.3")
        implementation("org.flywaydb:flyway-core:10.7.1")
        runtimeOnly("org.flywaydb:flyway-database-postgresql:10.7.1")

        testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.4")
        testImplementation("org.springframework.security:spring-security-test:6.2.4")
    }
}
