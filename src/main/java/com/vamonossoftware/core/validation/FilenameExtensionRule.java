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

import java.io.File;
import java.text.MessageFormat;

/**
 * @author Paul Rule
 * @since 0.1
 */
public class FilenameExtensionRule implements ValidationRule<File> {
    private String extension;
    private boolean caseSensitive;

    private static final String format = "File {0} does not end with extension {1}";
    
    public FilenameExtensionRule(String extension, boolean caseSensitive) {
        this.extension = extension;
        this.caseSensitive = caseSensitive;
    }

    public Errors validate(File input) {
        Errors errors = new Errors();
        String ext = extension;
        String filename = input.getName();

        if(!caseSensitive) {
            ext = ext.toUpperCase();
            filename = filename.toUpperCase();
        }

        if(!filename.endsWith(ext)) {
            errors.add(new ValidationError(this.getClass().getSimpleName(),MessageFormat.format(format,input.getName(),extension)));
        }
        return errors;
    }


}
