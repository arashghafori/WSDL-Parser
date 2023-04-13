package com.agh.parser.helper;

import com.agh.parser.model.CreateSOAPRequestDTO;
import com.agh.parser.model.SoapBasicAuthenticationDTO;
import com.predic8.wsdl.Definitions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SoapRequestTemplateGeneratorTest {

    private WSDLParser wsdlParser;
    private SoapRequestTemplateGenerator soapRequestTemplateGenerator;
    private XMLUtil xmlUtil;

    @BeforeEach
    void injectObjects() {
        wsdlParser = WSDLParser.getInstance();
        soapRequestTemplateGenerator = SoapRequestTemplateGenerator.getInstance();
        xmlUtil = XMLUtil.getInstance();
    }

    @ParameterizedTest
    @CsvSource(
            { "http://localhost:8080/soapws/articles.wsdl, ArticlesPortSoap11, ArticlesPort, addArticle" ,
                    "http://localhost:8080/soapws/articles.wsdl, ArticlesPortSoap11, ArticlesPort, getArticleById" }
    )
    @DisplayName("generate_SOAP_request_template")
    public void createSOAPRequestTemplate(String url, String bindingName, String portTypeName, String operationName) {
        Definitions definition = wsdlParser.getDefinitions(url, SoapBasicAuthenticationDTO.builder().username("username").password("password").build());
        assertNotNull(definition);
        String soapRequestTemplate = soapRequestTemplateGenerator.generate(CreateSOAPRequestDTO.builder()
                .wsdlUrl(url)
                .definition(definition)
                .bindingName(bindingName)
                .portTypeName(portTypeName)
                .operationName(operationName)
                .build());
        assertNotNull(soapRequestTemplate);
    }

    @ParameterizedTest
    @CsvSource(
            { "http://localhost:8080/soapws/articles.wsdl, ArticlesPortSoap11, ArticlesPort, addArticle" ,
                    "http://localhost:8080/soapws/articles.wsdl, ArticlesPortSoap11, ArticlesPort, getArticleById" }
    )
    @DisplayName("remove_additional_XML_namespaces")
    void removeXmlNamespace(String url, String bindingName, String portTypeName, String operationName) {
        Definitions definition = wsdlParser.getDefinitions(url, SoapBasicAuthenticationDTO.builder().username("username").password("password").build());
        assertNotNull(definition);
        String soapRequestTemplate = soapRequestTemplateGenerator.generate(CreateSOAPRequestDTO.builder()
                .wsdlUrl(url)
                .definition(definition)
                .bindingName(bindingName)
                .portTypeName(portTypeName)
                .operationName(operationName)
                .build());
        assertNotNull(soapRequestTemplate);
        String cleanXmlRequest = xmlUtil.removeXmlNamespace(soapRequestTemplate);
        assertNotNull(cleanXmlRequest);
    }
}