package ch.helsana.microservice.stubs.recorder.client

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy

class TestStringStrategy : JAXBToStringStrategy() {

    companion object {
        val INSTANCE = TestStringStrategy()
    }

    override fun appendClassName(buffer: StringBuilder, obj: Any) {
        buffer.append(getShortClassName(obj.javaClass))
    }

    override fun appendIdentityHashCode(buffer: java.lang.StringBuilder?, obj: Any) {}
}