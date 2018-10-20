package ch.helsana.microservice.stubs.recorder

import ch.helsana.microservice.stubs.recorder.client.ApiBridgeClient
import com.adcubum.syrius.api.bestandsverw.common.identifier.v1.WsPartnerIdType
import com.adcubum.syrius.api.partnermgmt.common.identifier.v1.WsLeistungserbringerIdType
import com.adcubum.syrius.api.produktmgmt.common.identifier.v1.WsProduktIdType
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
            val partner = apiBridgeClient.getPartner(this)
            val adresse = apiBridgeClient.getDomizilAdressse(this)
            val email = apiBridgeClient.getEmail(this)
            val telefon = apiBridgeClient.getTelefon(this)
            val organisationseinheit = apiBridgeClient.getOrganisationseinheit(this)
            val organisationseinheitTelefon = apiBridgeClient.getOrganisationseinheitTelefon(organisationseinheit.first().id)
            val familie = apiBridgeClient.getFamilie(this)
            val vertrag = apiBridgeClient.getVertrag(WsPartnerIdType(this.id))
            val versichertenkarte = apiBridgeClient.getVersichertenkarte(WsPartnerIdType(this.id))
            val kostenbeteiligung = apiBridgeClient.getKostenbeteiligung(com.adcubum.syrius.api.schadenleistungsmgmt.extern.partner.identifier.v1.WsPartnerIdType(this.id))
            val versichertesObjekt = apiBridgeClient.getVersichertesObjekt(WsPartnerIdType(this.id))
            val produkte = apiBridgeClient.getProdukt(*versichertesObjekt
                    .flatMap { it.vertragsproduktes }
                    .map { WsProduktIdType(it.produktId.id) }
                    .toTypedArray())
            val leistungserbringer = apiBridgeClient.getLeistungserbringer(*versichertesObjekt
                    .flatMap { it.vertragsproduktes }
                    .map { it.leistungserbringerId.id }
                    .filterNot { it == "" }
                    .map { WsLeistungserbringerIdType(it) }
                    .toTypedArray())
            println("partner: $partner")
            println("adresse: ${adresse.size}")
            println("email: ${email.size}")
            println("telefon: ${telefon.size}")
            println("organisationseinheit: ${organisationseinheit.size}")
            println("organisationseinheitTelefon: ${organisationseinheitTelefon.size}")
            println("familie: ${familie.mitglieders.size}")
            println("vertrag: ${vertrag.size}")
            println("versichertenkarte: ${versichertenkarte.size}")
            println("kostenbeteiligung: ${kostenbeteiligung.size}")
            println("versichertesObjekt: ${versichertesObjekt.size}")
            println("produkte: ${produkte.size}")
            println("leistungserbringer: ${leistungserbringer.size}")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<RecorderApplication>(*args)
}
