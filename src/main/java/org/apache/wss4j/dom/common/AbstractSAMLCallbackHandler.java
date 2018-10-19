/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.wss4j.dom.common;

import org.apache.wss4j.common.crypto.Crypto;
import org.apache.wss4j.common.saml.SAMLCallback;
import org.apache.wss4j.common.saml.bean.*;
import org.apache.wss4j.common.saml.bean.KeyInfoBean.CERT_IDENTIFIER;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.message.WSSecEncryptedKey;
import org.joda.time.DateTime;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.security.auth.callback.CallbackHandler;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A base implementation of a Callback Handler for a SAML assertion. By default it creates an
 * authentication assertion.
 */
public abstract class AbstractSAMLCallbackHandler implements CallbackHandler {

    public enum Statement {
        AUTHN, ATTR, AUTHZ
    }

    protected String subjectName;
    protected String subjectQualifier;
    protected String confirmationMethod;
    protected X509Certificate[] certs;
    protected Statement statement = Statement.AUTHN;
    protected CERT_IDENTIFIER certIdentifier = CERT_IDENTIFIER.X509_CERT;
    protected byte[] ephemeralKey;
    protected String issuer;
    protected String issuerFormat;
    protected String subjectNameIDFormat;
    protected String subjectLocalityIpAddress;
    protected String subjectLocalityDnsAddress;
    protected DateTime sessionNotOnOrAfter;
    protected DateTime authenticationInstant;
    protected String resource;
    protected List<Object> customAttributeValues;
    protected ConditionsBean conditions;
    protected SubjectConfirmationDataBean subjectConfirmationData;
    private Crypto issuerCrypto;
    private String issuerName;
    private String issuerPassword;
    private Element assertionAdviceElement;
    private Element keyInfoElement;

    public void setSubjectConfirmationData(SubjectConfirmationDataBean subjectConfirmationData) {
        this.subjectConfirmationData = subjectConfirmationData;
    }

    public void setConditions(ConditionsBean conditionsBean) {
        this.conditions = conditionsBean;
    }

    public void setConfirmationMethod(String confMethod) {
        confirmationMethod = confMethod;
    }

