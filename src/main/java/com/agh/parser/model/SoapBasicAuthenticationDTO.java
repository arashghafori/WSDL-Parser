package com.agh.parser.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SoapBasicAuthenticationDTO {
    private String username;
    private String password;
}
