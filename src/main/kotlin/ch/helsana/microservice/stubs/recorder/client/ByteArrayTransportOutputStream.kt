package ch.helsana.microservice.stubs.recorder.client

import org.springframework.ws.transport.TransportOutputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.OutputStream

class ByteArrayTransportOutputStream : TransportOutputStream() {

    private var byteArrayOutputStream: ByteArrayOutputStream? = null

    @Throws(IOException::class)
    override fun addHeader(name: String, value: String) {
        createOutputStream()
        val header = "$name: $value$NEW_LINE"
        byteArrayOutputStream!!.write(header.toByteArray())
    }

    @Throws(IOException::class)
    override fun createOutputStream(): OutputStream? {
        if (byteArrayOutputStream == null) {
            byteArrayOutputStream = ByteArrayOutputStream()
        }
        return byteArrayOutputStream
    }

    fun toByteArray(): ByteArray {
        return byteArrayOutputStream!!.toByteArray()
    }

    companion object {

        private val NEW_LINE = System.getProperty("line.separator")
    }
}
