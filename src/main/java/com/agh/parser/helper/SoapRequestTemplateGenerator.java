package com.agh.parser.helper;

import com.agh.parser.model.CreateSOAPRequestDTO;
import com.predic8.wstool.creator.RequestTemplateCreator;
import com.predic8.wstool.creator.SOARequestCreator;
import groovy.xml.MarkupBuilder;

import java.io.StringWriter;

public class SoapRequestTemplateGenerator {

    private static class RequestTemplateGeneratorHelper {
        static SoapRequestTemplateGenerator Instance = new SoapRequestTemplateGenerator();
    }

    public static SoapRequestTemplateGenerator getInstance() {
        return SoapRequestTemplateGenerator.RequestTemplateGeneratorHelper.Instance;
    }

    public String generate(CreateSOAPRequestDTO createSOAPRequestDTO) {
        StringWriter writer = new StringWriter();
        SOARequestCreator creator = new SOARequestCreator(createSOAPRequestDTO.getDefinition(), new RequestTemplateCreator(), new MarkupBuilder(writer));
        creator.createRequest(createSOAPRequestDTO.getPortTypeName(), createSOAPRequestDTO.getOperationName(), createSOAPRequestDTO.getBindingName());
        return writer.toString();
    }
}
