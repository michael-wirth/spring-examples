package ch.helsana.microservice.stubs.recorder.client

import com.adcubum.syrius.api.partnermgmt.common.identifier.v1.WsPartnerIdType
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
import org.springframework.ws.WebServiceMessageFactory
import org.springframework.ws.client.core.support.WebServiceGatewaySupport
import org.springframework.ws.client.support.interceptor.ClientInterceptor
import java.util.GregorianCalendar
import javax.xml.datatype.DatatypeFactory


class ApiBridgeClient(webServiceMessageFactory: WebServiceMessageFactory, defaultUri: String, vararg clientInterceptor: ClientInterceptor) :
        WebServiceGatewaySupport(webServiceMessageFactory) {

    init {
        webServiceTemplate.interceptors = clientInterceptor
        webServiceTemplate.defaultUri = defaultUri
    }

    val partnerIdentifikatorService = defaultUri + "apibridge-partnermgmt/PartneridentifikatorService_v1"
    val partnerService = defaultUri + "apibridge-partnermgmt/PartnerService_v1"
    val datatypeFactory = DatatypeFactory.newInstance()

    fun resolvePortalKontoId(portalKontoId: String): String? {
        val searchOnlineIdRequest = SearchOnlineIdRequest(portalKontoId)
        val searchOnlineIdResponse = webServiceTemplate.marshalSendAndReceive(partnerIdentifikatorService, searchOnlineIdRequest) as SearchOnlineIdResponse

        if (searchOnlineIdResponse.partneridentifikatorId == null) {
            return null
        }
        val getPartneridentifikatorRequest = GetPartneridentifikatorRequest(listOf(searchOnlineIdResponse.partneridentifikatorId), stichtag())
        return (webServiceTemplate.marshalSendAndReceive(partnerIdentifikatorService, getPartneridentifikatorRequest) as GetPartneridentifikatorResponse).partneridentifikators.firstOrNull()?.run {
            partnerId.id
        }
    }

    fun getPartner(partnerId: String) : WsPartnerType {
        val getPartnerRequest = GetPartnerRequest(listOf(WsPartnerIdType(partnerId)), stichtag())
        return (webServiceTemplate.marshalSendAndReceive(partnerService, getPartnerRequest) as GetPartnerResponse).partners.first()
    }

    fun getFamilie(partnerId: String) : WsFamilieType {
        val getFamilieRequest = GetFamilieRequest(listOf(WsPartnerIdType(partnerId)), stichtag())
        return (webServiceTemplate.marshalSendAndReceive(partnerService, getFamilieRequest) as GetFamilieResponse).families.first()
    }

    fun stichtag() = datatypeFactory.newXMLGregorianCalendar(GregorianCalendar())
}
