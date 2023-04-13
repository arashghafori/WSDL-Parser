package com.agh.parser.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SoapResponseDTO {
    private String targetNamespace;
    private String bindingName;
    private String portTypeName;
    private List<SoapOperationDTO> soapOperationDTO;
}
