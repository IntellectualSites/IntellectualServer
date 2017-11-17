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
package com.github.intellectualsites.kvantum.crush.syntax.filter;

import com.github.intellectualsites.kvantum.crush.syntax.Filter;

import java.util.Collection;

final public class List extends Filter
{

    public List()
    {
        super( "list" );
    }

    public Object handle(String objectName, Object o)
    {
        StringBuilder s = new StringBuilder();
        s.append( "<ul id='list-" ).append( objectName ).append( "'>" );
        if ( o instanceof Object[] )
        {
            for ( Object oo : (Object[]) o )
            {
                s.append( "<li>" ).append( oo ).append( "</li>" );
            }
        } else if ( o instanceof Collection )
        {
            for ( Object oo : (Collection) o )
            {
                s.append( "<li>" ).append( oo ).append( "</li>" );
            }
        }
        s.append( "</ul>" );
        return s.toString();
    }

}
