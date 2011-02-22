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
package org.apache.easyant.example;

import java.io.IOException;
import java.util.Properties;
import org.apache.easyant.example.core.ExampleCore;
import org.apache.easyant.example.core.ExampleCoreImpl;

public class Example {
    public static void main(String[] args) throws IOException {
        System.out.println(new Example().getMessage());
    }

    public String getMessage() throws IOException {
        ExampleCore exampleCore = new ExampleCoreImpl();
        return exampleCore.getMessage();
    }
}
