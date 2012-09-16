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

/**
 * When performing an operation, there are 3 high level results:
 * <ol>
 * <li>Success - operation completed without any problems</li>
 * <li>Failure - operation completed with problems</li>
 * <li>Error - operation could not be completed due to unexpected issues</li>
 * </ol>
 *
 * @author Paul Rule
 * @since 0.1
 */
public enum Status {
    SUCCESS, FAILURE, ERROR;
}
