plugins {
  kotlin("multiplatform")
  id("co.touchlab.expose") version "0.1.0-SNAPSHOT"
}

repositories {
  mavenCentral()
  mavenLocal()
}

kotlin {
  jvm {
    compilations.all {
      kotlinOptions {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.useIR = true
      }
    }
  }
/*  js(IR) {
    browser()
    nodejs()
  }
*/

  val osName = System.getProperty("os.name")
  when {
    "Windows" in osName -> mingwX64("native")
    "Mac OS" in osName -> macosX64("native")
    else -> linuxX64("native")
  }

  sourceSets {
    val commonTest by getting {
      dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
      }
    }
    val jvmTest by getting {
      dependencies {
        implementation(kotlin("test-junit"))
      }
    }
    /*val jsTest by getting {
      dependencies {
        implementation(kotlin("test-js"))
      }
    }*/
    val nativeTest by getting {
      dependsOn(commonTest)
    }
  }
}

mpexpose {
  enabled = true
}
