import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("java-gradle-plugin")
  kotlin("jvm")
  id("com.github.gmazzo.buildconfig")
  id("com.gradle.plugin-publish")
  id("kotlin-publish")
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation(kotlin("gradle-plugin-api"))
}

buildConfig {
  val pluginProject = project(":mp-expose-plugin")
  packageName(pluginProject.group.toString())
  buildConfigField("String", "KOTLIN_PLUGIN_ID", "\"${pluginProject.group}.${pluginProject.name}\"")
  buildConfigField("String", "KOTLIN_PLUGIN_GROUP", "\"${pluginProject.group}\"")
  buildConfigField("String", "KOTLIN_PLUGIN_NAME", "\"${pluginProject.name}\"")
  buildConfigField("String", "KOTLIN_PLUGIN_VERSION", "\"${pluginProject.version}\"")

  val annotationProject = project(":mp-expose-annotation")
  buildConfigField("String", "ANNOTATION_LIBRARY_GROUP", "\"${annotationProject.group}\"")
  buildConfigField("String", "ANNOTATION_LIBRARY_NAME", "\"${annotationProject.name}\"")
  buildConfigField("String", "ANNOTATION_LIBRARY_VERSION", "\"${annotationProject.version}\"")
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}

gradlePlugin {
  plugins {
    create("mpexpose") {
      id = "co.touchlab.expose"
      displayName = "Kotlin Expose compiler plugin"
      description = "Kotlin compiler plugin to change function visibility depending on multiplatform target."
      implementationClass = "co.touchlab.expose.ExposeGradlePlugin"
    }
  }
}

pluginBundle {
  website = "https://github.com/brady-aiello/mp-expose"
  vcsUrl = "https://github.com/brady-aiello/mp-expose.git"
  tags = listOf("kotlin", "compiler-plugin")
}
