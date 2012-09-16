package com.vamonossoftware.core;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class SystemUtilTest {
    @Test
    public void testHostname() {
        final String hostname = SystemUtil.hostname();
        Assert.assertTrue(StringUtils.isNotBlank(hostname));
        Assert.assertFalse("unknown".equals(hostname));
    }

    @Test
    public void testMemory() {
        final Memory memory = SystemUtil.memory();
        Assert.assertTrue(memory.getTotal()<memory.getMax());
        Assert.assertTrue(memory.getFree()<memory.getMax());
    }
}
