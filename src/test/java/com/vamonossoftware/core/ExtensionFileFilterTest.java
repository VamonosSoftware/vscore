package com.vamonossoftware.core;

import java.io.File;
import org.junit.Assert;
import org.junit.Test;

public class ExtensionFileFilterTest {
    @Test
    public void testAccept() {
        final ExtensionFileFilter filter = new ExtensionFileFilter("txt", "properties");
        Assert.assertTrue(filter.accept(new File("x.txt")));
        Assert.assertTrue(filter.accept(new File("x.properties")));
        Assert.assertFalse(filter.accept(new File("x.exe")));
    }
}
