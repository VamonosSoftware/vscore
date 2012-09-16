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

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * Convenience class to merge a FreeMarker template and a Map model.
 * @author Paul Rule
 * @since 0.1
 */
public class FreeMarkerTemplater implements Templater {

    private Configuration cfg = new Configuration();

    public FreeMarkerTemplater(File templateDirectory) {
        try {
            cfg.setDirectoryForTemplateLoading(templateDirectory);
        } catch (IOException ex) {
            throw new RuntimeException("Invalid template directory " + templateDirectory.getAbsolutePath(), ex);
        }
    }

    @Override
    public String merge(String templateName, Map data) {
        try {
            Template template = cfg.getTemplate(templateName);
            final StringWriter writer = new StringWriter();
            template.process(data, writer);
            return writer.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Could not process template "+templateName,ex);
        }

    }
}
