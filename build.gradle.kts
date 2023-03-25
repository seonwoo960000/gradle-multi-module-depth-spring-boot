import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.allopen") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
}

java.sourceCompatibility = JavaVersion.VERSION_17

allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring") //allopen
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        testImplementation("org.springframework.boot:spring-boot-starter-test")

//        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
    }

    dependencyManagement {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()

        testLogging {
            events("passed", "failed", "skipped")
            setExceptionFormat("full")
        }
    }

    tasks.test {
        useJUnitPlatform() {
            includeTags("unitTest")
            excludeTags("integrationTest")
        }
    }

    task<Test>("integration") {
        useJUnitPlatform() {
            excludeTags("unitTest")
            includeTags("integrationTest")
        }
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }
}

project(":module1") {
    dependencies {

    }
}
//project(":api") {
//    dependencies {
//        api(project(":common-lib"))
//        implementation(project(":application"))
//        runtimeOnly(project(":infrastructure"))
//
//        testImplementation(project(":application"))
//        testImplementation(project(":domain"))
//        testImplementation(project(":infrastructure"))
//    }
//}

//project(":application") {
//    dependencies {
//        api(project(":common-lib"))
//        implementation(project(":domain"))
//    }
//}
//
//project(":infrastructure") {
//    dependencies {
//        implementation(project(":domain"))
//        implementation(project(":application"))
//        api(project(":common-lib"))
//
//        testImplementation(project(":domain"))
//        testImplementation(project(":application"))
//    }
//}

//project(":domain") {
//    dependencies {
//        api(project(":common-lib"))
//    }
//}
//
//project(":domain") {
//    val jar: Jar by tasks
//    val bootJar: BootJar by tasks
//
//    bootJar.enabled = false
//    jar.enabled = true
//}
//
//project(":common-lib") {
//    val jar: Jar by tasks
//    val bootJar: BootJar by tasks
//
//    bootJar.enabled = false
//    jar.enabled = true
//}
//
//project(":application") {
//    val jar: Jar by tasks
//    val bootJar: BootJar by tasks
//
//    bootJar.enabled = false
//    jar.enabled = true
//}
//
//project(":infrastructure") {
//    val jar: Jar by tasks
//    val bootJar: BootJar by tasks
//
//    bootJar.enabled = false
//    jar.enabled = true
//}
