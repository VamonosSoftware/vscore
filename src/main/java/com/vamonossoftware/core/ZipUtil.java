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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.commons.io.IOUtils;

/**
 * @author Paul Rule
 * @since 0.1
 */
public class ZipUtil {

    public static int unzip(File zip, File dest) {
        try {
            ZipFile zf = new ZipFile(zip);
            int count = 0;
            for (Enumeration entries = zf.entries(); entries.hasMoreElements();) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                File destfile = new File(dest,entry.getName());
                if(entry.isDirectory()) {
                    destfile.mkdirs();
                } else {
                    IOUtils.copy(zf.getInputStream(entry),new FileOutputStream(destfile));
                }
            }
            return count;
        } catch (IOException e) {
            return -1;
        }
    }

    public static int countFiles(File zip) {
        try {
            ZipFile zf = new ZipFile(zip);
            int count = 0;
            for (Enumeration entries = zf.entries(); entries.hasMoreElements();) {
                final ZipEntry nextElement = (ZipEntry) entries.nextElement();
                if(!nextElement.isDirectory()) {
                    count++;
                }
            }
            return count;
        } catch (IOException e) {
            return -1;
        }
    }

    public static boolean isValid(File zip) {
        return countFiles(zip)>=0;
    }


}
