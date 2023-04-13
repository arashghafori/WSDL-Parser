package com.agh.parser.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SoapOperationDTO {
    private String name;
    private String inputName;
    private String outputName;
    private JSONObject inputElement;
    private JSONObject outputElement;
}
