# WSDL Parser for SOAP API

This is a Java library that provides a WSDL parser for SOAP APIs. It helps you to parse WSDL files(even if they are protected with basic authentication) and then, extract information about the available SOAP operations, input and output parameters in XML and JSON format, generate SOAP request templates and more.
This library is developed based on [predic8](https://github.com/membrane/soa-model.git) library. I customised a fork from the [predic8](https://github.com/membrane/soa-model.git) library in my git repository([here](https://github.com/arashghafori/soa-model.git)) and used it in my WSDL Parser library.

## Getting Started
To use the library, first, you need to get my customized [predic8](https://github.com/arashghafori/soa-model.git) library and build it with your build tool(Maven/Gradle). Here is the example for Maven:
```text
mvn clean install
```
After that, clone WSDL Parser and build it with your build tool(Maven/Gradle). Finally, you can add the dependency to your Pom file like this: 
```xml
<dependency>
    <groupId>com.AGH</groupId>
    <artifactId>WSDLParser</artifactId>
    <version>1.0.0</version>
</dependency>
```
## Examples

Here are some examples of how you can use the library:

### Get full information from a WSDL file/URL

```java
WSDLParser.getInstance().getFullInfoOfWSDL(SoapRequestDTO.builder()
        .wsdlUrl(url)
        .build());
```

### Get full information from a WSDL URL that is protected with basic authentication: 

```java
WSDLParser.getInstance().getFullInfoOfWSDL(SoapRequestDTO.builder()
        .wsdlUrl(url)
        .basicAuthenticationDTO(SoapBasicAuthenticationDTO.builder().username(username).password(password).build())
        .build());
```

### Get definition of WSDL file/URL

```java
WSDLParser.getInstance().getDefinitions(url, null);
```
or
```java
WSDLParser.getInstance().getDefinitions(url, SoapBasicAuthenticationDTO.builder().username("username").password("password").build());
```

### Generate SOAP request template in XML format:

```java
Definitions definition = WSDLParser.getInstance().getDefinitions(url, SoapBasicAuthenticationDTO.builder().username("username").password("password").build());
String soapRequestTemplate = SoapRequestTemplateGenerator.getInstance().generate(CreateSOAPRequestDTO.builder()
        .wsdlUrl(url)
        .definition(definition)
        .bindingName(bindingName)
        .portTypeName(portTypeName)
        .operationName(operationName)
        .build());
```

You can also remove the additional namespaces from this request template like this:
```java
Definitions definition = WSDLParser.getInstance().getDefinitions(url, SoapBasicAuthenticationDTO.builder().username("username").password("password").build());
String soapRequestTemplate = SoapRequestTemplateGenerator.getInstance().generate(CreateSOAPRequestDTO.builder()
        .wsdlUrl(url)
        .definition(definition)
        .bindingName(bindingName)
        .portTypeName(portTypeName)
        .operationName(operationName)
        .build());
String cleanXmlRequest = XMLUtil.getInstance().removeXmlNamespace(soapRequestTemplate);
```


## Contributing
If you would like to contribute to the development of this library, please feel free to submit a pull request. I welcome contributions of all kinds, including bug fixes, new features, and improvements to the documentation.