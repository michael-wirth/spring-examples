package ch.helsana.microservice.stubs.recorder.config

import org.springframework.core.io.Resource
import org.springframework.ws.client.support.interceptor.ClientInterceptorAdapter
import org.springframework.ws.context.MessageContext
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamSource


class PayloadTransformingClientInterceptor(xslt: Resource) : ClientInterceptorAdapter() {
    private val transformer = TransformerFactory.newInstance().newTransformer(
            StreamSource(xslt.url.openStream()))

    override fun handleResponse(messageContext: MessageContext): Boolean {
        transformer.transform(
                messageContext.response.payloadSource,
                messageContext.response.payloadResult)
        return true
    }
}
