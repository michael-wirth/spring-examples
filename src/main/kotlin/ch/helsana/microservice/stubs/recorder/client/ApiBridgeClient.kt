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
import org.springframework.ws.WebServiceMessageFactory
import org.springframework.ws.client.core.support.WebServiceGatewaySupport
import org.springframework.ws.client.support.interceptor.ClientInterceptor
import java.util.*
import javax.xml.datatype.DatatypeFactory

open class ApiBridgeClient(webServiceMessageFactory: WebServiceMessageFactory, defaultUri: String, vararg clientInterceptor: ClientInterceptor) :
        WebServiceGatewaySupport(webServiceMessageFactory) {

    init {
        webServiceTemplate.interceptors = clientInterceptor
        webServiceTemplate.defaultUri = defaultUri
    }

    val partnerIdentifikatorService = defaultUri + "apibridge-partnermgmt/PartneridentifikatorService_v1"
    val partnerService = defaultUri + "apibridge-partnermgmt/PartnerService_v1"
    val organisationseinheitService = defaultUri + "apibridge-partnermgmt/OrganisationseinheitService_v1"
    val oeZuteilungService = defaultUri + "apibridge-partnermgmt/OeZuteilungService_v1"
    val adresseService = defaultUri + "apibridge-partnermgmt/AdresseService_v1"
    val emailService = defaultUri + "apibridge-partnermgmt/EmailService_v1"
    val telefonService = defaultUri + "apibridge-partnermgmt/TelefonService_v1"
    val vertragService = defaultUri + "apibridge-bestandsverw/VertragService_v1"
    val versichertesObjektService = defaultUri + "apibridge-bestandsverw/VersichertesObjektService_v1"
    val versichertenkarteService = defaultUri + "apibridge-bestandsverw/VersichertenkarteService_v1"
    val produktService = defaultUri + "apibridge-produktmgmt/ProduktService_v1"
    val kostenbeteiligungService = defaultUri + "apibridge-schadenleistungsmgmt/KostenbeteiligungService_v1"
    val codeService = defaultUri + "apibridge-infrastructure/CodeService_v1"
    val bankService = defaultUri + "apibridge-partnermgmt/BankService_v1"
    val leistungserbringerService = defaultUri + "apibridge-partnermgmt/LeistungserbringerService_v1"

    val datatypeFactory = DatatypeFactory.newInstance()

    fun resolvePortalKontoId(portalKontoId: String): WsPartnerIdType? {
        val searchOnlineIdRequest = SearchOnlineIdRequest(portalKontoId)
        val searchOnlineIdResponse = webServiceTemplate.marshalSendAndReceive(partnerIdentifikatorService, searchOnlineIdRequest) as SearchOnlineIdResponse

        if (searchOnlineIdResponse.partneridentifikatorId == null) {
            return null
        }
        val partneridentifikatorRequest = GetPartneridentifikatorRequest(listOf(searchOnlineIdResponse.partneridentifikatorId), stichtag())
        return (webServiceTemplate.marshalSendAndReceive(partnerIdentifikatorService, partneridentifikatorRequest) as GetPartneridentifikatorResponse).partneridentifikators.firstOrNull()?.partnerId
    }

    fun getPartner(partnerId: WsPartnerIdType): WsPartnerType {
        val partnerRequest = GetPartnerRequest(listOf(partnerId), stichtag())
        return (webServiceTemplate.marshalSendAndReceive(partnerService, partnerRequest) as GetPartnerResponse).partners.first()
    }

    fun getFamilie(partnerId: WsPartnerIdType): WsFamilieType {
        val familieRequest = GetFamilieRequest(listOf(partnerId), stichtag())
        return (webServiceTemplate.marshalSendAndReceive(partnerService, familieRequest) as GetFamilieResponse).families.first()
    }

    fun getDomizilAdressse(vararg partnerIds: WsPartnerIdType): List<WsPartnerDomiziladresseType> {
        val adressRequest = GetPartnerDomiziladresseRequest(listOf(*partnerIds), stichtag())
        return (webServiceTemplate.marshalSendAndReceive(adresseService, adressRequest) as GetPartnerDomiziladresseResponse).partnerDomiziladressens
    }

    fun getTelefon(vararg partnerIds: WsPartnerIdType): List<WsPartnerTelefonType> {
        val telefonRequest = GetPartnerTelefonRequest(listOf(*partnerIds), stichtag())
        return (webServiceTemplate.marshalSendAndReceive(telefonService, telefonRequest) as GetPartnerTelefonResponse).partnerTelefons
    }

    fun getEmail(vararg partnerIds: WsPartnerIdType): List<WsPartnerEmailType> {
        val emailRequest = GetPartnerEmailRequest(listOf(*partnerIds), stichtag())
        return (webServiceTemplate.marshalSendAndReceive(emailService, emailRequest) as GetPartnerEmailResponse).partnerEmails
    }

    fun getOrganisationseinheit(vararg partnerIds: WsPartnerIdType): List<WsOrganisationseinheitType> {
        val oeZuteilungRequest = GetPartnerOeZuteilungRequest(listOf(*partnerIds), stichtag())
        val zuteilungList = (webServiceTemplate.marshalSendAndReceive(oeZuteilungService, oeZuteilungRequest) as GetPartnerOeZuteilungResponse).partnerGbereichListOeZuteilungs

        val organisationseinheitRequest = GetOrganisationseinheitRequest(zuteilungList.flatMap { it.partnerGbereichOeZuteilungs }.flatMap { it.oeZuteilungs }.map { it.organisationseinheitId }, stichtag())
        return (webServiceTemplate.marshalSendAndReceive(organisationseinheitService, organisationseinheitRequest) as GetOrganisationseinheitResponse).organisationseinheits
    }

    fun getOrganisationseinheitTelefon(vararg organisationseinheitIds: WsOrganisationseinheitIdType): List<WsOrganisationseinheitTelefonType> {
        val organisationseinheitTelefonRequest = GetOrganisationseinheitTelefonRequest(listOf(*organisationseinheitIds), stichtag())
        return (webServiceTemplate.marshalSendAndReceive(telefonService, organisationseinheitTelefonRequest) as GetOrganisationseinheitTelefonResponse).organisationseinheitTelefons
    }

    fun getVertrag(partnerId: com.adcubum.syrius.api.bestandsverw.common.identifier.v1.WsPartnerIdType): List<WsVertragType> {
        val searchVertragForPartnerRequest = SearchVertragForPartnerRequest(partnerId, stichtag())
        val vertragIdList = (webServiceTemplate.marshalSendAndReceive(vertragService, searchVertragForPartnerRequest) as SearchVertragForPartnerResponse).vertragIds

        val vertragRequest = GetVertragRequest(vertragIdList, stichtag())
        return (webServiceTemplate.marshalSendAndReceive(vertragService, vertragRequest) as GetVertragResponse).vertrags
    }

    fun getVersichertesObjekt(partnerId: com.adcubum.syrius.api.bestandsverw.common.identifier.v1.WsPartnerIdType): List<WsVersichertesObjektType> {
        val searchVersichertesObjektForPartnerRequest = SearchVersichertesObjektForPartnerRequest(partnerId, stichtag())
        val versichertesObjektList = (webServiceTemplate.marshalSendAndReceive(versichertesObjektService, searchVersichertesObjektForPartnerRequest) as SearchVersichertesObjektForPartnerResponse).versichertesObjektIds

        val versichertesObjektRequest = GetVersichertesObjektRequest(versichertesObjektList, stichtag())
        return (webServiceTemplate.marshalSendAndReceive(versichertesObjektService, versichertesObjektRequest) as GetVersichertesObjektResponse).versichertesObjekts
    }

    fun getProdukt(vararg produktIds: WsProduktIdType): List<WsProduktType> {
        val produktRequest = GetProduktRequest(listOf(*produktIds), stichtag())
        return (webServiceTemplate.marshalSendAndReceive(produktService, produktRequest) as GetProduktResponse).produkts
    }

    fun getVersichertenkarte(vararg partnerIds: com.adcubum.syrius.api.bestandsverw.common.identifier.v1.WsPartnerIdType): List<WsVersichertenkarteType> {
        val searchAktuelleVersichertenkarteForPartnerRequest = SearchAktuelleVersichertenkarteForPartnerRequest(listOf(*partnerIds))
        val versichertenkarteIdList = (webServiceTemplate.marshalSendAndReceive(versichertenkarteService, searchAktuelleVersichertenkarteForPartnerRequest) as SearchAktuelleVersichertenkarteForPartnerResponse).versichertenkarteIds

        val versichertenkarteRequest = GetVersichertenkarteRequest(versichertenkarteIdList)
        return (webServiceTemplate.marshalSendAndReceive(versichertenkarteService, versichertenkarteRequest) as GetVersichertenkarteResponse).versichertenkartes
    }

    fun getKostenbeteiligung(vararg partnerIds: com.adcubum.syrius.api.schadenleistungsmgmt.extern.partner.identifier.v1.WsPartnerIdType): List<WsKostenbeteiligungType> {
        val kostenbeteiligungenIdRequest = ReadKostenbeteiligungenOfGrundversicherungForPartnerRequest(listOf(*partnerIds), stichtag())
        return (webServiceTemplate.marshalSendAndReceive(kostenbeteiligungService, kostenbeteiligungenIdRequest) as ReadKostenbeteiligungenOfGrundversicherungForPartnerResponse).kostenbeteiligungGrundversicherungs
    }

    fun getKollektivvertrag(vararg kollektivvertragId: WsKollektivvertragNamentlichIdType): List<WsKollektivvertragNamentlichType> {
        val kollektivvertragRequest = GetKollektivvertragNamentlichRequest(listOf(*kollektivvertragId), stichtag())
        return (webServiceTemplate.marshalSendAndReceive(vertragService, kollektivvertragRequest) as GetKollektivvertragNamentlichResponse).kollektivvertragNamentliches
    }

    fun getLeistungserbringer(vararg leistungserbringerId: WsLeistungserbringerIdType): List<WsPublicLeistungserbringerType> {
        val leistungserbringerRequest = GetPublicLeistungserbringerRequest(listOf(*leistungserbringerId), stichtag())
        return (webServiceTemplate.marshalSendAndReceive(leistungserbringerService, leistungserbringerRequest) as GetPublicLeistungserbringerResponse).publicLeistungserbringers
    }

    private fun stichtag() = datatypeFactory.newXMLGregorianCalendar(GregorianCalendar())
}
