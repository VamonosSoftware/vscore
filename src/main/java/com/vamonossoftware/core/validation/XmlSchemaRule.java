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
package com.vamonossoftware.core.validation;

import com.vamonossoftware.core.XmlUtil;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.xml.sax.SAXException;

/**
 * @author Paul Rule
 * @since 0.1
 */
public class XmlSchemaRule implements ValidationRule<File> {

    private URI schema;

    public XmlSchemaRule(File schema) {
        this(schema.toURI());
    }

    public XmlSchemaRule(URI resource) {
        this.schema = resource;
    }

    public XmlSchemaRule(URL resource) {
        try {
            this.schema = resource.toURI();
        } catch (URISyntaxException ex) {
            throw new RuntimeException("Schema not available : " + resource.toString(), ex);
        }
    }

    public Errors validate(File input) {
        Errors errors = new Errors();
        try {
            XmlUtil.validate(schema.toURL().openStream(), new FileInputStream(input));
        } catch (SAXException e) {
            errors.add(new ValidationError(this.getClass().getSimpleName(), "XML Schema validation failed - " + e.getMessage()));
        } catch (Exception ex) {
            throw new ValidationRuntimeException(ex);
        }
        return errors;

    }
}
