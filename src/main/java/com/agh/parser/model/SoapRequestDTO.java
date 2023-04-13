package com.agh.parser.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SoapRequestDTO {
    private String wsdlUrl;
    private SoapBasicAuthenticationDTO basicAuthenticationDTO;
}
