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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Paul Rule
 * @since 0.1
 */
public class Validator {
    private List<ValidationRule> rules = new ArrayList();
    private boolean failfast = false;

    public Validator() {
        this.failfast = false;
    }

    public Validator(boolean failfast, ValidationRule... rules) {
        this.failfast = failfast;
        this.rules.addAll(Arrays.asList(rules));
    }

    public Errors validate(Object object) {
        try {
            Errors errors = new Errors();
            for (ValidationRule rule : rules) {
                Errors errs = rule.validate(object);
                errors.add(errs);
                if (failfast && !errors.isEmpty()) {
                    return errors;
                }
            }
            return errors;
        } catch (ValidationRuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(ValidationRule rule) {
        rules.add(rule);
    }
}
