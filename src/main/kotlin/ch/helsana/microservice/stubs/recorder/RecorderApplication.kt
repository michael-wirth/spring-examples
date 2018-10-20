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
            val familie = apiBridgeClient.getFamilie(this)
            println("vorstand")
            println(familie.vorstand.intBetreuerId.id)
            println("familie")
            familie.mitglieders.stream().map { it.intBetreuerId.id }.forEach { println(it) }

        }
    }
}

fun main(args: Array<String>) {
    runApplication<RecorderApplication>(*args)
}
