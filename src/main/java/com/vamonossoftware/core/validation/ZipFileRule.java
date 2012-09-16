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

import com.vamonossoftware.core.ZipUtil;
import java.io.File;
import java.text.MessageFormat;

/**
 * @author Paul Rule
 * @since 0.1
 */
public class ZipFileRule implements ValidationRule<File> {
    private static final String format1 = "Zip file {0} contained {1} files - expected {3} of {2}";
    private static final String format2 = "Zip file {0} contained is not a valid zip file";
    private int max;
    private int min;

    public ZipFileRule(int min, int max) {
        this.max = max;
        this.min = min;
    }

    public Errors validate(File input) {
        final Errors errors = new Errors();
        if(!ZipUtil.isValid(input)) {
            errors.add(new ValidationError(this.getClass().getSimpleName(), MessageFormat.format(format2, input.getName())));
            // can't continue processing a zip file that isn't a valid zip
            return errors;
        }
        int count = ZipUtil.countFiles(input);
        if(count>max) {
            errors.add(new ValidationError(this.getClass().getSimpleName(), MessageFormat.format(format1, input.getName(), count, max, "maximum")));
        }
        if(count<min) {
            errors.add(new ValidationError(this.getClass().getSimpleName(), MessageFormat.format(format1, input.getName(), count, min, "minimum")));
        }
        return errors;
    }

}
