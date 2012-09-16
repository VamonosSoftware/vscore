package com.vamonossoftware.core.demo;

import com.vamonossoftware.core.DateUtil;
import java.util.Date;

/**
 *
 * @author paul5
 */
public class DateUtilDemo {
    public static void main(String[] args) {
        // set up our desired available patterns when the application starts
        DateUtil.setPattern(new String[] {"dd-MMM-yyyy", "dd-MM-yy"});

        // now we can easily manufacture dates using the available patterns
        Date date1 = DateUtil.date("1-Jan-2010");
        Date date2 = DateUtil.date("2-2-10");

        // and convert dates to Strings *consistently* throughout our application
        // (uses the FIRST available pattern)
        System.out.println("Date 1 is " + DateUtil.format(date1));
        System.out.println("Date 2 is " + DateUtil.format(date2));
    }
}
