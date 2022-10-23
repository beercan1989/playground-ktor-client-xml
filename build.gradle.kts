import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"
}

repositories {
    mavenCentral()
    mavenLocal()
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {

    // Logging
    implementation("ch.qos.logback:logback-classic:1.4.4")

    // TODO - JAXB??

    // Client: Engine
    implementation("io.ktor:ktor-client-core:2.1.2")
    implementation("io.ktor:ktor-client-cio:2.1.2")

    // Client: Serialization
    implementation("io.ktor:ktor-client-content-negotiation:2.1.2")
    implementation("io.ktor:ktor-serialization-kotlinx-xml:2.1.2")

    // Client: Logging HTTP calls
    testImplementation("io.ktor:ktor-client-logging:2.1.2")

    // Test definitions and running
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1")

    // Assertions
    testImplementation("io.kotest:kotest-assertions-core:5.5.1")

    // Client: Mocking
    testImplementation("io.ktor:ktor-client-mock:2.1.2")

    // Mocking
    testImplementation("io.mockk:mockk:1.13.2")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        showExceptions = true
        showStandardStreams = true
        events = setOf(TestLogEvent.FAILED, TestLogEvent.SKIPPED, TestLogEvent.PASSED)
        exceptionFormat = TestExceptionFormat.FULL
    }
}

tasks.withType<Wrapper>().configureEach {
    distributionType = Wrapper.DistributionType.ALL
}
