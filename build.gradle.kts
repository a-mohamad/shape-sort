plugins {
    java
    `java-library`
    application
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(16))
    }
}

tasks.compileJava {
    options.release.set(16)
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation("com.formdev:flatlaf:1.6")
}

sourceSets {
    main {
        java.srcDir("src/main/java")
    }

    test {
        java.srcDir("src/test/java")
    }
}

application {
    mainClass.set("shapesort.Main")
}

tasks.register<Jar>("uberJar") {
    archiveClassifier.set("uber")

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}
