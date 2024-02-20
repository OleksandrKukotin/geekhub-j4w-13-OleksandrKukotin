repositories {
    maven {
        url = uri("https://repo.repsy.io/mvn/oleksandr_k/okukotin-private")
        name = "My Private Maven Repository on Repsy"
        credentials {
            username = System.getenv("MAVEN_USERNAME") ?: "placeholder - change when need to run locally"
            password = System.getenv("MAVEN_PASSWORD") ?: "placeholder - change when need to run locally"
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.2")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:3.2.2")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:3.2.2")

    implementation("org.postgresql:postgresql:42.7.1")
    implementation("org.flywaydb:flyway-core:10.7.1")
    runtimeOnly("org.flywaydb:flyway-database-postgresql:10.7.1")

    implementation("com.geekhub.private-repo:awesome-oleksandrs-ciphers:0.0.3")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.2")
}
