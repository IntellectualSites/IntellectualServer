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

import com.github.intellectualsites.kvantum.api.util.KvantumJsonFactory;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * GSON serializer for accounts. Will only expose ID and username (not data), so this
 * can be used publicly
 */
final public class AccountSerializer implements JsonSerializer<Account>, JsonDeserializer<Account>
{

    @Override
    public JsonElement serialize(final Account src, final Type typeOfSrc, final JsonSerializationContext context)
    {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.add( "id", new JsonPrimitive( src.getId() ) );
        jsonObject.add( "username", KvantumJsonFactory.stringToPrimitive( src.getUsername() ) );
        return jsonObject;
    }

    @Override
    public Account deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws
            JsonParseException
    {
        if ( json instanceof JsonObject )
        {
            final JsonObject object = (JsonObject) json;
            return new Account( object.get( "id" ).getAsInt(),
                    object.get( "username" ).getAsString(), "" );
        } else
        {
            throw new JsonParseException( "Provided json not an object" );
        }
    }

}
