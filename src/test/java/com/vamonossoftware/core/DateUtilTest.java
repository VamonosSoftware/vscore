package com.vamonossoftware.core;

import java.text.ParseException;
import java.util.Date;
import org.joda.time.DateMidnight;
import org.junit.Assert;
import org.junit.Test;

public class DateUtilTest {

    static {
        DateUtil.setPattern(new String[]{"dd-MM-yy", "dd-MMM-yyyy"});
    }

    @Test
    public void testFormat() {
        Assert.assertEquals("01-02-10", DateUtil.format(new DateMidnight(2010, 2, 1).toDate()));
    }

    @Test
    public void testDate() {
        Date date = new DateMidnight(2010, 2, 1).toDate();
        Assert.assertEquals(date, DateUtil.date("01-02-10"));
        Assert.assertEquals(date, DateUtil.date("01-FEB-2010"));
        try {
            Assert.assertEquals(date, DateUtil.date("2010-02-01"));
            Assert.fail();
        } catch (RuntimeException e) {
            // pass
        }
    }

    @Test
    public void testCreateDate() {
        Date date = new DateMidnight(2010, 2, 1).toDate();
        Assert.assertEquals(date, DateUtil.date(2010, 2, 1));
    }

    @Test
    public void testDiff() {
        Date date1 = new DateMidnight(2010, 2, 1).toDate();
        Date date2 = new DateMidnight(2010, 2, 10).toDate();
        Assert.assertEquals(-9, DateUtil.diff(date1,date2));
        Assert.assertEquals(9, DateUtil.diff(date2,date1));
    }

}
