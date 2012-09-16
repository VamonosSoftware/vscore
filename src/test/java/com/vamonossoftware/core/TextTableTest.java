package com.vamonossoftware.core;

import org.junit.Assert;
import org.junit.Test;

public class TextTableTest {

    @Test
    public void testTable() {
        final TextTable table = new TextTable("xxx", TextTable.ALIGN.CENTER, TextTable.ALIGN.RIGHT, TextTable.ALIGN.LEFT);
        table.add("AAA", "BBBBB", "CC");
        table.add("AA", "BBBB", "C");
        table.add("AAAA", "BBBBBB", "CCC");

        String expected = ""
                + "xxxAAA   BBBBB CC  \n"
                + "xxx AA    BBBB C   \n"
                + "xxxAAAA BBBBBB CCC \n";

        Assert.assertEquals(expected, table.toString());
    }

    @Test
    public void testTableWithMissingColumns() {
        final TextTable table = new TextTable("xxx", TextTable.ALIGN.CENTER, TextTable.ALIGN.RIGHT, TextTable.ALIGN.LEFT);
        table.add("AAA", "BBBBB", "CC");
        table.add("AA", "BBBB");
        table.add("AAAA", "BBBBBB", "CCC");

        String expected = ""
                + "xxxAAA   BBBBB CC  \n"
                + "xxx AA    BBBB \n"
                + "xxxAAAA BBBBBB CCC \n";

        Assert.assertEquals(expected, table.toString());
    }

}
