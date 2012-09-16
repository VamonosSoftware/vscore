/*
 * Copyright 2010 Vamonos Software
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.vamonossoftware.core;

import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

/**
 * @author Paul Rule
 * @since 0.1
 */
public class XmlUtil {

    /**
     * Perform an xsl transformation
     */
    public static void transform(Source xsl, Source xml, Result result) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        Templates template = factory.newTemplates(xsl);
        Transformer transformer = template.newTransformer();
        transformer.transform(xml, result);
    }

    /**
     * Perform an xsl transformation
     */
    public static void transform(InputStream xsl, InputStream xml, Writer result) throws Exception {
        transform(new StreamSource(xsl), new StreamSource(xml), new StreamResult(result));
    }

    /**
     * Perform an xsl transformation, returning the result as a String.
     * Example use:
     * <pre>
     *      String html = XmlUtil.transform(this.getClass().getResourceAsStream("/sample.xslt"), new URL(sampleurl).openStream());
     * </pre>
     */
    public static String transform(InputStream xsl, InputStream xml) {
        StringWriter sw = new StringWriter();
        try {
            transform(xsl, xml, sw);
        } catch (Exception ex) {
            throw new RuntimeException("Could not perform transformation", ex);
        }
        return sw.toString();
    }

    /**
     * Perform a schema validation
     */
    public static void validate(Source schema, Source document) throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema sch = factory.newSchema(schema);
        Validator validator = sch.newValidator();
        validator.validate(document);
    }

    /**
     * Perform a schema validation
     */
    public static void validate(InputStream schema, InputStream document) throws Exception {
        validate(new StreamSource(schema), new StreamSource(document));
    }
}
