package ch.helsana.microservice.stubs.recorder.client

import org.slf4j.LoggerFactory
import org.springframework.ws.WebServiceMessage
import org.springframework.xml.transform.TransformerObjectSupport

class HttpLoggingUtils private constructor() : TransformerObjectSupport() {
    companion object {

        private val LOGGER = LoggerFactory.getLogger(HttpLoggingUtils::class.java)

        private val NEW_LINE = System.getProperty("line.separator")

        fun logMessage(id: String, webServiceMessage: WebServiceMessage) {
            try {
                val byteArrayTransportOutputStream = ByteArrayTransportOutputStream()
                webServiceMessage.writeTo(byteArrayTransportOutputStream)

                val httpMessage = String(byteArrayTransportOutputStream.toByteArray())
                LOGGER.info(NEW_LINE + "----------------------------" + NEW_LINE + id + NEW_LINE
                        + "----------------------------" + NEW_LINE + httpMessage + NEW_LINE)
            } catch (e: Exception) {
                LOGGER.error("Unable to log HTTP message.", e)
            }

        }
    }
}
