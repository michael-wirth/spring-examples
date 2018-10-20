package ch.helsana.microservice.stubs.recorder.config

import org.springframework.core.io.Resource
import org.springframework.ws.client.support.interceptor.ClientInterceptorAdapter
import org.springframework.ws.context.MessageContext
import org.springframework.ws.soap.saaj.SaajSoapMessage
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMResult
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamSource

class PayloadTransformingClientInterceptor(xslt: Resource) : ClientInterceptorAdapter() {
    private val transformer = TransformerFactory.newInstance().newTransformer(
            StreamSource(xslt.url.openStream()))

    override fun handleResponse(messageContext: MessageContext): Boolean {
        val result = DOMResult()
        val response = (messageContext.response as SaajSoapMessage)
        transformer.transform(
                response.saajMessage.soapPart.content,
                result)

        response.saajMessage = response.saajMessage.apply {
            soapPart.content = DOMSource(result.node)
        }
        return true
    }
}