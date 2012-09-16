package com.vamonossoftware.core;

import java.io.File;
import java.net.URL;
import org.junit.Ignore;

/**
 *
 * @author paul
 */
@Ignore
public class TestUtil {
    public static File getFile(Object obj, String filename) {
        String file = obj.getClass().getPackage().getName().replace('.', '/')+'/'+filename;
        URL url = obj.getClass().getClassLoader().getResource(file);
        return new File(url.getFile());
    }

    public static File getTempDir() {
        File file = new File("./target/test-temp-dir",TestUtil.class.getPackage().getName());
        file.mkdirs();
        return file;
    }

}
