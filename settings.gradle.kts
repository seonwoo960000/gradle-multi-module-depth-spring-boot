rootProject.name = "multi-module"

include(":module1")
include(":module2")

include(":stream")
include(":stream:processor")
include(":stream:sink")
include(":stream:source")
//
//project(":processor").projectDir = File("stream/processor")
//project(":sink").projectDir = File("stream/sink")
//project(":source").projectDir = File("stream/source")
