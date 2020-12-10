/*
 * MIT License
 *
 * Copyright (c) 2020 i509VCB
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

// Buildscript start
import com.matthewprenger.cursegradle.CurseProject
import com.matthewprenger.cursegradle.Options
import com.modrinth.minotaur.TaskModrinthUpload
import net.fabricmc.loom.util.Constants.Configurations
import java.net.URL

buildscript {
    dependencies {
        classpath("org.kohsuke:github-api:1.114")
    }
}

plugins {
    java
    `java-library`
    `kotlin-dsl`
    checkstyle
    id("com.diffplug.spotless") version "5.8.2"
    id("fabric-loom") version "0.5-SNAPSHOT"

    // Publishing
    `maven-publish`
    id("com.matthewprenger.cursegradle") version "1.4.0"
    id("com.modrinth.minotaur") version "1.1.0"
}

val distribution: String by project
val archivesBaseName: String by project
base.archivesBaseName = archivesBaseName

val mavenGroup: String by project
group = mavenGroup

val baseVersion: String by project
val minecraftVersion: String by project
version = "$baseVersion+$minecraftVersion"

val yarnBuild: String by project
val loaderVersion: String by project
val fabricApiVersion: String by project

val ccaVersion: String by project
val configurateVersion: String by project
val confabricateVersion: String by project

// Integrations
val shulkerBoxTooltipVersion: String by project
val quickShulkerVersion: String by project
val modMenuVersion: String by project
val reiVersion: String by project

logger.lifecycle("Building Bulky Shulkies $version")

repositories {
    jcenter()
    mavenCentral()

    maven("https://maven.misterpemodder.com/libs-release") {
        name = "Misterpemodder"
    }

    maven("https://dl.bintray.com/ladysnake/libs") {
        name = "Ladysnake"
    }

    maven("https://dl.bintray.com/kyrptonaught/Quickshulker/") {
        name = "Quickshulker"
    }

    maven("https://dl.bintray.com/kyrptonaught/kyrptconfig/") {
        name = "Kryptconfig"
    }
}

// Configurations
val implementationAndInclude by configurations.register("implementationAndInclude")
val modApiAndInclude by configurations.register("modApiAndInclude")

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$minecraftVersion+build.$yarnBuild:v2")
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")

    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricApiVersion")

    // Confabricate needs updating
    //confabricate(configurateVersion, confabricateVersion, true)
    implementationAndInclude("org.spongepowered:configurate-core:$configurateVersion") {
        exclude(module = "checker-qual")
    }
    implementationAndInclude("org.spongepowered:configurate-hocon:$configurateVersion") {
        exclude(module = "checker-qual")
    }

    // Base, entity, block
    modApiAndInclude("io.github.onyxstudios.Cardinal-Components-API:cardinal-components-base:$ccaVersion")
    modApiAndInclude("io.github.onyxstudios.Cardinal-Components-API:cardinal-components-block:$ccaVersion")
    modApiAndInclude("io.github.onyxstudios.Cardinal-Components-API:cardinal-components-entity:$ccaVersion")
    modApiAndInclude("io.github.onyxstudios.Cardinal-Components-API:cardinal-components-item:$ccaVersion")

    // Integrations
    modIntegration("net.kyrptonaught:quickshulker:$quickShulkerVersion", true)
    modIntegration("com.misterpemodder:shulkerboxtooltip:$shulkerBoxTooltipVersion", true)
    modIntegration("io.github.prospector:modmenu:$modMenuVersion", false)
    modIntegration("me.shedaniel:RoughlyEnoughItems:$reiVersion", false)

    // Apply custom configurations
    add(sourceSets.main.get().getTaskName(null, JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME), implementationAndInclude)
    add(Configurations.INCLUDE, implementationAndInclude)

    add(sourceSets.main.get().getTaskName("mod", JavaPlugin.API_CONFIGURATION_NAME), modApiAndInclude)
    add(Configurations.INCLUDE, modApiAndInclude)
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

spotless {
    java {
        // Only update license headers when changes have occurred
        ratchetFrom("origin/$distribution")
        licenseHeaderFile(project.file("codeformat/HEADER")).yearSeparator(", ")
    }

    // Spotless tries to be smart by ignoring package-info files, however license headers are allowed there
    format("package-info.java") {
        target("**/package-info.java")

        // Only update license headers when changes have occurred
        ratchetFrom("origin/$distribution")
        // Regex is `/**` or `package`
        licenseHeaderFile(project.file("codeformat/HEADER"), "/\\*\\*|package").yearSeparator(", ")
    }

    format("buildscript") {
        target("**/**.gradle.kts")

        // Only update license headers when changes have occurred
        ratchetFrom("origin/$distribution")
        licenseHeaderFile(project.file("codeformat/HEADER"), "// Buildscript start").yearSeparator(", ")
    }

    format("gradle.properties") {
        target("**/gradle.properties")

        // Only update license headers when changes have occurred
        ratchetFrom("origin/$distribution")
        licenseHeaderFile(project.file("codeformat/PROPERTIES_HEADER"), "# Buildscript start").yearSeparator(", ")
    }
}

checkstyle {
    configFile = rootProject.file("checkstyle.xml")
    toolVersion = "8.25"
}

java {
    withSourcesJar()
}

tasks.processResources {
    inputs.properties("version" to project.version)

    filesMatching("fabric.mod.json") {
        expand("version" to version)
    }
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${project.base.archivesBaseName}" }
    }
}

tasks.compileJava {
    options.encoding = "UTF-8"

    // Build for JDK 8
    if (JavaVersion.current().isJava9Compatible) {
        options.release.set(8)
    }
}

fun DependencyHandler.modIntegration(dependencyNotation: Any, enabled: Boolean = true): Dependency? = when(enabled) {
    true -> modImplementation(dependencyNotation)
    false -> modCompileOnly(dependencyNotation)
}?.also {
    (it as ExternalModuleDependency).exclude(group = "net.fabricmc.fabric-api")
}

// Publishing

val checkVersion by tasks.register<DefaultTask>("checkVersion") {
    doFirst {
        val mavenUrl = URL("") // TODO: Test this task
        @Suppress("UNCHECKED_CAST")
        val metadata = groovy.xml.XmlSlurper().parseText(mavenUrl.readText()) as Map<String, Map<String, *>>
        val versions = metadata["versioning"]?.get("versions") as Collection<*>

        if (versions.contains(version)) {
            throw GradleException("$version has already been published")
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifact(tasks.remapJar) {
                builtBy(tasks.remapJar)
            }

            artifact(tasks.remapSourcesJar) {
                builtBy(tasks.remapSourcesJar)
            }
        }
    }

    repositories {
        // TODO
    }
}

curseforge {
//    project(closureOf<CurseProject> {
//        // TODO:
//    })

    options(closureOf<Options> {
        forgeGradleIntegration = false
    })
}

val publishToModrinth = tasks.register<TaskModrinthUpload>("publishToModrinth") {
    mustRunAfter(checkVersion)

    group = "publishing"

    projectId = "bulkyshulkies"
    versionNumber = version as String
    uploadFile = tasks.remapJar
    releaseType = "alpha" // TODO
    addGameVersion(minecraftVersion)
    addLoader("Fabric")
}

val publishToGithubReleases = tasks.register<DefaultTask>("publishToGithubReleases") {
    mustRunAfter(checkVersion)

    onlyIf {
        System.getenv().containsKey("GITHUB_TOKEN")
    }

    doLast {
        // TODO
    }
}

tasks.curseforge { mustRunAfter(checkVersion) }
tasks.publish { mustRunAfter(checkVersion) }
