package com.example.module1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class Module1Application

fun main(args: Array<String>) {
	runApplication<Module1Application>(*args)
}

@RestController
@RequestMapping("/module1")
class HelloController {

	@GetMapping("/hello")
	fun hello(): String {
		return "hello"
	}
}
