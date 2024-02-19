dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.2")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:3.2.2")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:3.2.2")

    implementation("com.walterjwhite.java.dependencies:hikari-jdbc-connection-pool:0.0.17")
    implementation("org.postgresql:postgresql:42.7.1")
    implementation("org.flywaydb:flyway-core:10.7.1")
    runtimeOnly("org.flywaydb:flyway-database-postgresql:10.7.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.2")
}
