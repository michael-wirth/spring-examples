package ch.helsana.microservice.stubs.recorder.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("api.bridge")
class ApiBridgeProperties {

    lateinit var requestContextPaths: List<String>
    lateinit var responseContextPaths: List<String>

    lateinit var baseUri: String

}
