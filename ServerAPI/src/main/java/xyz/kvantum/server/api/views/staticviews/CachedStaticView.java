/*
 *
 *    Copyright (C) 2017 IntellectualSites
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
package xyz.kvantum.server.api.views.staticviews;

import xyz.kvantum.server.api.cache.CacheApplicable;
import xyz.kvantum.server.api.request.AbstractRequest;
import xyz.kvantum.server.api.views.View;

final class CachedStaticView extends View implements CacheApplicable
{

    CachedStaticView(final ViewDeclaration viewDeclaration,
                     final ResponseMethod method)
    {
        super( viewDeclaration.getFilter(), viewDeclaration.getName(),
                null, method, viewDeclaration.getHttpMethod() );
        this.forceHTTPS = viewDeclaration.isForceHttps();
    }

    @Override
    public boolean passes(final AbstractRequest request)
    {
        return true;
    }

    @Override
    public boolean isApplicable(final AbstractRequest r)
    {
        return true;
    }
}
