/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.easyant.tasks.menu;

import org.apache.tools.ant.types.DataType;

/**
 * This {@link DataType} is a representation of an entry in a menu
 */
public class MenuEntry extends DataType {
    private String title;
    private String targetLink;
    /**
     * Get the title
     * @return a title
     */
    public String getTitle() {
        return title;
    }
    /**
     * Set the title
     * @param title a title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Get the target link
     * @return a target url
     */
    public String getTargetLink() {
        return targetLink;
    }
    /**
     * Set the target link
     * @param targetLink a target link
     */
    public void setTargetLink(String targetLink) {
        this.targetLink = targetLink;
    }
    
}
