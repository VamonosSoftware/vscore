package com.vamonossoftware.core;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

public class ZipUtilTest {

    @Test
    public void testCount() {
        Assert.assertEquals(3, ZipUtil.countFiles(TestUtil.getFile(this, "ziputiltest.zip")));
    }

    @Test
    public void testUnzip() throws Exception {
        final File tempDir = TestUtil.getTempDir();
        FileUtils.cleanDirectory(tempDir);
        Assert.assertEquals(0, FileUtils.listFiles(tempDir, null, true).size());
        ZipUtil.unzip(TestUtil.getFile(this, "ziputiltest.zip"), TestUtil.getTempDir());
        Assert.assertEquals(3, FileUtils.listFiles(tempDir, null, true).size());
        Assert.assertTrue(new File(tempDir, "zipfile1").exists());
        Assert.assertTrue(new File(tempDir, "zipfile2").exists());
        Assert.assertTrue(new File(tempDir, "zipfolder1/zipfile3").exists());
    }
}
