repositories {
    maven {
        url = uri("https://repo.repsy.io/mvn/oleksandr_k/okukotin-private")
        name = "My Private Maven Repository on Repsy"
        credentials {
            username = env.MAVEN_USERNAME.value
            password = env.MAVEN_PASSWORD.value
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.2")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:3.2.2")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:3.2.2")

    implementation("io.swagger.core.v3:swagger-core:2.2.20")
    implementation("javax.xml.bind:jaxb-api:2.3.1")

    implementation("org.postgresql:postgresql:42.7.2")
    implementation("org.flywaydb:flyway-core:10.7.1")
    runtimeOnly("org.flywaydb:flyway-database-postgresql:10.7.1")

    implementation("com.geekhub.private-repo:awesome-oleksandrs-ciphers:0.1.5")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.2")
}
