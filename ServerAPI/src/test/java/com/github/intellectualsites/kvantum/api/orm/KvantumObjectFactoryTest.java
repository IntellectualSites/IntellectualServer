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
package com.github.intellectualsites.kvantum.api.orm;

import com.github.intellectualsites.kvantum.api.mocking.MockRequest;
import com.github.intellectualsites.kvantum.api.orm.annotations.KvantumConstructor;
import com.github.intellectualsites.kvantum.api.orm.annotations.KvantumField;
import com.github.intellectualsites.kvantum.api.orm.annotations.KvantumObject;
import com.github.intellectualsites.kvantum.api.request.AbstractRequest;
import com.github.intellectualsites.kvantum.api.request.HttpMethod;
import com.github.intellectualsites.kvantum.api.util.ParameterScope;
import net.sf.oval.constraint.MinLength;
import net.sf.oval.constraint.NotEmpty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class KvantumObjectFactoryTest
{

    @Test
    void from()
    {
        //
        // Make sure that the request is parsed successfully
        //
        final KvantumObjectFactory<MockObject> factory = KvantumObjectFactory.from( MockObject.class );
        MockRequest mockRequest = new MockRequest( new AbstractRequest.Query( HttpMethod.POST,
                "/?koala=animal" ) );
        KvantumObjectParserResult<MockObject> result = factory.build( ParameterScope.GET )
                .parseRequest( mockRequest );
        Assertions.assertNotNull( result );
        Assertions.assertTrue( result.isSuccess() );
        Assertions.assertNull( result.getError() );
        Assertions.assertNotNull( result.getParsedObject() );
        final MockObject mockObject = result.getParsedObject();
        Assertions.assertEquals( "animal", mockObject.string1 );
        Assertions.assertEquals( "york", mockObject.string2 );

        //
        // Test if the validation fails (minimum size of string1 is 2, but provided string size is 1)
        //
        mockRequest = new MockRequest( new AbstractRequest.Query( HttpMethod.POST, "/?koala=a" ) );
        result = factory.build( ParameterScope.GET ).parseRequest( mockRequest );
        Assertions.assertNotNull( result );
        Assertions.assertFalse( result.isSuccess() );
        Assertions.assertNotNull( result.getError() );
        Assertions.assertNull( result.getParsedObject() );
        Assertions.assertTrue( result.getError()
                instanceof KvantumObjectParserResult.KvantumObjectParserValidationFailed );
        final KvantumObjectParserResult.KvantumObjectParserValidationFailed validationFailed = (
                KvantumObjectParserResult.KvantumObjectParserValidationFailed) result.getError();
        Assertions.assertEquals( 1, validationFailed.getViolations().size() );
    }

    @KvantumObject(checkValidity = true)
    private static class MockObject
    {

        @MinLength(2)
        @NotEmpty
        @KvantumField(kvantumName = "koala")
        private String string1;

        @KvantumField(kvantumName = "new", defaultValue = "york")
        private String string2;

        @KvantumConstructor
        private MockObject()
        {
        }

    }

}
