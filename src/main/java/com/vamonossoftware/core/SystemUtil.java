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

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Paul Rule
 * @since 0.1
 */
public class SystemUtil {

    public static final String ENVIRONMENT_NAME = "environment.name";

	/**
     * Returns the name of the environment the JVM is configured with, derived by looking
     * at the system property "<code>environment.name</code>". To set this environment name, pass the
     * JVM a system property with the desired name - ie:
     * <pre>
     *  -Denvironment.name=DEV
     * </pre>
     */
    public static String environment() {
        return System.getProperty(ENVIRONMENT_NAME);
    }

    /**
     * Returns the hostname of the machine
     */
    public static String hostname() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            byte[] ipAddr = addr.getAddress();
            return addr.getHostName();
        } catch (UnknownHostException e) {
            return "unknown";
        }
    }

    /**
     * Provides a easy to log representation of the current JVM memory state.
     */
    public static Memory memory() {
        final Runtime runtime = Runtime.getRuntime();
        return new Memory(runtime.freeMemory(), runtime.totalMemory(), runtime.maxMemory());
    }
}

class Memory {
    private long free;
    private long total;
    private long max;

    Memory(long freeMemory, long totalMemory, long maxMemory) {
        free = freeMemory;
        total = totalMemory;
        max = maxMemory;
    }

    public long getFree() {
        return free;
    }

    public long getMax() {
        return max;
    }

    public long getTotal() {
        return total;
    }

    
    @Override
    public String toString() {
        return "Memory{" + "free=" + free + " total=" + total + " max=" + max + '}';
    }
   
}