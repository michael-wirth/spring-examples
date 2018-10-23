package ch.helsana.microservice.stubs.recorder.client

import com.adcubum.syrius.api.bestandsverw.common.identifier.v1.WsKollektivvertragNamentlichIdType
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.versichertenkarte.v1.schema.GetVersichertenkarteRequest
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.versichertenkarte.v1.schema.GetVersichertenkarteResponse
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.versichertenkarte.v1.schema.SearchAktuelleVersichertenkarteForPartnerRequest
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.versichertenkarte.v1.schema.SearchAktuelleVersichertenkarteForPartnerResponse
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.versichertenkarte.v1.schema.WsVersichertenkarteType
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.versichertesobjekt.v1.schema.GetVersichertesObjektRequest
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.versichertesobjekt.v1.schema.GetVersichertesObjektResponse
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.versichertesobjekt.v1.schema.SearchVersichertesObjektForPartnerRequest
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.versichertesobjekt.v1.schema.SearchVersichertesObjektForPartnerResponse
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.versichertesobjekt.v1.schema.WsVersichertesObjektType
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.vertrag.v1.schema.GetKollektivvertragNamentlichRequest
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.vertrag.v1.schema.GetKollektivvertragNamentlichResponse
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.vertrag.v1.schema.GetVertragRequest
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.vertrag.v1.schema.GetVertragResponse
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.vertrag.v1.schema.SearchVertragForPartnerRequest
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.vertrag.v1.schema.SearchVertragForPartnerResponse
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.vertrag.v1.schema.WsKollektivvertragNamentlichType
import com.adcubum.syrius.api.bestandsverw.vertragsverw.auskunft.data.vertrag.v1.schema.WsVertragType
import com.adcubum.syrius.api.partnermgmt.common.identifier.v1.WsLeistungserbringerIdType
import com.adcubum.syrius.api.partnermgmt.common.identifier.v1.WsOrganisationseinheitIdType
import com.adcubum.syrius.api.partnermgmt.common.identifier.v1.WsPartnerIdType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.adresse.v1.schema.GetPartnerDomiziladresseRequest
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.adresse.v1.schema.GetPartnerDomiziladresseResponse
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.adresse.v1.schema.WsPartnerDomiziladresseType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.email.v1.schema.GetPartnerEmailRequest
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.email.v1.schema.GetPartnerEmailResponse
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.email.v1.schema.WsPartnerEmailType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.leistungserbringer.v1.schema.GetPublicLeistungserbringerRequest
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.leistungserbringer.v1.schema.GetPublicLeistungserbringerResponse
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.leistungserbringer.v1.schema.WsPublicLeistungserbringerType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.oezuteilung.v1.schema.GetPartnerOeZuteilungRequest
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.oezuteilung.v1.schema.GetPartnerOeZuteilungResponse
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.organisationseinheit.v1.schema.GetOrganisationseinheitRequest
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.organisationseinheit.v1.schema.GetOrganisationseinheitResponse
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.organisationseinheit.v1.schema.WsOrganisationseinheitType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partner.v1.schema.GetFamilieRequest
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partner.v1.schema.GetFamilieResponse
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partner.v1.schema.GetPartnerRequest
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partner.v1.schema.GetPartnerResponse
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partner.v1.schema.WsFamilieType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partner.v1.schema.WsPartnerType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partneridentifikator.v1.schema.GetPartneridentifikatorRequest
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partneridentifikator.v1.schema.GetPartneridentifikatorResponse
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partneridentifikator.v1.schema.SearchOnlineIdRequest
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partneridentifikator.v1.schema.SearchOnlineIdResponse
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.telefon.v1.schema.GetOrganisationseinheitTelefonRequest
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.telefon.v1.schema.GetOrganisationseinheitTelefonResponse
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.telefon.v1.schema.GetPartnerTelefonRequest
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.telefon.v1.schema.GetPartnerTelefonResponse
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.telefon.v1.schema.WsOrganisationseinheitTelefonType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.telefon.v1.schema.WsPartnerTelefonType
import com.adcubum.syrius.api.produktmgmt.common.identifier.v1.WsProduktIdType
import com.adcubum.syrius.api.produktmgmt.produktpflege.produktkatalog.data.produkt.v1.schema.GetProduktRequest
import com.adcubum.syrius.api.produktmgmt.produktpflege.produktkatalog.data.produkt.v1.schema.GetProduktResponse
import com.adcubum.syrius.api.produktmgmt.produktpflege.produktkatalog.data.produkt.v1.schema.WsProduktType
import com.adcubum.syrius.api.schadenleistungsmgmt.leistungserbringung.leistungsberechnung.data.kostenbeteiligung.v1.schema.ReadKostenbeteiligungenOfGrundversicherungForPartnerRequest
import com.adcubum.syrius.api.schadenleistungsmgmt.leistungserbringung.leistungsberechnung.data.kostenbeteiligung.v1.schema.ReadKostenbeteiligungenOfGrundversicherungForPartnerResponse
import com.adcubum.syrius.api.schadenleistungsmgmt.leistungserbringung.leistungsberechnung.data.kostenbeteiligung.v1.schema.WsKostenbeteiligungType
import org.jvnet.jaxb2_commons.lang.ToString2
import org.slf4j.LoggerFactory
import org.springframework.ws.WebServiceMessageFactory
import org.springframework.ws.client.core.support.WebServiceGatewaySupport
import org.springframework.ws.client.support.interceptor.ClientInterceptor
import java.util.GregorianCalendar
import javax.xml.datatype.DatatypeFactory

