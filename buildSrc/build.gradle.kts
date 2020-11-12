plugins {
    `kotlin-dsl`
    kotlin("jvm") version "1.3.72"
    id("net.minecrell.licenser") version "0.4.1" apply false
}

repositories {
    jcenter()
    mavenCentral()
    gradlePluginPortal()
    mavenLocal()
    maven(url = "https://maven.fabricmc.net") {
        this.name = "Fabric"
    }
}

dependencies {
    // FIXME: Merge PR to fix source sets upstream
    implementation("me.i509:fabric-loom:0.5-SNAPSHOT")
    implementation("gradle.plugin.net.minecrell:licenser:0.4.1")
}
