plugins {
    id("org.springframework.boot") version "3.2.4"
}

dependencies {
    implementation(project(":Coursework:rest-api"))
    implementation(project(":Coursework:domain"))
    implementation(project(":Coursework:persistence"))
}
