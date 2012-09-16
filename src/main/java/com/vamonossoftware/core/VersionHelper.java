/*
 * Copyright 2010 Vamonos Software
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.vamonossoftware.core;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;
import javax.servlet.ServletContext;

/**
 * @author Paul Rule
 * @since 0.1
 */
public class VersionHelper {

    private static final String VERSION_KEY = "version";
    private static final String POM_PROPS_PATH_MF = "/META-INF/maven/{0}/{1}/pom.properties";
    private static final String GRAILS_PROPS_PATH = "/application.properties";
    private static final String GRAILS_APP_KEY = "app.name";
    private static final String GRAILS_APPVER_KEY = "app.version";

    private static Properties loadProperties(InputStream is) {
        if (is != null) {
            Properties properties = new Properties();
            try {
                properties.load(is);
            } catch (IOException ex) {
                return null;
            }
            return properties;
        }
        return null;
    }

    public static Version fromProperties(Class clz,String path, String key) {
        Properties properties = loadProperties(clz.getResourceAsStream(path));
        if (properties != null) {
            return new Version(null, properties.getProperty(key));
        }
        return new Version(null, "unknown");
    }

    /**
     * For artifacts built with Maven, this retrieves the version of the artifact by looking in the embedded
     * META-INF pom.properties.
     */
    public static Version getMavenVersion(Class clz, String groupId, String artifactId) {
        String resourceName = MessageFormat.format(POM_PROPS_PATH_MF, new Object[]{groupId, artifactId});
        Properties properties = loadProperties(clz.getResourceAsStream(resourceName));
        if (properties != null) {
            return new Version(groupId + "-" + artifactId, properties.getProperty(VERSION_KEY));
        }

        return new Version(groupId + "-" + artifactId, "unknown");
    }

    public static Version getMavenWarVersion(ServletContext context, String groupId, String artifactId) {
        String resourceName = MessageFormat.format(POM_PROPS_PATH_MF, new Object[]{groupId, artifactId});
        Properties properties = loadProperties(context.getResourceAsStream(resourceName));
        if (properties != null) {
            return new Version(groupId + "-" + artifactId, properties.getProperty(VERSION_KEY));
        }
        return new Version(groupId + "-" + artifactId, "unknown");
    }

    /**
     * For Grails applications reads the application properties to return the version.
     * Pass in any object from the Grails application so it can access the correct classpath.
     *
     * You can expose the version via a controller:
     * <pre>
     * class HomeController {
     *   def version = {
     *     render com.vamonossoftware.core.VersionHelper.getGrailsAppVersion(this).getVersion();
     *   }
     * }
     * </pre>
     */
    public static Version getGrailsAppVersion(Object obj) {
        InputStream is = obj.getClass().getResourceAsStream(GRAILS_PROPS_PATH);
        if (is != null) {
            Properties properties = new Properties();
            try {
                properties.load(is);
            } catch (IOException ex) {
                return null;
            }
            return new Version(properties.getProperty(GRAILS_APP_KEY), properties.getProperty(GRAILS_APPVER_KEY));
        }
        return new Version(null, "unknown");
    }
}
