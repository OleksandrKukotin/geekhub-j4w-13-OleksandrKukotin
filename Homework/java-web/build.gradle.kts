dependencies {
    implementation ("org.springframework:spring-context:6.1.3")

    implementation ("org.springframework:spring-jdbc:6.1.3")
    implementation ("org.postgresql:postgresql:42.7.1")
    implementation ("org.flywaydb:flyway-core:10.7.1")
    runtimeOnly ("org.flywaydb:flyway-database-postgresql:10.7.1")

    implementation ("com.walterjwhite.java.dependencies:hikari-jdbc-connection-pool:0.0.17")

    implementation ("org.slf4j:slf4j-api:2.0.11")
    implementation ("org.slf4j:slf4j-simple:2.0.11")

    testImplementation ("org.junit.jupiter:junit-jupiter")
    testImplementation ("org.mockito:mockito-core")
    testImplementation ("org.mockito:mockito-junit-jupiter")
    testImplementation ("org.assertj:assertj-core:3.24.2")
    testImplementation (platform("org.mockito:mockito-bom:5.7.0"))
    testImplementation (platform("org.junit:junit-bom:5.9.1"))
}
