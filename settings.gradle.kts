rootProject.name = "multi-module"

include("module1")
include("module2")
include("source")
//
project(":source").projectDir = File("stream/source")
