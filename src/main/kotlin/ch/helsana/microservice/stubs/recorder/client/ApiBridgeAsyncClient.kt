package ch.helsana.microservice.stubs.recorder.client

import com.adcubum.syrius.api.bestandsverw.common.identifier.v1.WsKollektivvertragNamentlichIdType
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.versichertenkarte.v1.schema.WsVersichertenkarteType
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.versichertesobjekt.v1.schema.WsVersichertesObjektType
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.vertrag.v1.schema.WsKollektivvertragNamentlichType
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.vertrag.v1.schema.WsVertragType
import com.adcubum.syrius.api.partnermgmt.common.identifier.v1.WsLeistungserbringerIdType
import com.adcubum.syrius.api.partnermgmt.common.identifier.v1.WsOrganisationseinheitIdType
import com.adcubum.syrius.api.partnermgmt.common.identifier.v1.WsPartnerIdType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.adresse.v1.schema.WsPartnerDomiziladresseType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.email.v1.schema.WsPartnerEmailType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.leistungserbringer.v1.schema.WsPublicLeistungserbringerType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.organisationseinheit.v1.schema.WsOrganisationseinheitType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partner.v1.schema.WsFamilieType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partner.v1.schema.WsPartnerType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.telefon.v1.schema.WsOrganisationseinheitTelefonType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.telefon.v1.schema.WsPartnerTelefonType
import com.adcubum.syrius.api.produktmgmt.common.identifier.v1.WsProduktIdType
import com.adcubum.syrius.api.produktmgmt.produktpflege.produktkatalog.data.produkt.v1.schema.WsProduktType
import com.adcubum.syrius.api.schadenleistungsmgmt.leistungserbringung.leistungsberechnung.data.kostenbeteiligung.v1.schema.WsKostenbeteiligungType
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class ApiBridgeAsyncClient(private val apiBridgeClient: ApiBridgeClient) {

    @Async
    fun resolvePortalKontoId(portalKontoId: String): CompletableFuture<WsPartnerIdType?> {
        return CompletableFuture.completedFuture(apiBridgeClient.resolvePortalKontoId(portalKontoId))
    }

    @Async
    fun getPartner(partnerId: WsPartnerIdType): CompletableFuture<WsPartnerType> {
        return CompletableFuture.completedFuture(apiBridgeClient.getPartner(partnerId))
    }

    @Async
    fun getFamilie(partnerId: WsPartnerIdType): CompletableFuture<WsFamilieType> {
        return CompletableFuture.completedFuture(apiBridgeClient.getFamilie(partnerId))
    }

    @Async
    fun getDomizilAdressse(vararg partnerIds: WsPartnerIdType): CompletableFuture<List<WsPartnerDomiziladresseType>> {
        return CompletableFuture.completedFuture(apiBridgeClient.getDomizilAdressse(*partnerIds))
    }

    @Async
    fun getTelefon(vararg partnerIds: WsPartnerIdType): CompletableFuture<List<WsPartnerTelefonType>> {
        return CompletableFuture.completedFuture(apiBridgeClient.getTelefon(*partnerIds))
    }

    @Async
    fun getEmail(vararg partnerIds: WsPartnerIdType): CompletableFuture<List<WsPartnerEmailType>> {
        return CompletableFuture.completedFuture(apiBridgeClient.getEmail(*partnerIds))
    }

    @Async
    fun getOrganisationseinheit(vararg partnerIds: WsPartnerIdType): CompletableFuture<List<WsOrganisationseinheitType>> {
        return CompletableFuture.completedFuture(apiBridgeClient.getOrganisationseinheit(*partnerIds))
    }

    @Async
    fun getOrganisationseinheitTelefon(vararg organisationseinheitIds: WsOrganisationseinheitIdType): CompletableFuture<List<WsOrganisationseinheitTelefonType>> {
        return CompletableFuture.completedFuture(apiBridgeClient.getOrganisationseinheitTelefon(*organisationseinheitIds))
    }

    @Async
    fun getVertrag(partnerId: com.adcubum.syrius.api.bestandsverw.common.identifier.v1.WsPartnerIdType): CompletableFuture<List<WsVertragType>> {
        return CompletableFuture.completedFuture(apiBridgeClient.getVertrag(partnerId))
    }

    @Async
    fun getVersichertesObjekt(partnerId: com.adcubum.syrius.api.bestandsverw.common.identifier.v1.WsPartnerIdType): CompletableFuture<List<WsVersichertesObjektType>> {
        return CompletableFuture.completedFuture(apiBridgeClient.getVersichertesObjekt(partnerId))
    }

    @Async
    fun getProdukt(vararg produktIds: WsProduktIdType): CompletableFuture<List<WsProduktType>> {
        return CompletableFuture.completedFuture(apiBridgeClient.getProdukt(*produktIds))
    }

    @Async
    fun getVersichertenkarte(vararg partnerIds: com.adcubum.syrius.api.bestandsverw.common.identifier.v1.WsPartnerIdType): CompletableFuture<List<WsVersichertenkarteType>> {
        return CompletableFuture.completedFuture(apiBridgeClient.getVersichertenkarte(*partnerIds))
    }

    @Async
    fun getKostenbeteiligung(vararg partnerIds: com.adcubum.syrius.api.schadenleistungsmgmt.extern.partner.identifier.v1.WsPartnerIdType): CompletableFuture<List<WsKostenbeteiligungType>> {
        return CompletableFuture.completedFuture(apiBridgeClient.getKostenbeteiligung(*partnerIds))
    }

    @Async
    fun getKollektivvertrag(vararg kollektivvertragId: WsKollektivvertragNamentlichIdType): CompletableFuture<List<WsKollektivvertragNamentlichType>> {
        return CompletableFuture.completedFuture(apiBridgeClient.getKollektivvertrag(*kollektivvertragId))
    }

    @Async
    fun getLeistungserbringer(vararg leistungserbringerId: WsLeistungserbringerIdType): CompletableFuture<List<WsPublicLeistungserbringerType>> {
        return CompletableFuture.completedFuture(apiBridgeClient.getLeistungserbringer(*leistungserbringerId))
    }

}
