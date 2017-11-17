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
package com.github.intellectualsites.kvantum.implementation;

import com.github.intellectualsites.kvantum.api.config.CoreConfig;
import com.github.intellectualsites.kvantum.api.core.ServerImplementation;
import com.github.intellectualsites.kvantum.api.logging.LogContext;
import com.github.intellectualsites.kvantum.api.logging.LogWrapper;
import com.github.intellectualsites.kvantum.api.util.Assert;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.apache.commons.text.StrSubstitutor;

/**
 * The default log handler. UsesAnsi.FColor for colored output
 */
@SuppressWarnings( "WeakerAccess" )
@AllArgsConstructor
public class DefaultLogWrapper implements LogWrapper
{

    @Setter
    private String format;

    public DefaultLogWrapper()
    {
        this( CoreConfig.Logging.logFormat );
    }

    @Override
    public void log(final LogContext logContext)
    {
        Assert.notNull( logContext );
        final String replacedMessage = StrSubstitutor.replace( format, logContext.toMap() );
        if ( ServerImplementation.hasImplementation() )
        {
            ( (Server) ServerImplementation.getImplementation() ).logStream.println( replacedMessage );
        }
        System.out.println( replacedMessage );
    }

    @Override
    public void log(final String s)
    {
        Assert.notNull( s );
        System.out.println( s );
        ( (Server) ServerImplementation.getImplementation() ).logStream.println( s );
    }

}
