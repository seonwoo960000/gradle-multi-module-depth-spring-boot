import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10" apply false
    kotlin("plugin.jpa") version "1.6.10" apply false
    kotlin("kapt") version "1.3.61"
}

java.sourceCompatibility = JavaVersion.VERSION_11

allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")

    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")

    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring") //allopen

    dependencies {
        //kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        //spring boot
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("io.micrometer:micrometer-registry-prometheus")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.springframework.boot:spring-boot-starter-validation") // kafka 에서 필요

        //mockk
        testImplementation("io.mockk:mockk:1.12.0")
        testImplementation("com.ninja-squad:springmockk:2.0.3")
    }

    dependencyManagement {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
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
