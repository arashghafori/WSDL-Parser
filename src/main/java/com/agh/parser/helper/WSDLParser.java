package com.agh.parser.helper;

import com.agh.parser.model.*;
import com.predic8.wsdl.*;
import com.predic8.xml.util.CustomBasicAuthenticationResolver;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class WSDLParser {
    private static class WSDLParserHelper {
        static WSDLParser Instance = new WSDLParser();
    }

    public static WSDLParser getInstance() {
        return WSDLParserHelper.Instance;
    }

    public Definitions getDefinitions(String filePath, SoapBasicAuthenticationDTO basicAuthenticationInfo) {
        com.predic8.wsdl.WSDLParser parser = new com.predic8.wsdl.WSDLParser();
        if (basicAuthenticationInfo != null){
            CustomBasicAuthenticationResolver bs = new CustomBasicAuthenticationResolver();
            bs.setUsername(basicAuthenticationInfo.getUsername());
            bs.setPassword(basicAuthenticationInfo.getPassword());
            parser.setResourceResolver(bs);
        }
        return parser.parse(filePath);
    }

    public SoapResponseDTO getFullInfoOfWSDL(SoapRequestDTO soapRequestDTO) {
        Definitions definitions = getDefinitions(soapRequestDTO.getWsdlUrl(), soapRequestDTO.getBasicAuthenticationDTO());
        return getWSDLDetails(definitions);
    }

    public SoapResponseDTO getWSDLDetails(Definitions defs) {
        List<SoapOperationDTO> soapOperationDTOS = new ArrayList<>();

        Service service = defs.getServices().get(0);
        service.getPorts().get(0).getBinding().getPortType().getName();
        Port port = service.getPorts().get(0);

        for (Operation operation : port.getBinding().getPortType().getOperations()) {
            JSONObject inputElement;
            try {
                inputElement = (JSONObject) new JSONParser().parse(defs.getInputElementForOperation(port.getBinding().getType().getLocalPart(), operation.getName()).getAsJson());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JSONObject outputElement;
            try {
                outputElement = (JSONObject) new JSONParser().parse(defs.getOutputElementForOperation(port.getBinding().getType().getLocalPart(), operation.getName()).getAsJson());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            soapOperationDTOS.add(
                    SoapOperationDTO.builder()
                            .name(operation.getName())
                            .inputName(operation.getInput().getName())
                            .outputName(operation.getOutput().getName())
                            .inputElement(inputElement)
                            .outputElement(outputElement)
                            .build()
            );
        }

        return SoapResponseDTO.builder()
                .targetNamespace(defs.getTargetNamespace())
                .bindingName(port.getBinding().getName())
                .portTypeName(port.getBinding().getPortType().getName())
                .soapOperationDTO(soapOperationDTOS)
                .build();
    }
}
