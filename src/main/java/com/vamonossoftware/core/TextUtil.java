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

import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

/**
 * @author Paul Rule
 * @since 0.1
 */
public class TextUtil {

    private static final String START = "${";
    private static final String END = "}";

    /**
     * Replaces variables in a string with values from a properties object.
     * So, if the string is
     * "Hello ${name}."
     * And the properties contains
     * "name" -> "Mr Ed"
     * Then the result will be
     * "Hello Mr Ed."
     */
    public static String replace(String input, Properties properties) {
        for (Object key : properties.keySet()) {
            input = StringUtils.replace(input, START + key + END, properties.getProperty((String) key));
        }
        return input;
    }

    /**
     * @deprecated Use org.apache.commons.lang.StringEscapeUtils.escapeHtml()
     * @see 
     */
    public static String escapeHtml(String in) {
        String result = in.replaceAll("<", "&lt;");
        result = result.replaceAll(">", "&gt;");
        return result;
    }

    /**
     *
     */
    public static String hyperLink(String in) {
        if (in != null) {
            String[] tokens = in.split("\\s");
            StringBuilder sb = new StringBuilder();
            for (String token : tokens) {
                if (token.toLowerCase().startsWith("www.")) {
                    sb.append("<a target=\"_blank\" href=\"http://" + token + "\">" + token + "</a>");
                } else if (token.toLowerCase().startsWith("http:")) {
                    sb.append("<a target=\"_blank\" href=\"" + token + "\">" + token + "</a>");
                } else {
                    sb.append(token);
                }
                sb.append(" ");
            }
            return sb.toString();
        }
        return in;
    }
}
