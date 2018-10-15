package ch.helsana.microservice.stubs.recorder.client

import org.springframework.ws.client.WebServiceClientException
import org.springframework.ws.client.support.interceptor.ClientInterceptor
import org.springframework.ws.context.MessageContext

class LogHttpHeaderClientInterceptor : ClientInterceptor {

    @Throws(WebServiceClientException::class)
    override fun afterCompletion(messageContext: MessageContext, arg1: Exception) {
        HttpLoggingUtils.logMessage("After Completion", messageContext.response)
    }

    @Throws(WebServiceClientException::class)
    override fun handleFault(messageContext: MessageContext): Boolean {
        HttpLoggingUtils.logMessage("Handle Fault", messageContext.response)
        return true
    }

    @Throws(WebServiceClientException::class)
    override fun handleRequest(messageContext: MessageContext): Boolean {
        HttpLoggingUtils.logMessage("Client Request Message", messageContext.request)

        return true
    }

    @Throws(WebServiceClientException::class)
    override fun handleResponse(messageContext: MessageContext): Boolean {
        HttpLoggingUtils.logMessage("Client Response Message", messageContext.response)

        return true
    }


}
