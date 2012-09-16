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
import java.io.IOException;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;

/**
 * A simple archive service that MOVES a given file into the configured archive directory,
 * categorizing by (a configurable depth of) YEAR/MONTH/DAY/HOUR/COUNT where COUNT
 * is the next highest available integer in that directory.
 *
 * The COUNT subdirectory is used to avoid filename clashes.
 * 
 * @author Paul Rule
 * @since 0.1
 */
public class ArchiveServiceSimpleImpl implements ArchiveService {

    private File archiveDir;
    private boolean move = false;
    private String pathPattern = "yyyy/MM";

    public enum Depth {
        YEAR, MONTH, DAY, HOUR
    };

    /**
     * @param archiveDir Base directory to use for the archive storage
     * @param move if set to true, files will be MOVED to the archive, otherwise they will be COPIED
     * @param depth indicates the format of the archive directory - for example Depth.DAY will use subdirectories down to the day
     */
    public ArchiveServiceSimpleImpl(File archiveDir, boolean move, Depth depth) {
        this.archiveDir = archiveDir;
        this.archiveDir.mkdirs();
        this.move = move;

        switch (depth) {
            case YEAR:
                pathPattern = "yyyy";
                break;
            case MONTH:
                pathPattern = "yyyy/MM";
                break;
            case DAY:
                pathPattern = "yyyy/MM/dd";
                break;
            case HOUR:
                pathPattern = "yyyy/MM/dd/HH";
                break;
        }
    }

    /**
     * Puts the given file into the archive directory, using a MOVE or COPY based on the constructor parameter 'move'.
     */
    public synchronized File archive(File file) {
        File basePath = new File(archiveDir, FastDateFormat.getInstance(pathPattern).format(new DateTime().toDate()));

        int count = 1;
        File path = null;
        do {
            path = new File(basePath, Integer.toString(count));
            count++;
        } while (path.exists());

        path.mkdirs();
        File destFile = new File(path, file.getName());
        try {
            if (move) {
                FileUtils.moveFile(file, destFile);
            } else {
                FileUtils.copyFile(file, destFile);
            }
            return destFile;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
