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

import java.util.Collection;

/**
 * @author Paul Rule
 * @since 0.1
 */
public class CollectionUtil {

    /**
     * Null safe access to the first object in a collection
     */
    public static Object first(Collection items) {
        if (items != null && items.size() > 0) {
            return items.iterator().next();
        }
        return null;
    }

    /**
     * Simple string representation of an array of objects
     */
    public static String toString(Object[] items) {
        StringBuilder sb = new StringBuilder("[");
        if (items != null) {
            for (int i = 0; i < items.length; i++) {
                sb.append(items[i]);
                if (i < items.length - 1) {
                    sb.append(",");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Null safe summation of the sizes of all of the given collections
     */
    public static int size(Collection... collections) {
        int size = 0;
        if (collections != null) {
            for (Collection collection : collections) {
                if(collection!=null) size+=collection.size();
            }
        }
        return size;
    }

    /**
     * Returns true if all of the collections given are empty
     */
    public static boolean empty(Collection... collections) {
        return size(collections)==0;
    }
    
}
