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
package com.github.intellectualsites.kvantum.api.request.post;

import com.github.intellectualsites.kvantum.api.config.CoreConfig;
import com.github.intellectualsites.kvantum.api.request.AbstractRequest;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

final public class UrlEncodedPostRequest extends PostRequest
{

    public UrlEncodedPostRequest(final AbstractRequest parent, final String request)
    {
        super( parent, request, false );
    }

    @Override
    protected void parseRequest(final String request)
    {
        String fixedRequest;
        try
        {
            fixedRequest = URLDecoder.decode( request, StandardCharsets.UTF_8.toString() );
        } catch ( final Exception e )
        {
            if ( CoreConfig.debug )
            {
                e.printStackTrace();
            }
            fixedRequest = request;
        }
        this.setRequest( fixedRequest );
        for ( final String s : fixedRequest.split( "&" ) )
        {
            if ( !s.isEmpty() )
            {
                final String[] p = s.split( "=" );
                if ( p.length < 2 )
                {
                    continue;
                }
                getVariables().put( p[ 0 ], p[ 1 ].replace( "+", " " ) );
            }
        }
    }

    @Override
    public EntityType getEntityType()
    {
        return EntityType.FORM_URLENCODED;
    }

}
