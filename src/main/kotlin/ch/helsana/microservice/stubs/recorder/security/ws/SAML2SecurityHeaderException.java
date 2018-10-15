package ch.helsana.microservice.stubs.recorder.security.ws;

import org.springframework.ws.soap.security.WsSecuritySecurementException;

public class SAML2SecurityHeaderException extends WsSecuritySecurementException {
    public SAML2SecurityHeaderException(String message, Exception e) {
        super(message, e);
    }
}