open class ApiBridgeClient(webServiceMessageFactory: WebServiceMessageFactory, defaultUri: String, vararg clientInterceptor: ClientInterceptor) :
        WebServiceGatewaySupport(webServiceMessageFactory) {

    init {
        webServiceTemplate.interceptors = clientInterceptor
        webServiceTemplate.defaultUri = defaultUri
    }

    companion object {
        private const val PARTNERIDENTIFIKATORSERVICEURI = "apibridge-partnermgmt/PartneridentifikatorService_v1"
        private const val PARTNERSERVICEURI = "apibridge-partnermgmt/PartnerService_v1"
        private const val ORGANISATIONSEINHEITSERVICEURI = "apibridge-partnermgmt/OrganisationseinheitService_v1"
        private const val OEZUTEILUNGSERVICEURI = "apibridge-partnermgmt/OeZuteilungService_v1"
        private const val ADRESSESERVICEURI = "apibridge-partnermgmt/AdresseService_v1"
        private const val EMAILSERVICEURI = "apibridge-partnermgmt/EmailService_v1"
        private const val TELEFONSERVICEURI = "apibridge-partnermgmt/TelefonService_v1"
        private const val VERTRAGSERVICEURI = "apibridge-bestandsverw/VertragService_v1"
        private const val VERSICHERTESOBJEKTSERVICEURI = "apibridge-bestandsverw/VersichertesObjektService_v1"
        private const val VERSICHERTENKARTESERVICEURI = "apibridge-bestandsverw/VersichertenkarteService_v1"
        private const val PRODUKTSERVICEURI = "apibridge-produktmgmt/ProduktService_v1"
        private const val KOSTENBETEILIGUNGSERVICEURI = "apibridge-schadenleistungsmgmt/KostenbeteiligungService_v1"
        private const val CODESERVICEURI = "apibridge-infrastructure/CodeService_v1"
        private const val BANKSERVICEURI = "apibridge-partnermgmt/BankService_v1"
        private const val LEISTUNGSERBRINGERSERVICEURI = "apibridge-partnermgmt/LeistungserbringerService_v1"
    }

//    val partnerIdentifikatorService = defaultUri + "apibridge-partnermgmt/PartneridentifikatorService_v1"
//    val partnerService = defaultUri + "apibridge-partnermgmt/PartnerService_v1"
//    val organisationseinheitService = defaultUri + "apibridge-partnermgmt/OrganisationseinheitService_v1"
//    val oeZuteilungService = defaultUri + "apibridge-partnermgmt/OeZuteilungService_v1"
//    val adresseService = defaultUri + "apibridge-partnermgmt/AdresseService_v1"
//    val emailService = defaultUri + "apibridge-partnermgmt/EmailService_v1"
//    val telefonService = defaultUri + "apibridge-partnermgmt/TelefonService_v1"
//    val vertragService = defaultUri + "apibridge-bestandsverw/VertragService_v1"
//    val versichertesObjektService = defaultUri + "apibridge-bestandsverw/VersichertesObjektService_v1"
//    val versichertenkarteService = defaultUri + "apibridge-bestandsverw/VersichertenkarteService_v1"
//    val produktService = defaultUri + "apibridge-produktmgmt/ProduktService_v1"
//    val kostenbeteiligungService = defaultUri + "apibridge-schadenleistungsmgmt/KostenbeteiligungService_v1"
//    val codeService = defaultUri + "apibridge-infrastructure/CodeService_v1"
//    val bankService = defaultUri + "apibridge-partnermgmt/BankService_v1"
//    val leistungserbringerService = defaultUri + "apibridge-partnermgmt/LeistungserbringerService_v1"

    private val datatypeFactory = DatatypeFactory.newInstance()
    private val LOG = LoggerFactory.getLogger(ApiBridgeClient.javaClass)

    open fun resolvePortalKontoId(portalKontoId: String): WsPartnerIdType? {
        val searchOnlineIdRequest = SearchOnlineIdRequest(portalKontoId)
                .apply { LOG.debug(toString(this)) }
        val searchOnlineIdResponse = webServiceTemplate.marshalSendAndReceive(url(PARTNERIDENTIFIKATORSERVICEURI), searchOnlineIdRequest) as SearchOnlineIdResponse

        if (searchOnlineIdResponse.partneridentifikatorId == null) {
            return null
        }
        val partneridentifikatorRequest = GetPartneridentifikatorRequest(listOf(searchOnlineIdResponse.partneridentifikatorId), stichtag())
                .apply { LOG.debug(toString(this)) }
        return (webServiceTemplate.marshalSendAndReceive(url(PARTNERIDENTIFIKATORSERVICEURI), partneridentifikatorRequest) as GetPartneridentifikatorResponse).partneridentifikators.firstOrNull()?.partnerId
    }

    open fun getPartner(vararg partnerId: WsPartnerIdType): List<WsPartnerType> {
        val partnerRequest = GetPartnerRequest(listOf(*partnerId), stichtag())
                .apply { LOG.debug(toString(this)) }
        return (webServiceTemplate.marshalSendAndReceive(url(PARTNERSERVICEURI), partnerRequest) as GetPartnerResponse).partners
    }

    open fun getFamilie(partnerId: WsPartnerIdType): WsFamilieType {
        val familieRequest = GetFamilieRequest(listOf(partnerId), stichtag())
                .apply { LOG.debug(toString(this)) }
        return (webServiceTemplate.marshalSendAndReceive(url(PARTNERSERVICEURI), familieRequest) as GetFamilieResponse).families.first()
    }

    open fun getDomizilAdressse(vararg partnerIds: WsPartnerIdType): List<WsPartnerDomiziladresseType> {
        val adressRequest = GetPartnerDomiziladresseRequest(listOf(*partnerIds), stichtag())
                .apply { LOG.debug(toString(this)) }
        return (webServiceTemplate.marshalSendAndReceive(url(ADRESSESERVICEURI), adressRequest) as GetPartnerDomiziladresseResponse).partnerDomiziladressens
    }

    open fun getTelefon(vararg partnerIds: WsPartnerIdType): List<WsPartnerTelefonType> {
        val telefonRequest = GetPartnerTelefonRequest(listOf(*partnerIds), stichtag())
                .apply { LOG.debug(toString(this)) }
        return (webServiceTemplate.marshalSendAndReceive(url(TELEFONSERVICEURI), telefonRequest) as GetPartnerTelefonResponse).partnerTelefons
    }

    open fun getEmail(vararg partnerIds: WsPartnerIdType): List<WsPartnerEmailType> {
        val emailRequest = GetPartnerEmailRequest(listOf(*partnerIds), stichtag())
                .apply { LOG.debug(toString(this)) }
        return (webServiceTemplate.marshalSendAndReceive(url(EMAILSERVICEURI), emailRequest) as GetPartnerEmailResponse).partnerEmails
    }

    open fun getOrganisationseinheit(vararg partnerIds: WsPartnerIdType): List<WsOrganisationseinheitType> {
        val oeZuteilungRequest = GetPartnerOeZuteilungRequest(listOf(*partnerIds), stichtag())
                .apply { LOG.debug(toString(this)) }
        val zuteilungList = (webServiceTemplate.marshalSendAndReceive(url(OEZUTEILUNGSERVICEURI), oeZuteilungRequest) as GetPartnerOeZuteilungResponse).partnerGbereichListOeZuteilungs

        val organisationseinheitRequest = GetOrganisationseinheitRequest(zuteilungList.flatMap { it.partnerGbereichOeZuteilungs }.flatMap { it.oeZuteilungs }.map { it.organisationseinheitId }, stichtag())
                .apply { LOG.debug(toString(this)) }
        return (webServiceTemplate.marshalSendAndReceive(url(ORGANISATIONSEINHEITSERVICEURI), organisationseinheitRequest) as GetOrganisationseinheitResponse).organisationseinheits
    }

    open fun getOrganisationseinheitTelefon(vararg organisationseinheitIds: WsOrganisationseinheitIdType): List<WsOrganisationseinheitTelefonType> {
        val organisationseinheitTelefonRequest = GetOrganisationseinheitTelefonRequest(listOf(*organisationseinheitIds), stichtag())
                .apply { LOG.debug(toString(this)) }
        return (webServiceTemplate.marshalSendAndReceive(url(TELEFONSERVICEURI), organisationseinheitTelefonRequest) as GetOrganisationseinheitTelefonResponse).organisationseinheitTelefons
    }

    open fun getVertrag(partnerId: com.adcubum.syrius.api.bestandsverw.common.identifier.v1.WsPartnerIdType): List<WsVertragType> {
        val searchVertragForPartnerRequest = SearchVertragForPartnerRequest(partnerId, stichtag())
                .apply { LOG.debug(toString(this)) }
        val vertragIdList = (webServiceTemplate.marshalSendAndReceive(url(VERTRAGSERVICEURI), searchVertragForPartnerRequest) as SearchVertragForPartnerResponse).vertragIds

        val vertragRequest = GetVertragRequest(vertragIdList, stichtag())
                .apply { LOG.debug(toString(this)) }
        return (webServiceTemplate.marshalSendAndReceive(url(VERTRAGSERVICEURI), vertragRequest) as GetVertragResponse).vertrags
    }

    open fun getVersichertesObjekt(partnerId: com.adcubum.syrius.api.bestandsverw.common.identifier.v1.WsPartnerIdType): List<WsVersichertesObjektType> {
        val searchVersichertesObjektForPartnerRequest = SearchVersichertesObjektForPartnerRequest(partnerId, stichtag())
                .apply { LOG.debug(toString(this)) }
        val versichertesObjektList = (webServiceTemplate.marshalSendAndReceive(url(VERSICHERTESOBJEKTSERVICEURI), searchVersichertesObjektForPartnerRequest) as SearchVersichertesObjektForPartnerResponse).versichertesObjektIds

        val versichertesObjektRequest = GetVersichertesObjektRequest(versichertesObjektList, stichtag())
                .apply { LOG.debug(toString(this)) }
        return (webServiceTemplate.marshalSendAndReceive(url(VERSICHERTESOBJEKTSERVICEURI), versichertesObjektRequest) as GetVersichertesObjektResponse).versichertesObjekts
    }

    open fun getProdukt(vararg produktIds: WsProduktIdType): List<WsProduktType> {
        val produktRequest = GetProduktRequest(listOf(*produktIds), stichtag())
                .apply { LOG.debug(toString(this)) }
        return (webServiceTemplate.marshalSendAndReceive(url(PRODUKTSERVICEURI), produktRequest) as GetProduktResponse).produkts
    }

    open fun getVersichertenkarte(vararg partnerIds: com.adcubum.syrius.api.bestandsverw.common.identifier.v1.WsPartnerIdType): List<WsVersichertenkarteType> {
        val searchAktuelleVersichertenkarteForPartnerRequest = SearchAktuelleVersichertenkarteForPartnerRequest(listOf(*partnerIds))
                .apply { LOG.debug(toString(this)) }
        val versichertenkarteIdList = (webServiceTemplate.marshalSendAndReceive(url(VERSICHERTENKARTESERVICEURI), searchAktuelleVersichertenkarteForPartnerRequest) as SearchAktuelleVersichertenkarteForPartnerResponse).versichertenkarteIds

        val versichertenkarteRequest = GetVersichertenkarteRequest(versichertenkarteIdList)
                .apply { LOG.debug(toString(this)) }
        return (webServiceTemplate.marshalSendAndReceive(url(VERSICHERTENKARTESERVICEURI), versichertenkarteRequest) as GetVersichertenkarteResponse).versichertenkartes
    }

    open fun getKostenbeteiligung(vararg partnerIds: com.adcubum.syrius.api.schadenleistungsmgmt.extern.partner.identifier.v1.WsPartnerIdType): List<WsKostenbeteiligungType> {
        val kostenbeteiligungenIdRequest = ReadKostenbeteiligungenOfGrundversicherungForPartnerRequest(listOf(*partnerIds), stichtag())
                .apply { LOG.debug(toString(this)) }
        return (webServiceTemplate.marshalSendAndReceive(url(KOSTENBETEILIGUNGSERVICEURI), kostenbeteiligungenIdRequest) as ReadKostenbeteiligungenOfGrundversicherungForPartnerResponse).kostenbeteiligungGrundversicherungs
    }

    open fun getKollektivvertrag(vararg kollektivvertragId: WsKollektivvertragNamentlichIdType): List<WsKollektivvertragNamentlichType> {
        val kollektivvertragRequest = GetKollektivvertragNamentlichRequest(listOf(*kollektivvertragId), stichtag())
                .apply { LOG.debug(toString(this)) }
        return (webServiceTemplate.marshalSendAndReceive(url(VERTRAGSERVICEURI), kollektivvertragRequest) as GetKollektivvertragNamentlichResponse).kollektivvertragNamentliches
    }

    open fun getLeistungserbringer(vararg leistungserbringerId: WsLeistungserbringerIdType): List<WsPublicLeistungserbringerType> {
        val leistungserbringerRequest = GetPublicLeistungserbringerRequest(listOf(*leistungserbringerId), stichtag())
                .apply { LOG.debug(toString(this)) }
        return (webServiceTemplate.marshalSendAndReceive(url(LEISTUNGSERBRINGERSERVICEURI), leistungserbringerRequest) as GetPublicLeistungserbringerResponse).publicLeistungserbringers
    }

    private fun stichtag() = datatypeFactory.newXMLGregorianCalendar(GregorianCalendar())

    private fun url(serviceUri: String) = webServiceTemplate.defaultUri + "/" + serviceUri

    private fun toString(obj: ToString2): String {
        val buffer = StringBuilder()
        obj.append(null, buffer, TestStringStrategy.INSTANCE)
        return buffer.toString()
    }
}
