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

import java.text.ParseException;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;

/**
 * <p>
 * Many applications need to handle dates, and converting back and forth to strings.
 * It helps to handle and format these dates consistently throughout the application so
 * this class provides one central place to set the application format.
 * </p><p>
 * When the application starts it should configure the desired pattern(s), and then leave it.
 * Changing the pattern is not thread safe - and, hopefully the reason you are using this
 * is because you want <b>consistent</b> formatting.
 * </p><p>
 * See DateUtilDemo.java for example use.
 * </p>
 *
 * @author Paul Rule
 * @since 0.1
 */
public abstract class DateUtil {

    private static String defaultPattern = "dd/MMM/yyyy";
    private static String[] activePatterns;
    private static FastDateFormat format;

    static {
        setPattern(new String[] {defaultPattern});
    }

    /**
     * Sets the available patterns.
     */
    public static void setPattern(String[] patterns) {
        activePatterns = patterns;
        format = FastDateFormat.getInstance(activePatterns[0]);
    }

    /**
     * Formats the Date to a String, using the first set pattern.
     */
    public static String format(Date date) {
        return format.format(date);
    }

    /**
     * Converts the String to a Date using the set patterns.
     */
    public static Date date(String date) {
        try {
            return DateUtils.parseDateStrictly(date, activePatterns);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Manufactures a given date, at midnight - you could instead use DateMidnight from JodaTime.
     */
    public static Date date(int year, int month, int day) {
        try {
            return DateUtils.parseDate(day + "/" + month + "/" + year, new String[]{"dd/MM/yyyy"});
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * @return The number of days difference between the 2 dates.
     */
    public static int diff(Date date1, Date date2) {
        return (int) ((date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
    }
}
