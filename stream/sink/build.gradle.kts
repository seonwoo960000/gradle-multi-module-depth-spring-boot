dependencies {
    implementation(project(mapOf("path" to ":stream:source")))
}

// Throws an error if not specified
// Error: Task 'prepareKotlinBuildScriptModel' not found in project ':source'.
task("prepareKotlinBuildScriptModel")
