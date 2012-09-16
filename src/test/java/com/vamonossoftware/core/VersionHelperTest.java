package com.vamonossoftware.core;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author prule
 */
public class VersionHelperTest {
    @Test
    public void version() {
        Assert.assertEquals("2.5", VersionHelper.getMavenVersion(this.getClass(),"commons-lang", "commons-lang").getVersion());
        Assert.assertEquals("unknown", VersionHelper.getMavenVersion(this.getClass(), "doesnot", "exist").getVersion());
    }

    @Test
    public void grailsVersion() {
        Assert.assertEquals("0.1", VersionHelper.getGrailsAppVersion(this).getVersion());
    }

}
