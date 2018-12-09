/*
 *    _  __                     _
 *    | |/ /__   __ __ _  _ __  | |_  _   _  _ __ ___
 *    | ' / \ \ / // _` || '_ \ | __|| | | || '_ ` _ \
 *    | . \  \ V /| (_| || | | || |_ | |_| || | | | | |
 *    |_|\_\  \_/  \__,_||_| |_| \__| \__,_||_| |_| |_|
 *
 *    Copyright (C) 2018 Alexander Söderberg
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
package xyz.kvantum.server.api.request;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class HttpMethodTest {

    @Test void getByName() {
        final Optional<HttpMethod> upperscorePostTest = HttpMethod.getByName("POST");
        final Optional<HttpMethod> underscorePostTest = HttpMethod.getByName("post");
        final Optional<HttpMethod> whitespacePostTest = HttpMethod.getByName(" PO ST");
        // False positive test
        final Optional<HttpMethod> unknownMethodTest = HttpMethod.getByName("foo");
        // Assertions
        Assertions.assertTrue(upperscorePostTest.isPresent());
        Assertions.assertTrue(underscorePostTest.isPresent());
        Assertions.assertTrue(whitespacePostTest.isPresent());
        Assertions.assertFalse(unknownMethodTest.isPresent());
    }
}