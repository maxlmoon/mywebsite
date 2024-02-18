package com.max.website

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyWebsiteApplication

fun main(args: Array<String>) {
	runApplication<MyWebsiteApplication>(*args)
}
