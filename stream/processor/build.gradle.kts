dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

// Throws an error if not specified
// Error: Task 'prepareKotlinBuildScriptModel' not found in project ':source'.
task("prepareKotlinBuildScriptModel")