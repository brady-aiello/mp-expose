pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenLocal()
  }
}

rootProject.name = "mp-expose"

include(":mp-expose-annotation")
include(":mp-expose-gradle")
include(":mp-expose-plugin")
include(":mp-expose-plugin-native")
include(":mp-expose-integration")

