import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "xyz.sk1.service.httpserver"
version = "1.0-SNAPSHOT"

apply {
    plugin("java")
    plugin("com.github.johnrengelman.shadow")
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.slf4j:slf4j-simple:1.7.5")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "xyz.sk1.service.httpserver.SimpleHttpServer",
            "Manifest-Version" to 1.0,
            "Created-By" to "Skylar :)"
        )
    }
}

tasks.withType<ShadowJar> {
    destinationDirectory.set(file("/home/skylar/Desktop/services/httpServer/"))
    archiveBaseName.set("BasicHttpServer")
    archiveVersion.set(project.version.toString())

    manifest {
        "Main-Class" to "xyz.sk1.service.httpserver.SimpleHttpServer"
        "Manifest-Version" to 1.0
        "Created-By" to "Skylar :)"
    }

}

tasks.named("assemble"){
    dependsOn("shadowJar")
}