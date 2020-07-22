import net.fabricmc.loom.task.RemapJarTask
import net.minecrell.gradle.licenser.header.HeaderStyle

plugins {
    java
    checkstyle
    id("fabric-loom")
    id("net.minecrell.licenser")
}

val minecraftVersion: String by project
val yarnBuild: String by project
val loaderVersion: String by project

val fabricApiVersion: String by project

val configurateVersion: String by project
//val confabricateVersion: String by project

// Optional dependencies
val shulkerBoxTooltipVersion: String by project
val quickShulkerVersion: String by project

// Nice to have
val reiVersion: String by project
val modMenuVersion: String by project

val baseVersion: String by project
val modVersion: String = "$baseVersion+$minecraftVersion"

val archivesBaseName: String by project

logger.lifecycle("""
Building Bulky Shulkies $modVersion 
""")

val main by sourceSets

repositories {
    jcenter()
    pex()
    sponge()
    misterpe()

    // For Quickshulker and kyrptconfig
    maven(url = "https://dl.bintray.com/kyrptonaught/Quickshulker/")
    maven(url = "https://dl.bintray.com/kyrptonaught/kyrptconfig/")
}

dependencies {
    minecraft(minecraftVersion)
    yarn(minecraftVersion, yarnBuild)
    `fabric-loader`(loaderVersion)
    `fabric-api`(fabricApiVersion)

    // Confabricate needs updating to 20w29a
    //confabricate(configurateVersion, confabricateVersion, true)
    configurate(ConfigurateType.HOCON, configurateVersion, true)?.let {
        include(it)
    }

    // Optional dependencies
    optionalMod("com.misterpemodder:shulkerboxtooltip:$shulkerBoxTooltipVersion", false)
    optionalMod("net.kyrptonaught:quickshulker:$quickShulkerVersion", true)

    // Nice to have
    optionalMod("io.github.prospector:modmenu:$modMenuVersion", false)
    optionalMod("me.shedaniel:RoughlyEnoughItems:$reiVersion", false)

    // Annotations
    implementation("org.checkerframework:checker-qual:3.0.1")
    //implementation("org.jetbrains:annotations:19.0.0")
}

tasks.withType(ProcessResources::class).configureEach {
    filesMatching("fabric.mod.json") {
        expand("version" to modVersion)
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

license {
    header = file("HEADER.txt")

    // Apply NinjaPhenix's license header to the scrollable stuff
    val exclusions = setOf(
        "**/ScrollableScreenHandler.java",
        "**/ScrollableScreen.java",
        "**/SearchTextFieldWidget.java"
    )

    include("**/*.java")
    exclude(exclusions)
    style("java", HeaderStyle.BLOCK_COMMENT)

    // Apply NinjaPhenix's license header to the scrollable widgets
    matching(exclusions) {
        it.header = file("LICENSE-NINJAPHENIX.txt")
    }
}

checkstyle {
    configFile = rootProject.file("checkstyle.xml")
    toolVersion = "8.25"
}

tasks.withType(JavaCompile::class) {
    options.encoding = "UTF-8"
}

task<Jar>("sourcesJar") {
    classifier = "sources"
    from(main.allSource)
}

// Okay Kotlin DSL is funky here
tasks.withType(Jar::class) {
    from("LICENSE")
    this.version = modVersion
}

tasks.withType(RemapJarTask::class) {
    this.version = modVersion
}

// TODO: Publishing
