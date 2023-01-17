plugins {
    kotlin("multiplatform") version "1.7.21"
    `maven-publish`
}

group = "com.kotakotik"
version = "1.0"

repositories {
    mavenCentral()
}

publishing {
    val ghUsername = System.getenv("GITHUB_ACTOR")
    val ghToken = System.getenv("GITHUB_TOKEN")
    if (ghUsername != null || ghToken != null) {
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/kotakotik22/kotalin")
                credentials {
                    username = ghUsername
                    password = ghToken
                }
            }
        }
        publications {
            create<MavenPublication>("grp") {
                from(components["kotlin"])
            }
        }
    } else {
        println("Not including publishing because we are not running on a github workflow")
    }
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(BOTH) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    
    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("$buildDir/gen/")
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting
        val nativeMain by getting
        val nativeTest by getting
    }
}
