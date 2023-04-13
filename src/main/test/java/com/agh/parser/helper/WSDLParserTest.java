package com.agh.parser.helper;

import com.agh.parser.model.SoapBasicAuthenticationDTO;
import com.agh.parser.model.SoapRequestDTO;
import com.agh.parser.model.SoapResponseDTO;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


public class WSDLParserTest {
    private WSDLParser wsdlParser;

    @BeforeEach
    void injectObjects() {
        wsdlParser = WSDLParser.getInstance();
    }

    @ParameterizedTest
    @CsvSource({ "http://localhost:8080/soapws/articles.wsdl, username, password" })
    @DisplayName("get_full_info_with_basic_authentication")
    public void getFullInfoOfWSDL_1(String url, String username, String password) {
        SoapResponseDTO soapResponseDTO = wsdlParser.getFullInfoOfWSDL(SoapRequestDTO.builder()
                .wsdlUrl(url)
                .basicAuthenticationDTO(SoapBasicAuthenticationDTO.builder().username(username).password(password).build())
                .build());
        assertNotNull(soapResponseDTO);
    }

    @ParameterizedTest
    @ValueSource(strings = "http://localhost:8080/soapws/articles.wsdl")
    @DisplayName("get_full_info_without_basic_authentication")
    public void getFullInfoOfWSDL_2(String url) {
        SoapResponseDTO soapResponseDTO = wsdlParser.getFullInfoOfWSDL(SoapRequestDTO.builder()
                .wsdlUrl(url)
                .build());
        assertNotNull(soapResponseDTO);
    }
}