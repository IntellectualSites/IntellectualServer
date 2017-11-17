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
package com.github.intellectualsites.kvantum.crush.syntax;

import com.github.intellectualsites.kvantum.api.config.CoreConfig;
import com.github.intellectualsites.kvantum.api.logging.Logger;
import com.github.intellectualsites.kvantum.api.request.AbstractRequest;
import com.github.intellectualsites.kvantum.api.util.ProviderFactory;
import com.github.intellectualsites.kvantum.api.util.VariableProvider;
import com.github.intellectualsites.kvantum.crush.syntax.filter.Javascript;
import com.github.intellectualsites.kvantum.crush.syntax.filter.List;
import com.github.intellectualsites.kvantum.crush.syntax.filter.Lowercase;
import com.github.intellectualsites.kvantum.crush.syntax.filter.Uppercase;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final public class Variable extends Syntax
{

    private final Map<String, Filter> filters;

    public Variable()
    {
        super( Pattern.compile( "\\{\\{([a-zA-Z0-9]*)\\.([@A-Za-z0-9_\\-]*)( [|]{2} [A-Z]*)?}}" ) );
        filters = new HashMap<>();
        Set<Filter> preFilters = new LinkedHashSet<>();
        preFilters.add( new Uppercase() );
        preFilters.add( new Lowercase() );
        preFilters.add( new List() );
        preFilters.add( new Javascript() );
        for ( final Filter filter : preFilters )
        {
            filters.put( filter.toString(), filter );
        }
    }

    @Override
    public String process(String content, Matcher matcher, AbstractRequest r, Map<String, ProviderFactory> factories)
    {
        while ( matcher.find() )
        {
            String provider = matcher.group( 1 );
            String variable = matcher.group( 2 );

            String filter = "";
            if ( matcher.group().contains( " || " ) )
            {
                filter = matcher.group().split( " \\|\\| " )[ 1 ].replace( "}}", "" );
            }
            if ( factories.containsKey( provider.toLowerCase() ) )
            {
                try
                {
                    Optional pOptional = factories.get( provider.toLowerCase() ).get( r );
                    if ( pOptional.isPresent() )
                    {
                        final VariableProvider p = (VariableProvider) pOptional.get();
                        if ( p.contains( variable ) )
                        {
                            Object o = p.get( variable );
                            if ( !"".equals( filter ) )
                            {
                                o = filters.get( filter.toUpperCase() ).handle( variable, o );
                            }
                            content = content.replace( matcher.group(), o.toString() );
                        }
                    } else
                    {
                        content = content.replace( matcher.group(), "" );
                    }
                } catch ( final Throwable e )
                {
                    e.printStackTrace();
                    content = content.replace( matcher.group(), "" );
                }
            } else
            {
                if ( CoreConfig.debug )
                {
                    Logger.debug( "Template requesting unknown variable factory [%s]", provider.toLowerCase() );
                }
                content = content.replace( matcher.group(), "" );
            }
        }
        return content;
    }
}
