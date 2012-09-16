package com.vamonossoftware.core;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTimeUtils;
import org.joda.time.JodaTimePermission;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArchiveServiceSimpleImplTest {

    File tempDir = TestUtil.getTempDir();
    File archiveDir = new File(tempDir, "archive");
    File sourceDir = new File(tempDir, "source");
    File sourceFile1;
    File sourceFile2;

    @Before
    public void before() throws IOException {
        DateTimeUtils.setCurrentMillisFixed(1289343447508L);
        archiveDir.mkdirs();
        sourceDir.mkdirs();
        FileUtils.cleanDirectory(archiveDir);
        sourceFile1 = new File(sourceDir, "samplefile1");
        FileUtils.writeStringToFile(sourceFile1, "Hello World");
        sourceFile2 = new File(sourceDir, "samplefile2");
        FileUtils.writeStringToFile(sourceFile2, "Loreum Ipsum");
    }

    @Test
    public void testCount() throws IOException {
        ArchiveService archiveService = new ArchiveServiceSimpleImpl(archiveDir, false, ArchiveServiceSimpleImpl.Depth.YEAR);
        final File archive1 = archiveService.archive(sourceFile1);
        final File archive2 = archiveService.archive(sourceFile2);

        Assert.assertEquals(archiveDir.getAbsolutePath() + "/2010/1/" + sourceFile1.getName(), archive1.getAbsolutePath());
        Assert.assertEquals(archiveDir.getAbsolutePath() + "/2010/2/" + sourceFile2.getName(), archive2.getAbsolutePath());
    }

    @Test
    public void testMove() throws IOException {
        ArchiveService archiveService = new ArchiveServiceSimpleImpl(archiveDir, true, ArchiveServiceSimpleImpl.Depth.YEAR);
        long size = sourceFile1.length();
        final File archive1 = archiveService.archive(sourceFile1);

        Assert.assertFalse(sourceFile1.exists());
        Assert.assertTrue(archive1.exists());
        Assert.assertTrue(archive1.length()>0);
        Assert.assertEquals(size, archive1.length());
    }

    @Test
    public void testCopy() throws IOException {
        ArchiveService archiveService = new ArchiveServiceSimpleImpl(archiveDir, false, ArchiveServiceSimpleImpl.Depth.YEAR);
        final File archive1 = archiveService.archive(sourceFile1);

        Assert.assertTrue(sourceFile1.exists());
        Assert.assertTrue(archive1.exists());
        Assert.assertTrue(archive1.length()>0);
        Assert.assertEquals(sourceFile1.length(), archive1.length());
    }

    @Test
    public void testDepth() throws IOException {
        {
            ArchiveService archiveService = new ArchiveServiceSimpleImpl(archiveDir, false, ArchiveServiceSimpleImpl.Depth.MONTH);
            final File archive1 = archiveService.archive(sourceFile1);
            Assert.assertEquals(archiveDir.getAbsolutePath() + "/2010/11/1/" + sourceFile1.getName(), archive1.getAbsolutePath());
        }
        {
            ArchiveService archiveService = new ArchiveServiceSimpleImpl(archiveDir, false, ArchiveServiceSimpleImpl.Depth.DAY);
            final File archive1 = archiveService.archive(sourceFile1);
            Assert.assertEquals(archiveDir.getAbsolutePath() + "/2010/11/10/1/" + sourceFile1.getName(), archive1.getAbsolutePath());
        }
        {
            ArchiveService archiveService = new ArchiveServiceSimpleImpl(archiveDir, false, ArchiveServiceSimpleImpl.Depth.HOUR);
            final File archive1 = archiveService.archive(sourceFile1);
            Assert.assertEquals(archiveDir.getAbsolutePath() + "/2010/11/10/09/1/" + sourceFile1.getName(), archive1.getAbsolutePath());
        }
        // test 24 hour time
        {
            DateTimeUtils.setCurrentMillisFixed(1289355447508L);
            ArchiveService archiveService = new ArchiveServiceSimpleImpl(archiveDir, false, ArchiveServiceSimpleImpl.Depth.HOUR);
            final File archive1 = archiveService.archive(sourceFile1);
            Assert.assertEquals(archiveDir.getAbsolutePath() + "/2010/11/10/13/1/" + sourceFile1.getName(), archive1.getAbsolutePath());
        }
    }
}
