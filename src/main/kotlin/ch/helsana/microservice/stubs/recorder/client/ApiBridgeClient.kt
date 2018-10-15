package ch.helsana.microservice.stubs.recorder.client

import com.adcubum.syrius.api.partnermgmt.common.identifier.v0.WsPartneridentifikatorIdType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partneridentifikator.v0.schema.GetPartneridentifikatorRequestType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partneridentifikator.v0.schema.GetPartneridentifikatorResponseType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partneridentifikator.v0.schema.SearchOnlineIdRequestType
import com.adcubum.syrius.api.partnermgmt.partnerdatenverw.data.partneridentifikator.v0.schema.SearchOnlineIdResponseType
import org.springframework.ws.WebServiceMessageFactory
import org.springframework.ws.client.core.support.WebServiceGatewaySupport
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor

class ApiBridgeClient(webServiceMessageFactory: WebServiceMessageFactory, wss4jSecurityInterceptor: Wss4jSecurityInterceptor) :
        WebServiceGatewaySupport(webServiceMessageFactory) {
    init {
        this.webServiceTemplate.interceptors = arrayOf(wss4jSecurityInterceptor)
    }

    fun getPartneridentifikatorResponse(portalKontoId :String): GetPartneridentifikatorResponseType {
        val searchOnlineIdRequestType  = SearchOnlineIdRequestType()
        searchOnlineIdRequestType.wert = portalKontoId
        val searchOnlineIdResponseType = webServiceTemplate.marshalSendAndReceive(searchOnlineIdRequestType) as SearchOnlineIdResponseType

        val getPartneridentifikatorRequestType = GetPartneridentifikatorRequestType()
        getPartneridentifikatorRequestType.partneridentifikatorId + searchOnlineIdResponseType.partneridentifikatorId
        return this.webServiceTemplate.marshalSendAndReceive(getPartneridentifikatorRequestType) as GetPartneridentifikatorResponseType
    }
}