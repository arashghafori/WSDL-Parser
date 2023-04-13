package com.agh.parser.model;

import com.predic8.wsdl.Definitions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSOAPRequestDTO {
    private Definitions definition;
    private String wsdlUrl;
    private String portTypeName;
    private String operationName;
    private String bindingName;
}
