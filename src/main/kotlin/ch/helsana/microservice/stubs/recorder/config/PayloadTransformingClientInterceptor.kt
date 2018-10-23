package ch.helsana.microservice.stubs.recorder.config

import org.springframework.core.io.Resource
import org.springframework.util.StreamUtils
import org.springframework.ws.client.support.interceptor.ClientInterceptorAdapter
import org.springframework.ws.context.MessageContext
import org.springframework.ws.soap.saaj.SaajSoapMessage
import org.springframework.xml.transform.StringSource
import java.nio.charset.StandardCharsets
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMResult
import javax.xml.transform.dom.DOMSource

class PayloadTransformingClientInterceptor(xslt: Resource) : ClientInterceptorAdapter() {
    private val transformerFactory = TransformerFactory.newInstance()
    private val style = StringSource(StreamUtils.copyToString(xslt.url.openStream(), StandardCharsets.UTF_8))

    override fun handleResponse(messageContext: MessageContext): Boolean {
        val result = DOMResult()
        val response = (messageContext.response as SaajSoapMessage)
        transformerFactory.newTransformer(style).transform(
                response.saajMessage.soapPart.content,
                result)

        response.saajMessage = response.saajMessage.apply {
            soapPart.content = DOMSource(result.node)
        }
        return true
    }
}