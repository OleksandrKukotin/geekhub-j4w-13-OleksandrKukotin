plugins {
    java
    `maven-publish`
}

group = "com.geekhub.private-repo"
version = "0.0.2"

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
                username = System.getenv("MAVEN_USERNAME")
                        ?: providers.gradleProperty("username").get()
                password = System.getenv("MAVEN_PASSWORD")
                        ?: providers.gradleProperty("password").get()
            }
        }
    }
}