    public void setSessionNotOnOrAfter(DateTime sessionNotOnOrAfter) {
        this.sessionNotOnOrAfter = sessionNotOnOrAfter;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public void setCertIdentifier(CERT_IDENTIFIER certIdentifier) {
        this.certIdentifier = certIdentifier;
    }

    public void setCerts(X509Certificate[] certs) {
        this.certs = certs;
    }

    public byte[] getEphemeralKey() {
        return ephemeralKey;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public void setIssuerFormat(String issuerFormat) {
        this.issuerFormat = issuerFormat;
    }

    public void setSubjectNameIDFormat(String subjectNameIDFormat) {
        this.subjectNameIDFormat = subjectNameIDFormat;
    }

    public void setSubjectLocality(String ipAddress, String dnsAddress) {
        this.subjectLocalityIpAddress = ipAddress;
        this.subjectLocalityDnsAddress = dnsAddress;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public void setCustomAttributeValues(List<Object> customAttributeValues) {
        this.customAttributeValues = customAttributeValues;
    }

    public DateTime getAuthenticationInstant() {
        return authenticationInstant;
    }

    public void setAuthenticationInstant(DateTime authenticationInstant) {
        this.authenticationInstant = authenticationInstant;
    }

    /**
     * Note that the SubjectBean parameter should be null for SAML2.0
     */
    protected void createAndSetStatement(SubjectBean subjectBean, SAMLCallback callback) {
        if (statement == Statement.AUTHN) {
            AuthenticationStatementBean authBean = new AuthenticationStatementBean();
            if (subjectBean != null) {
                authBean.setSubject(subjectBean);
            }
            if (subjectLocalityIpAddress != null || subjectLocalityDnsAddress != null) {
                SubjectLocalityBean subjectLocality = new SubjectLocalityBean();
                subjectLocality.setIpAddress(subjectLocalityIpAddress);
                subjectLocality.setDnsAddress(subjectLocalityDnsAddress);
                authBean.setSubjectLocality(subjectLocality);
            }
            authBean.setAuthenticationMethod("Password");
            authBean.setAuthenticationInstant(authenticationInstant);
            authBean.setSessionNotOnOrAfter(sessionNotOnOrAfter);
            callback.setAuthenticationStatementData(Collections.singletonList(authBean));
        } else if (statement == Statement.ATTR) {
            AttributeStatementBean attrBean = new AttributeStatementBean();
            AttributeBean attributeBean = new AttributeBean();
            if (subjectBean != null) {
                attrBean.setSubject(subjectBean);
                attributeBean.setSimpleName("role");
                attributeBean.setQualifiedName("http://custom-ns");
            } else {
                attributeBean.setQualifiedName("role");
            }
            if (customAttributeValues != null) {
                attributeBean.setAttributeValues(customAttributeValues);
            } else {
                List<Object> attributes = new ArrayList<>();
                attributes.add("user");
                attributeBean.setAttributeValues(attributes);
            }
            attrBean.setSamlAttributes(Collections.singletonList(attributeBean));
            callback.setAttributeStatementData(Collections.singletonList(attrBean));
        } else {
            AuthDecisionStatementBean authzBean = new AuthDecisionStatementBean();
            if (subjectBean != null) {
                authzBean.setSubject(subjectBean);
            }
            ActionBean actionBean = new ActionBean();
            actionBean.setContents("Read");
            authzBean.setActions(Collections.singletonList(actionBean));
            authzBean.setResource("endpoint");
            authzBean.setDecision(AuthDecisionStatementBean.Decision.PERMIT);
            authzBean.setResource(resource);
            callback.setAuthDecisionStatementData(Collections.singletonList(authzBean));
        }
    }

    protected KeyInfoBean createKeyInfo() throws Exception {
        KeyInfoBean keyInfo = new KeyInfoBean();
        if (keyInfoElement != null) {
            keyInfo.setElement(keyInfoElement);
        } else if (statement == Statement.AUTHN) {
            keyInfo.setCertificate(certs[0]);
            keyInfo.setCertIdentifer(certIdentifier);
        } else if (statement == Statement.ATTR) {
            // Build a new Document
            DocumentBuilderFactory docBuilderFactory =
                DocumentBuilderFactory.newInstance();
            docBuilderFactory.setNamespaceAware(true);
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Create an Encrypted Key
            WSSecEncryptedKey encrKey = new WSSecEncryptedKey(doc);
            encrKey.setKeyIdentifierType(WSConstants.ISSUER_SERIAL);
            encrKey.setUseThisCert(certs[0]);
            encrKey.prepare(null);
            ephemeralKey = encrKey.getEphemeralKey();
            Element encryptedKeyElement = encrKey.getEncryptedKeyElement();

            // Append the EncryptedKey to a KeyInfo element
            Element kiElement =
                doc.createElementNS(
                    WSConstants.SIG_NS, WSConstants.SIG_PREFIX + ":" + WSConstants.KEYINFO_LN
                );
            kiElement.setAttributeNS(
                WSConstants.XMLNS_NS, "xmlns:" + WSConstants.SIG_PREFIX, WSConstants.SIG_NS
            );
            kiElement.appendChild(encryptedKeyElement);

            keyInfo.setElement(kiElement);
        }
        return keyInfo;
    }

    public Crypto getIssuerCrypto() {
        return issuerCrypto;
    }

    public void setIssuerCrypto(Crypto issuerCrypto) {
        this.issuerCrypto = issuerCrypto;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public String getIssuerPassword() {
        return issuerPassword;
    }

    public void setIssuerPassword(String issuerPassword) {
        this.issuerPassword = issuerPassword;
    }

    public Element getAssertionAdviceElement() {
        return assertionAdviceElement;
    }

    public void setAssertionAdviceElement(Element assertionAdviceElement) {
        this.assertionAdviceElement = assertionAdviceElement;
    }

    public Element getKeyInfoElement() {
        return keyInfoElement;
    }

    public void setKeyInfoElement(Element keyInfoElement) {
        this.keyInfoElement = keyInfoElement;
    }
}
