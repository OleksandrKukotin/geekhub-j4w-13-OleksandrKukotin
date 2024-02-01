dependencies {
    implementation ("org.springframework:spring-context:6.1.3")

    implementation ("org.springframework:spring-jdbc:6.1.3")
    implementation ("org.postgresql:postgresql:42.7.1")
    implementation ("org.flywaydb:flyway-core:10.7.1")

    testImplementation (platform("org.junit:junit-bom:5.9.1"))
    testImplementation (platform("org.mockito:mockito-bom:5.7.0"))

    testImplementation ("org.junit.jupiter:junit-jupiter")
    testImplementation ("org.mockito:mockito-core")
    testImplementation ("org.mockito:mockito-junit-jupiter")
    testImplementation ("org.assertj:assertj-core:3.24.2")
}
