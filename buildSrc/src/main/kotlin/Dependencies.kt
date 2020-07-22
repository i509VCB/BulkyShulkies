import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.*
import java.net.URI

fun RepositoryHandler.fabric() = maven {
    this.name = "Fabric"
    this.url = URI("https://maven.fabricmc.net/")
}

fun RepositoryHandler.sponge() = maven {
    this.name = "Sponge"
    this.url = URI("https://repo.spongepowered.org/maven/")
}

fun RepositoryHandler.pex() = maven {
    this.name = "PEX"
    this.url = URI("https://repo.glaremasters.me/repository/permissionsex")
}

fun RepositoryHandler.misterpe() = maven {
    this.name = "misterpe"
    this.url = URI("https://maven.misterpemodder.com/libs-release/")
}

fun DependencyHandler.minecraft(version: String): Dependency? = this.add(
    "minecraft",
    "com.mojang:minecraft:$version"
)

fun DependencyHandler.yarn(minecraftVersion: String, yarnBuild: String): Dependency? = this.add(
    "mappings",
    "net.fabricmc:yarn:$minecraftVersion+build.$yarnBuild:v2"
)

fun DependencyHandler.`fabric-loader`(version: String): Dependency? = modImplementation(
    "net.fabricmc:fabric-loader:$version"
)

fun DependencyHandler.`fabric-api`(version: String): Dependency? = modImplementation(
    "net.fabricmc.fabric-api:fabric-api:$version"
)

fun DependencyHandler.modImplementation(dependencyNotation: Any): Dependency? = this.add(
    "modImplementation",
    dependencyNotation
)

fun DependencyHandler.modCompileOnly(dependencyNotation: Any): Dependency? = this.add(
    "modCompileOnly",
    dependencyNotation
)

fun DependencyHandler.testmodImplementation(dependencyNotation: Any): Dependency? = this.add(
    "testmodImplementation",
    dependencyNotation
)

fun DependencyHandler.include(dependencyNotation: Any): Dependency? = this.add(
    "include",
    dependencyNotation
)

fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? = this.add(
    "implementation",
    dependencyNotation
)

fun DependencyHandler.optionalMod(dependencyNotation: Any, enabled: Boolean = true): Dependency? = when(enabled) {
    true -> modImplementation(dependencyNotation)
    false -> modCompileOnly(dependencyNotation)
}?.also {
    (it as ExternalModuleDependency).exclude(group = "net.fabricmc.fabric-api")
}

fun Project.testmod() {
    with(project) {
        apply(plugin = "java")
        apply(plugin = "fabric-loom")

        val sourceSets = this.extensions.getByType(SourceSetContainer::class)
        val main = sourceSets.getByName("main")
        sourceSets.register("testmod") {
            this.compileClasspath += main.compileClasspath
            this.runtimeClasspath += main.runtimeClasspath
        }

        dependencies {
            afterEvaluate {
                testmodImplementation(main.output)
            }
        }
    }
}

fun DependencyHandler.confabricate(configurateVersion: String, confabricateVersion: String, snapshot: Boolean = false): Dependency? = modImplementation(
    "ca.stellardrift:confabricate:$confabricateVersion${if (snapshot) "-SNAPSHOT+" else "+"}$configurateVersion"
)?.also {
    (it as ExternalModuleDependency).exclude(group = "net.fabricmc.fabric-api")
    include(it)
    configurate(ConfigurateType.HOCON, configurateVersion, snapshot)
}

fun DependencyHandler.configurate(type: ConfigurateType, configurateVersion: String, snapshot: Boolean = false): Dependency? = implementation(
    "org.spongepowered:configurate-${type.type}:$configurateVersion${if (snapshot) "-SNAPSHOT" else ""}"
)

enum class ConfigurateType(internal val type: String) {
    HOCON("hocon"),
    YAML("yaml"),
    JSON("json"),
    XML("xml")
}
