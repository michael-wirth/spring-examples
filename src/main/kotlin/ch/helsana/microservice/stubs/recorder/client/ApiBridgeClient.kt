package ch.helsana.microservice.stubs.recorder.client

import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partneridentifikator.v0.schema.GetPartneridentifikatorRequest
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partneridentifikator.v0.schema.GetPartneridentifikatorResponse
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partneridentifikator.v0.schema.SearchOnlineIdRequest
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partneridentifikator.v0.schema.SearchOnlineIdResponse
import org.springframework.ws.WebServiceMessageFactory
import org.springframework.ws.client.core.support.WebServiceGatewaySupport
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor
import javax.xml.datatype.DatatypeFactory

class ApiBridgeClient(webServiceMessageFactory: WebServiceMessageFactory, wss4jSecurityInterceptor: Wss4jSecurityInterceptor, defaultUri: String) :
        WebServiceGatewaySupport(webServiceMessageFactory) {
    init {
        webServiceTemplate.interceptors = arrayOf(wss4jSecurityInterceptor, LogHttpHeaderClientInterceptor())
        webServiceTemplate.defaultUri = defaultUri
    }

    fun getPartneridentifikatorResponse(portalKontoId: String): GetPartneridentifikatorResponse {
        val searchOnlineIdRequestType = SearchOnlineIdRequest()
        searchOnlineIdRequestType.wert = portalKontoId
        val searchOnlineIdResponseType = webServiceTemplate.marshalSendAndReceive(searchOnlineIdRequestType) as SearchOnlineIdResponse
//        val searchOnlineIdResponseType = webServiceTemplate.marshalSendAndReceive("apibridge-partnermgmt/PartnerService_v1", searchOnlineIdRequestType) as SearchOnlineIdResponseType

        val getPartneridentifikatorRequest = GetPartneridentifikatorRequest()
        getPartneridentifikatorRequest.partneridentifikatorIds + searchOnlineIdResponseType.partneridentifikatorId
        getPartneridentifikatorRequest.stichtag = DatatypeFactory.newInstance().newXMLGregorianCalendar()
        return this.webServiceTemplate.marshalSendAndReceive("apibridge-partnermgmt/PartnerService_v1", getPartneridentifikatorRequest) as GetPartneridentifikatorResponse
    }
}
