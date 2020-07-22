import net.minecrell.gradle.licenser.LicenseExtension
import net.minecrell.gradle.licenser.LicenseProperties
import net.minecrell.gradle.licenser.header.HeaderStyle
import org.gradle.api.tasks.util.PatternSet
import org.gradle.kotlin.dsl.closureOf

fun LicenseExtension.matching(includes: Set<String> = setOf(), properties: (LicenseProperties) -> Unit) = this.matching(PatternSet().setIncludes(includes), closureOf(properties))

fun LicenseExtension.style(extension: String, style: HeaderStyle) {
    this.style[extension] = style.format
}
