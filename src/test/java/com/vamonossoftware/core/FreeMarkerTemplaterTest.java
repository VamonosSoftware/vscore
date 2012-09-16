package com.vamonossoftware.core;

import java.io.File;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;

public class FreeMarkerTemplaterTest {
    @Test
    public void testMerge() {
        final File file = TestUtil.getFile(this, "freemarkertemplater1.txt");
        final FreeMarkerTemplater templater = new FreeMarkerTemplater(file.getParentFile());
        final HashMap map = new HashMap();
        map.put("message1", "Hello");
        map.put("message2", "World");
        Assert.assertEquals("Hello World", templater.merge("freemarkertemplater1.txt", map).trim());
    }
}
