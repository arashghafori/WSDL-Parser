package com.agh.parser.helper;

public class XMLUtil {

    private static class XMLUtilHelper {
        static XMLUtil Instance = new XMLUtil();
    }

    public static XMLUtil getInstance() {
        return XMLUtil.XMLUtilHelper.Instance;
    }

    public String removeXmlNamespace(String xmlString) {
        return xmlString.replaceAll("(<\\?[^<]*\\?>)?", "")     /* remove preamble */
                .replaceAll("xmlns.*?(\"|\').*?(\"|\')", "")    /* remove xmlns declaration */
                .replaceAll("(<)(\\w+:)(.*?>)", "$1$3")         /* remove opening tag prefix */
                .replaceAll("(?s)<!--.*?-->", "")               /* remove comments */
                .replaceAll("\\s+", " ")
                .replaceAll("(</)(\\w+:)(.*?>)", "$1$3");       /* remove closing tags prefix */
    }
}
