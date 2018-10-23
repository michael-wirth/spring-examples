package ch.helsana.microservice.stubs.recorder

import ch.helsana.microservice.stubs.recorder.client.ApiBridgeAsyncClient
import com.adcubum.syrius.api.bestandsverw.common.identifier.v1.WsPartnerIdType
import com.adcubum.syrius.api.partnermgmt.common.identifier.v1.WsLeistungserbringerIdType
import com.adcubum.syrius.api.produktmgmt.common.identifier.v1.WsProduktIdType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.util.StopWatch
import java.util.concurrent.CompletableFuture

@SpringBootApplication
@EnableAsync
class RecorderApplication : CommandLineRunner {
//    @Autowired
//    lateinit var apiBridgeClient: ApiBridgeClient

    @Autowired
    lateinit var apiBridgeClient: ApiBridgeAsyncClient

    override fun run(vararg args: String?) {
        val stopWatch = StopWatch()
        stopWatch.start()
        apiBridgeClient.resolvePortalKontoId("A1031934")
                .thenCompose { it ->
                    it?.run {
                        val partner = apiBridgeClient.getPartner(this)
                        val familie = apiBridgeClient.getFamilie(this)
                        //    Stream.concat(Stream.of(it.vorstand.id), it.mitglieders.map { it.id }.stream())
                        familie.thenApply { it ->
                            it.mitglieders.map { it.id }.toMutableList().apply {
                                add(it.vorstand.id)
                            }.toTypedArray()
                        }.thenCompose {
                            val adresse = apiBridgeClient.getDomizilAdressse(*it)
                                    .whenComplete { result, _ -> println("Adresse: ${result.size}") }

                            val email = apiBridgeClient.getEmail(*it)
                                    .whenComplete { result, _ -> println("Email: ${result.size}") }

                            val telefon = apiBridgeClient.getTelefon(*it)
                                    .whenComplete { result, _ -> println("Telefon: ${result.size}") }

                            val organisationseinheit = apiBridgeClient.getOrganisationseinheit(*it)
                                    .whenComplete { result, _ -> println("Organisationeinheit: ${result.size}") }

                            val organisationseinheitTelefon = organisationseinheit.thenCompose { it ->
                                apiBridgeClient.getOrganisationseinheitTelefon(*it.map { it.id }.toTypedArray())
                            }
                                    .whenComplete { result, _ -> println("OrganisationeinheitTelefon: ${result.size}") }

                            val versichertenkarte = apiBridgeClient.getVersichertenkarte(*it.map { WsPartnerIdType(it.id) }.toTypedArray())
                                    .whenComplete { result, _ -> println("Versichertenkarte: ${result.size}") }

                            val kostenbeteiligung = apiBridgeClient.getKostenbeteiligung(*it.map {
                                com.adcubum.syrius.api.schadenleistungsmgmt.extern.partner.identifier.v1.WsPartnerIdType(it.id)
                            }.toTypedArray())
                                    .whenComplete { result, _ -> println("Kostenbeteiligung: ${result.size}") }

                            val vertragList = it.map {
                                apiBridgeClient.getVertrag(WsPartnerIdType(it.id))
                                        .whenComplete { result, _ -> println("Vertrag: ${result.size}") }
                            }

                            val vertrag = CompletableFuture.allOf(*vertragList.toTypedArray())

                            val versichertesObjektList = it.map {
                                apiBridgeClient.getVersichertesObjekt(WsPartnerIdType(it.id))
                                        .whenComplete { result, _ -> println("VersichertesObjekt: ${result.size}") }
                            }

                            val versichertesObjekt = CompletableFuture.allOf(*versichertesObjektList.toTypedArray())

                            val produkte = versichertesObjekt.thenCompose { _ ->
                                apiBridgeClient.getProdukt(*versichertesObjektList.flatMap { it.get() }.flatMap { it.vertragsproduktes }
                                        .map { WsProduktIdType(it.produktId.id) }.toTypedArray())
                            }
                                    .whenComplete { result, _ -> println("Produkt: ${result.size}") }

                            val kollektivvertrag = vertrag.thenCompose { _ ->
                                apiBridgeClient.getKollektivvertrag(*vertragList.flatMap { it.get() }.map { it.kollektivvertragNamentlichId }.toTypedArray())
                            }
                                    .whenComplete { result, _ -> println("Kollektivvertrag: ${result.size}") }

                            val kollektivvertragPartner = kollektivvertrag.thenCompose { it ->
                                apiBridgeClient.getPartner(*it.map { it.versicherungsnehmerId.id }.filterNotNull()
                                        .map { com.adcubum.syrius.api.partnermgmt.common.identifier.v1.WsPartnerIdType(it) }.toTypedArray())
                                        .whenComplete { result, _ -> println("kollektivvertragPartner: ${result.size}") }
                            }

                            val leistungserbringer = versichertesObjekt.thenCompose { _ ->
                                apiBridgeClient.getLeistungserbringer(*versichertesObjektList.flatMap { it.get() }.flatMap { it.vertragsproduktes }
                                        .map { it.leistungserbringerId.id }
                                        .filterNotNull()
                                        .map { WsLeistungserbringerIdType(it) }
                                        .toTypedArray())
                            }
                                    .whenComplete { result, _ -> println("Leistungserbringer: ${result.size}") }


                            CompletableFuture.allOf(partner, adresse, email, telefon, organisationseinheit, organisationseinheitTelefon,
                                    familie, vertrag, versichertenkarte, kostenbeteiligung, versichertesObjekt, produkte, leistungserbringer, kollektivvertrag)
                        }


//                        val vertrag = apiBridgeClient.getVertrag(WsPartnerIdType(this.id))
//                        val versichertesObjekt = apiBridgeClient.getVersichertesObjekt(WsPartnerIdType(this.id))
//                        val produkte = versichertesObjekt.thenApply { it ->
//                            apiBridgeClient.getProdukt(*it
//                                    .flatMap { it.vertragsproduktes }
//                                    .map { WsProduktIdType(it.produktId.id) }
//                                    .toTypedArray())
//                        }
//                        val kollektivvertrag = vertrag.thenApply { it ->
//                            apiBridgeClient.getKollektivvertrag(*it.map { it.kollektivvertragNamentlichId }.toTypedArray())
//                        }

//                        val leistungserbringer = versichertesObjekt.thenApply { it ->
//                            apiBridgeClient.getLeistungserbringer(*it.flatMap { it.vertragsproduktes }
//                                    .map { it.leistungserbringerId.id }
//                                    .filterNotNull()
//                                    .map { WsLeistungserbringerIdType(it) }
//                                    .toTypedArray())
//                        }

//                                .join()


//        val partnerId = apiBridgeClient.resolvePortalKontoId("A1031934")
//        partnerId?.apply {
//            val partner = apiBridgeClient.getPartner(this)
//            val adresse = apiBridgeClient.getDomizilAdressse(this)
//            val email = apiBridgeClient.getEmail(this)
//            val telefon = apiBridgeClient.getTelefon(this)
//            val organisationseinheit = apiBridgeClient.getOrganisationseinheit(this)
//            val organisationseinheitTelefon = apiBridgeClient.getOrganisationseinheitTelefon(organisationseinheit.first().id)
//            val familie = apiBridgeClient.getFamilie(this)
//            val vertrag = apiBridgeClient.getVertrag(WsPartnerIdType(this.id))
//            val versichertenkarte = apiBridgeClient.getVersichertenkarte(WsPartnerIdType(this.id))
//            val kostenbeteiligung = apiBridgeClient.getKostenbeteiligung(com.adcubum.syrius.api.schadenleistungsmgmt.extern.partner.identifier.v1.WsPartnerIdType(this.id))
//            val versichertesObjekt = apiBridgeClient.getVersichertesObjekt(WsPartnerIdType(this.id))
//            val produkte = apiBridgeClient.getProdukt(*versichertesObjekt
//                    .flatMap { it.vertragsproduktes }
//                    .map { WsProduktIdType(it.produktId.id) }
//                    .toTypedArray())
//            val kollektivvertrag = apiBridgeClient.getKollektivvertrag(*vertrag.map { it.kollektivvertragNamentlichId }.toTypedArray())
//            val leistungserbringer = apiBridgeClient.getLeistungserbringer(*versichertesObjekt
//                    .flatMap { it.vertragsproduktes }
//                    .map { it.leistungserbringerId.id }
//                    .filterNotNull()
//                    .map { WsLeistungserbringerIdType(it) }
//                    .toTypedArray())
//            println("partner: $partner")
//            println("adresse: ${adresse.size}")
//            println("email: ${email.size}")
//            println("telefon: ${telefon.size}")
//            println("organisationseinheit: ${organisationseinheit.size}")
//            println("organisationseinheitTelefon: ${organisationseinheitTelefon.size}")
//            println("familie: ${familie.mitglieders.size}")
//            println("vertrag: ${vertrag.size}")
//            println("versichertenkarte: ${versichertenkarte.size}")
//            println("kostenbeteiligung: ${kostenbeteiligung.size}")
//            println("versichertesObjekt: ${versichertesObjekt.size}")
//            println("produkte: ${produkte.size}")
//            println("leistungserbringer: ${leistungserbringer.size}")
//            println("kollektivvertrag: ${kollektivvertrag.size}")
                    }
                }.thenApply { it ->
                    System.out.println(it)
                }
                .join()
        stopWatch.stop()
        println(stopWatch.prettyPrint())
    }
}

fun main(args: Array<String>) {
    runApplication<RecorderApplication>(*args)
}
