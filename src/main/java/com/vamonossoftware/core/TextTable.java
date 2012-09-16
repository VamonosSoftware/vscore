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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 * A simple way to convert a table of objects to a text table representation.
 *
 * @author Paul Rule
 * @since 0.1
 */
public class TextTable {

    public enum ALIGN {
        LEFT, RIGHT, CENTER
    };
    private List<Object[]> data = new ArrayList();
    private Map<Integer, Integer> maxWidths = new HashMap();
    private ALIGN[] alignment;
    private String leftPad="";

    /**
     *
     * @param leftPad String to lead each row
     * @param alignment Alignment for each column
     */
    public TextTable(String leftPad, ALIGN... alignment) {
        this.alignment = alignment;
        this.leftPad = leftPad;
    }

    public void clear() {
        data = new ArrayList<Object[]>();
    }

    /**
     * Add a row of data
     */
    public TextTable add(Object... objs) {
        data.add(objs);
        int col = 0;
        for (Object object : objs) {
            String val = object.toString();
            if (!maxWidths.containsKey(col) || val.length() > maxWidths.get(col)) {
                maxWidths.put(col, val.length());
            }
            col++;
        }
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object[] objects : data) {
            sb.append(leftPad);
            for (int i = 0; i < objects.length; i++) {
                Object object = objects[i];
                if (alignment.length > i) {
                    switch (alignment[i]) {
                        case CENTER:
                            sb.append(StringUtils.center(object.toString(), maxWidths.get(i)));
                            break;
                        case RIGHT:
                            sb.append(StringUtils.leftPad(object.toString(), maxWidths.get(i)));
                            break;
                        default:
                            sb.append(StringUtils.rightPad(object.toString(), maxWidths.get(i)));
                            break;
                    }
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
