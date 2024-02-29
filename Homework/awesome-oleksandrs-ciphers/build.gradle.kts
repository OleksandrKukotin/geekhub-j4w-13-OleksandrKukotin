dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.mockito:mockito-core:5.10.0")
}
plugins {
    java
    `maven-publish`
}

group = "com.geekhub.private-repo"
version = "0.1.5"

publishing {
    publications {
        create<MavenPublication>("initialMavenPublication") {
            from(components["java"])
            pom {
                name = "Awesome Oleksandr Ciphers"
                description = "Java library that contains some classic ciphers"
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        id = "oleksandr_k"
                        name = "Oleksandr Kukotin"
                        email = "oleksandr.kukotin@gmail.com"
                    }
                }
            }
        }
    }

    repositories {
        maven {
            name = "repsy"
            url = uri("https://repsy.io/mvn/oleksandr_k/okukotin-private")
            credentials {
                username = env.MAVEN_USERNAME.value
                password = env.MAVEN_PASSWORD.value
            }
        }
    }
}
