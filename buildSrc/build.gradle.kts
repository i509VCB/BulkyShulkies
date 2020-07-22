plugins {
    `kotlin-dsl`
    kotlin("jvm") version "1.3.72"
    id("net.minecrell.licenser") version "0.4.1" apply false
}

repositories {
    jcenter()
    mavenCentral()
    gradlePluginPortal()
    maven(url = "https://maven.fabricmc.net") {
        this.name = "Fabric"
    }
}

dependencies {
    implementation("net.fabricmc:fabric-loom:0.4-SNAPSHOT")
    implementation("gradle.plugin.net.minecrell:licenser:0.4.1")
}
