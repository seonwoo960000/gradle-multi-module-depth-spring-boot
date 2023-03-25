rootProject.name = "multi-module"

include("module1")
include("module2")
include("processor")
include("sink")
include("source")
//
project(":processor").projectDir = File("stream/processor")
project(":sink").projectDir = File("stream/sink")
project(":source").projectDir = File("stream/source")
