import name.remal.gradle_plugins.sonarlint.SonarLint

plugins {
    id ("name.remal.sonarlint") version "3.3.11"
    java
    idea
    checkstyle
    jacoco
}

tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
    gradleVersion = "latest"
}

allprojects {

    apply {
        plugin("name.remal.sonarlint")
        plugin("java")
        plugin("idea")
        plugin("checkstyle")
        plugin("jacoco")
    }
    group = "org.geekhub"

    java.sourceCompatibility = JavaVersion.VERSION_17
    java.targetCompatibility = JavaVersion.VERSION_17

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation ("com.google.code.findbugs:jsr305:3.0.2")
    }

    idea {
        module {
            sourceDirs.plusAssign(file("src/main/java"))
        }
    }

    sonarLint {
        languages {
            include("java")
        }
        rules {
            disable(
                    "java:S1192", // Allow string literals to be duplicated
                    "java:S1197", // Allow constants to be defined in interfaces
                    "java:S1118", // Allow utility classes to have a private constructor
                    "java:S106", // Allow system out and err to be used
                    "java:S107", // Allow constructors with more than 7 parameters
                    "java:S3776", // Allow methods with more than 15 lines
                    "java:S1135", // Allow TODO comments
                    "java:S2094", // Allow empty classes for homeworks
                    "java:S1452", // Allow generic wildcards types in return type
                    "java:S125", // Allow commented sections of code
                    "java:6218", // Allow not overridden Equals() in records
                    "java:S3011" // Allow Reflection using for setting field's values
            )
        }
    }

    tasks {
        val checkstyleMain by getting(Checkstyle::class) {
            // configure Checkstyle task if needed
        }

        val sonarlintMain by getting(SonarLint::class) {
            // configure Checkstyle task if needed
        }

        "check" {
            dependsOn(checkstyleMain)
            dependsOn(sonarlintMain)
        }
    }

    tasks {
        val jacocoTestCoverageVerification by getting(JacocoCoverageVerification::class) {
            dependsOn(jacocoTestReport)
            violationRules {
                rule {
                    limit {
                        minimum = BigDecimal(0.85)
                    }
                }
            }
        }

        "check" {
            dependsOn(jacocoTestCoverageVerification)
        }
    }

    tasks.test {
        useJUnitPlatform()
    }
}
