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

import xyz.kvantum.server.api.AccountService;
import xyz.kvantum.server.api.config.CoreConfig;
import xyz.kvantum.server.api.request.AbstractRequest;
import xyz.kvantum.server.api.views.requesthandler.Middleware;
import xyz.kvantum.server.api.views.requesthandler.MiddlewareQueue;

public final class AuthenticationRequiredMiddleware extends Middleware {

    @Override public void handle(final AbstractRequest request, final MiddlewareQueue queue) {
        final IAccountManager accountManager = AccountService.getInstance().getGlobalAccountManager();
        if (accountManager != null && accountManager.getAccount(request.getSession()).isPresent()) {
            queue.handle(request);
        } else {
            request.internalRedirect(CoreConfig.Middleware.loginRedirect);
        }
    }

}
