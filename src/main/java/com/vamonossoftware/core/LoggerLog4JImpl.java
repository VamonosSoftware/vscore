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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 * @author Paul Rule
 * @since 0.1
 */
public class LoggerLog4JImpl {
    private static String separator = " ";

    public static void log(Logger logger, Priority priority, Object... params) {
        if(logger.isEnabledFor(priority)) {
            logger.log(priority, generate(params));
        }
    }

    private static String generate(Object... params) {
        StringBuilder sb = new StringBuilder();
        if(params!=null) {
            for (Object object : params) {
                if(object!=null) {
                    sb.append(object);
                }
                sb.append(separator);
            }
        }
        return sb.toString();
    }
}
