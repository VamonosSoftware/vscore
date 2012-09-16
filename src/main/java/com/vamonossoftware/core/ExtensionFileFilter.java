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

import java.io.File;
import java.io.FileFilter;

/**
 * A case insensitive file filter, base on file extension.
 *
 * @author Paul Rule
 * @since 0.1
 */
public class ExtensionFileFilter implements FileFilter {
    private String[] extensions;

    public ExtensionFileFilter(String... extensions) {
        this.extensions = extensions;
    }

    @Override
    public boolean accept(File file) {
        for(String ext : extensions) {
            if(file.getName().toLowerCase().endsWith('.'+ext.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
