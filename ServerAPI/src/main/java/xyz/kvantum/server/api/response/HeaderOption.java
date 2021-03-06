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
package xyz.kvantum.server.api.response;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.kvantum.server.api.config.CoreConfig;
import xyz.kvantum.server.api.logging.Logger;
import xyz.kvantum.server.api.util.AsciiString;
import xyz.kvantum.server.api.util.AsciiStringable;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused") @EqualsAndHashCode(of = "text")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE) public final class HeaderOption
    implements AsciiStringable {

    private static Map<AsciiString, HeaderOption> headerOptionMap = new HashMap<>();

    @Getter private final AsciiString text;
    @Getter private boolean cacheApplicable = true;

    public static HeaderOption create(final String string) {
        return create(AsciiString.of(string));
    }

    public static HeaderOption create(final AsciiString text) {
        return create(text, true);
    }

    public static HeaderOption create(final AsciiString text, boolean cacheApplicable) {
        final HeaderOption headerOption = new HeaderOption(text).cacheApplicable(cacheApplicable);
        headerOptionMap.put(text.toLowerCase(), headerOption);
        return headerOption;
    }

    public static HeaderOption getOrCreate(final AsciiString text) {
        if (headerOptionMap.containsKey(text.toLowerCase())) {
            return headerOptionMap.get(text.toLowerCase());
        }
        if (CoreConfig.debug) {
            Logger.debug("View requested unknown header [{}] - Creating...", text);
        }
        return create(text);
    }

    private HeaderOption cacheApplicable(final boolean b) {
        this.cacheApplicable = b;
        return this;
    }

    @Override public final String toString() {
        return this.text.toString();
    }

    @Override public final AsciiString toAsciiString() {
        return this.text;
    }

    /**
     * Get the ascii-encoded version of the string that this option represents
     *
     * @return ascii encoded string
     */
    public final byte[] getBytes() {
        return this.toAsciiString().getValue();
    }
}
