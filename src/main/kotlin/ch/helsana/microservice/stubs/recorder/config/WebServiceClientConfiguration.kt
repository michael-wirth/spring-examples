package ch.helsana.microservice.stubs.recorder.config

import ch.helsana.microservice.stubs.recorder.client.ApiBridgeClient
import ch.helsana.microservice.stubs.recorder.security.ws.SAML2ConfigurationProperties
import org.apache.wss4j.common.crypto.Crypto
import org.apache.wss4j.dom.common.AbstractSAMLCallbackHandler
import org.apache.wss4j.dom.common.SAML2CallbackHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.Resource
import org.springframework.oxm.jaxb.Jaxb2Marshaller
import org.springframework.ws.WebServiceMessageFactory
import org.springframework.ws.soap.SoapVersion
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean
import java.io.IOException

@Configuration
@EnableConfigurationProperties(ApiBridgeProperties::class, SAML2ConfigurationProperties::class)
class WebServiceClientConfiguration(private val saml2ConfigurationProperties: SAML2ConfigurationProperties, private val apiBridgeProperties: ApiBridgeProperties) {

    @Value("classpath:/ws/xslt/remove-proprietary-state-enums.xslt")
    lateinit var apiBridgeResponseXsltResource: Resource

    @Bean
    internal fun apiBridgeMarshaller() = Jaxb2Marshaller().apply {
        setContextPaths(*apiBridgeProperties.contextPaths.toTypedArray())
    }

    @Bean
    internal fun apiBridgeClient(webServiceMessageFactory: WebServiceMessageFactory,
                                 apiBridgeMarshaller: Jaxb2Marshaller,
                                 wss4jSecurityInterceptor: Wss4jSecurityInterceptor,
                                 payloadTransformingInterceptor: PayloadTransformingClientInterceptor): ApiBridgeClient = ApiBridgeClient(webServiceMessageFactory, apiBridgeProperties.baseUri,
            wss4jSecurityInterceptor, payloadTransformingClientInterceptor()).apply {
        marshaller = apiBridgeMarshaller
        unmarshaller = apiBridgeMarshaller
    }

    @Bean
    internal fun saajSoapMessageFactory() = SaajSoapMessageFactory().apply {
        setSoapVersion(SoapVersion.SOAP_11)
    }

    @Bean
    internal fun payloadTransformingClientInterceptor() =
            PayloadTransformingClientInterceptor(apiBridgeResponseXsltResource)

    @Bean
    @Throws(IOException::class)
    internal fun cryptoFactory() = CryptoFactoryBean().apply {
        setKeyStoreType(saml2ConfigurationProperties.keystore.type)
        setKeyStoreLocation(DefaultResourceLoader().getResource(saml2ConfigurationProperties.keystore.location!!))
        setKeyStorePassword(saml2ConfigurationProperties.keystore.password)
        setDefaultX509Alias(saml2ConfigurationProperties.keystore.alias)
    }

    @Bean
    @Throws(Exception::class)
    internal fun wss4jSecurityInterceptor(crypto: Crypto) = Wss4jSecurityInterceptor().apply {
        setSecurementActions("Timestamp SAMLTokenSigned")
        setSecurementSamlCallbackHandler(SAML2CallbackHandler(crypto, saml2ConfigurationProperties.keystore.alias).apply {
            issuerCrypto = crypto
            setStatement(AbstractSAMLCallbackHandler.Statement.AUTHN)
            setIssuer(saml2ConfigurationProperties.issuer)
            issuerName = saml2ConfigurationProperties.keystore.alias
            issuerPassword = saml2ConfigurationProperties.keystore.privateKeyPassword

        })
        setSecurementPassword(saml2ConfigurationProperties.keystore.privateKeyPassword)
        setSecurementUsername(saml2ConfigurationProperties.keystore.alias)
        setSecurementTimeToLive(saml2ConfigurationProperties.ttlSeconds)
        setSecurementSignatureCrypto(crypto)
        setSecurementSignatureKeyIdentifier("DirectReference")
        setSecurementSignatureParts("{Element}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Timestamp;")
        setTimestampStrict(true)
    }
}
