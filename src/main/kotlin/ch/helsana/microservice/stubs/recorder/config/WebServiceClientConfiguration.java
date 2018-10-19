package ch.helsana.microservice.stubs.recorder.config;

import ch.helsana.microservice.stubs.recorder.client.ApiBridgeClient;
import ch.helsana.microservice.stubs.recorder.security.ws.SAML2ConfigurationProperties;
import org.apache.wss4j.common.crypto.Crypto;
import org.apache.wss4j.dom.common.SAML2CallbackHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties({ApiBridgeProperties.class, SAML2ConfigurationProperties.class})
public class WebServiceClientConfiguration {

    private final SAML2ConfigurationProperties saml2ConfigurationProperties;
    private final ApiBridgeProperties apiBridgeProperties;

    public WebServiceClientConfiguration(SAML2ConfigurationProperties saml2ConfigurationProperties, ApiBridgeProperties apiBridgeProperties) {
        this.saml2ConfigurationProperties = saml2ConfigurationProperties;
        this.apiBridgeProperties = apiBridgeProperties;
    }

    @Bean
    Jaxb2Marshaller apiBridgeMarshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPaths(apiBridgeProperties.getContextPaths().toArray(new String[0]));
        return jaxb2Marshaller;
    }

    @Bean
    ApiBridgeClient apiBridgeClient(WebServiceMessageFactory webServiceMessageFactory, Jaxb2Marshaller apiBridgeMarshaller, Wss4jSecurityInterceptor wss4jSecurityInterceptor) {
        ApiBridgeClient customerClient = new ApiBridgeClient(webServiceMessageFactory, apiBridgeProperties.baseUri, wss4jSecurityInterceptor);
        customerClient.setMarshaller(apiBridgeMarshaller);
        customerClient.setUnmarshaller(apiBridgeMarshaller);
        return customerClient;
    }

    @Bean
    SaajSoapMessageFactory saajSoapMessageFactory() {
        SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory();
        messageFactory.setSoapVersion(SoapVersion.SOAP_11);
        return messageFactory;
    }

    @Bean
    CryptoFactoryBean cryptoFactory() throws IOException {
        CryptoFactoryBean cryptoFactory = new CryptoFactoryBean();
        cryptoFactory.setKeyStoreType(saml2ConfigurationProperties.getKeystore().getType());
        cryptoFactory.setKeyStoreLocation(new DefaultResourceLoader().getResource(saml2ConfigurationProperties.getKeystore().getLocation()));
        cryptoFactory.setKeyStorePassword(saml2ConfigurationProperties.getKeystore().getPassword());
        cryptoFactory.setDefaultX509Alias(saml2ConfigurationProperties.getKeystore().getAlias());
        return cryptoFactory;
    }

    @Bean
    Wss4jSecurityInterceptor wss4jSecurityInterceptor(Crypto crypto) throws Exception {
        SAML2CallbackHandler samlCallbackHandler = new SAML2CallbackHandler(crypto, saml2ConfigurationProperties.getKeystore().getAlias());
        samlCallbackHandler.setIssuerCrypto(crypto);
        samlCallbackHandler.setStatement(SAML2CallbackHandler.Statement.AUTHN);
        samlCallbackHandler.setIssuer(saml2ConfigurationProperties.getIssuer());
        samlCallbackHandler.setIssuerName(saml2ConfigurationProperties.getKeystore().getAlias());
        samlCallbackHandler.setIssuerPassword(saml2ConfigurationProperties.getKeystore().getPrivateKeyPassword());

        Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();
        securityInterceptor.setSecurementActions("Timestamp SAMLTokenSigned");
        securityInterceptor.setSecurementSamlCallbackHandler(samlCallbackHandler);
        securityInterceptor.setSecurementPassword(saml2ConfigurationProperties.getKeystore().getPrivateKeyPassword());
        securityInterceptor.setSecurementUsername(saml2ConfigurationProperties.getKeystore().getAlias());
        securityInterceptor.setSecurementTimeToLive(saml2ConfigurationProperties.getTtlSeconds());
        securityInterceptor.setSecurementSignatureCrypto(crypto);
        securityInterceptor.setSecurementSignatureKeyIdentifier("DirectReference");
        securityInterceptor.setSecurementSignatureParts("{Element}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Timestamp;");
        securityInterceptor.setTimestampStrict(true);
        return securityInterceptor;
    }
}
