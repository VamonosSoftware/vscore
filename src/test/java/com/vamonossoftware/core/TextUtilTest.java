package com.vamonossoftware.core;

import java.util.Properties;
import org.junit.Assert;
import org.junit.Test;

public class TextUtilTest {
    @Test
    public void testReplace() {
        final Properties properties = new Properties();
        properties.put("name", "Mr Ed");
        properties.put("A", "AA");
        properties.put("B", "BB");

        Assert.assertEquals("Hello Mr Ed.", TextUtil.replace("Hello ${name}.", properties));
        Assert.assertEquals("AA ", TextUtil.replace("${A} ", properties));
        Assert.assertEquals("AA AA", TextUtil.replace("${A} ${A}", properties));
    }

    @Test
    public void testEscapeHtml() {
        Assert.assertEquals("Hello Mr Ed.", TextUtil.escapeHtml("Hello Mr Ed."));
        Assert.assertEquals("Hello &lt;b&gt;Mr&lt;/b&gt; Ed.", TextUtil.escapeHtml("Hello <b>Mr</b> Ed."));
    }


    @Test
    public void testHyperlink() {
        Assert.assertEquals("Hello Mr Ed. <a target=\"_blank\" href=\"http://www.helloworld.com\">www.helloworld.com</a> ", TextUtil.hyperLink("Hello Mr Ed. www.helloworld.com"));
        Assert.assertEquals("Hello Mr Ed. <a target=\"_blank\" href=\"http://www.helloworld.com\">http://www.helloworld.com</a> ", TextUtil.hyperLink("Hello Mr Ed. http://www.helloworld.com"));
    }
}
