package ch.helsana.microservice.stubs.recorder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RecorderApplication

fun main(args: Array<String>) {
    runApplication<RecorderApplication>(*args)
}
