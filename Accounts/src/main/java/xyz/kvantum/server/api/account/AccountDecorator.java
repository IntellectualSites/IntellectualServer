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
package xyz.kvantum.server.api.account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;

/**
 * Decorates {@link IAccount accounts} on initialization.
 * Preferably registered using {@link IAccountManager#addAccountDecorator(AccountDecorator)}
 */
public class AccountDecorator {

    private final Collection<Consumer<IAccount>> consumers;

    private AccountDecorator(final Collection<Consumer<IAccount>> consumers) {
        this.consumers = Collections.unmodifiableCollection(consumers);
    }

    /**
     * @see #with(Object...)
     */
    @SafeVarargs public static AccountDecorator with(
        final Consumer<IAccount>... consumers) {
        return new AccountDecorator(Arrays.asList(consumers));
    }

    /**
     * Create a new decorator with the specified decorators.
     *
     * @param objects This can be {@link Consumer Consumers accepting IAccount}
     *                or any class extending {@link AccountExtension}
     * @return Created account decorator
     */
    @SuppressWarnings("ALL") public static AccountDecorator with(final Object... objects) {
        final Collection<Consumer<IAccount>> consumers = new ArrayList<>();
        for (final Object object : objects) {
            if (object instanceof Consumer) {
                consumers.add((Consumer<IAccount>) object);
            } else if (object instanceof Class) {
                final Class<? extends AccountExtension> extension =
                    (Class<? extends AccountExtension>) object;
                final Consumer<IAccount> consumer = account -> account.attachExtension(extension);
                consumers.add(consumer);
            }
        }
        return new AccountDecorator(consumers);
    }

    /**
     * Decorate an account with all of the decorators registered in this
     * {@link AccountDecorator}
     *
     * @param account {@link IAccount} to decorate
     */
    public void decorateAccount(final IAccount account) {
        this.consumers.forEach(consumer -> consumer.accept(account));
    }

}
