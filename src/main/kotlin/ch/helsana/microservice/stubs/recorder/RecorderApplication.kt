package ch.helsana.microservice.stubs.recorder

import ch.helsana.microservice.stubs.recorder.client.ApiBridgeClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RecorderApplication : CommandLineRunner {
    @Autowired
    lateinit var apiBridgeClient: ApiBridgeClient

    override fun run(vararg args: String?) {
        val partnerId = apiBridgeClient.resolvePortalKontoId("A1031934")
        partnerId?.apply {
            println(apiBridgeClient.getPartner(this))
            println(apiBridgeClient.getFamilie(this).mitglieders.size + 1)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<RecorderApplication>(*args)
}
