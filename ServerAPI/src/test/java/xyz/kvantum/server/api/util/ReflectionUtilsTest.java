/*
 *    _  __                     _
 *    | |/ /__   __ __ _  _ __  | |_  _   _  _ __ ___
 *    | ' / \ \ / // _` || '_ \ | __|| | | || '_ ` _ \
 *    | . \  \ V /| (_| || | | || |_ | |_| || | | | | |
 *    |_|\_\  \_/  \__,_||_| |_| \__| \__,_||_| |_| |_|
 *
 *    Copyright (C) 2019 Alexander Söderberg
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xyz.kvantum.server.api.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

class ReflectionUtilsTest {

    @Test void getAnnotatedMethods() {
        final List<ReflectionUtils.AnnotatedMethod<SampleAnnotation>> methodList =
            ReflectionUtils.getAnnotatedMethods(SampleAnnotation.class, ReflectionUtilsTest.class);
        Assertions.assertEquals(1, methodList.size());
        final ReflectionUtils.AnnotatedMethod<SampleAnnotation> method = methodList.get(0);
        Assertions.assertNotNull(method);
        final SampleAnnotation annotation = method.getAnnotation();
        Assertions.assertEquals("Hello Schweeden!", annotation.content());
    }

    @SampleAnnotation(content = "Hello Schweeden!") void sampleMethod() {
    }

    @Retention(RetentionPolicy.RUNTIME) @Target(ElementType.METHOD)
    private @interface SampleAnnotation {

        String content() default "Hello World!";
    }

}
