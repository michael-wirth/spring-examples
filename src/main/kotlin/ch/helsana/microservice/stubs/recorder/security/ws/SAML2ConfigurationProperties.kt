package ch.helsana.microservice.stubs.recorder.security.ws

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.validation.annotation.Validated

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "webservices.security")
class SAML2ConfigurationProperties {
    val keystore = Keystore()

    var issuer: String? = null
    var subject: String? = null
    var ttlSeconds: Int = 0

    class Keystore {

        var location: String? = null
        var password: String? = null
        var alias: String? = null
        var privateKeyPassword: String? = null
        var type = "JKS"
    }
}
