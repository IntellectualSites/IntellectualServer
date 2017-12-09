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
package xyz.kvantum.server.api.response;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import lombok.Getter;
import lombok.NonNull;
import xyz.kvantum.server.api.config.CoreConfig;
import xyz.kvantum.server.api.logging.Logger;
import xyz.kvantum.server.api.util.TimeUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@SuppressWarnings({ "unused", "WeakerAccess" })
final public class Header
{

    public static final String CONTENT_TYPE_CSS = "text/css; charset=utf-8";
    public static final String CONTENT_TYPE_OCTET_STREAM = "application/octet-stream; charset=utf-8";
    public static final String CONTENT_TYPE_HTML = "text/html; charset=utf-8";
    public static final String CONTENT_TYPE_JAVASCRIPT = "text/javascript; charset=utf-8";
    public static final String CONTENT_TYPE_TEXT_EXAMPLE = "text/example; charset=utf-8";
    public static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";

    public static final String CACHE_NO_CACHE = "no-cache";

    public static final String POWERED_BY = "Kvantum";

    //
    // 1xx Informational
    //
    public static final String STATUS_CONTINUE = "100 Continue";
    public static final String STATUS_SWITCHING_PROTOCOLS = "101 Switching Protocols";
    public static final String STATUS_PROCESSING = "102 Processing";

    //
    // 2xx Success
    //
    public static final String STATUS_OK = "200 OK";
    public static final String STATUS_CREATED = "2001 Created";
    public static final String STATUS_NON_AUTHORITATIVE_INFORMATION = "203 Non-Authoriative Information";
    public static final String STATUS_ACCEPTED = "202 Accepted";
    public static final String STATUS_NO_CONTENT = "204 No Content";
    public static final String STATUS_RESET_CONTENT = "205 Reset Content";
    public static final String STATUS_PARTIAL_CONTENT = "206 Partial Content";

    //
    // 3xx Redirection
    //
    public static final String STATUS_MOVED_PERMANENTLY = "301 Moved Permanently";
    public static final String STATUS_TEMPORARY_REDIRECT = "307 Temporary Redirect";

    //
    // 4xx Client errors
    //
    public static final String STATUS_BAD_REQUEST = "400 Bad Request";
    public static final String STATUS_ACCESS_DENIED = "401 Access Denied";
    public static final String STATUS_UNAUTHORIZED = "401 Unauthorized status";
    public static final String STATUS_NOT_FOUND = "404 Not Found";
    public static final String STATUS_NOT_ALLOWED = "405 Method not allowed";
    public static final String STATUS_NOT_ACCEPTABLE = "406 Not Acceptable";
    public static final String STATUS_PAYLOAD_TOO_LARGE = "413 Payload Too Large";
    public static final String STATUS_ENTITY_TOO_LARGE = "413 Entity Too Large";
    public static final String STATUS_REQUEST_TIMEOUT = "408 Request Timeout";
    public static final String STATUS_TOO_MANY_REQUESTS = "429 Too Many Requests";

    //
    // 5xx Server errors
    //
    public static final String STATUS_HTTP_VERSION_NOT_SUPPORTED = "505 HTTP Version Not Supported";
    public static final String STATUS_INTERNAL_ERROR = "500 Internal Server Error";

    public static final String ALLOW_ALL = "*";

    public static final HeaderOption X_CONTENT_TYPE_OPTIONS = HeaderOption.create( "X-Content-Type-Options" );
    public static final HeaderOption X_FRAME_OPTIONS = HeaderOption.create( "X-Frame-Options" );
    public static final HeaderOption CONTENT_SECURITY_POLICY = HeaderOption.create( "Content-Security-Policy" );

    /**
     * Specifying which web sites can
     * participate in cross-origin
     * resource sharing
     *
     * @see #ALLOW_ALL
     */
    public static final HeaderOption HEADER_ACCESS_CONTROL_ALLOW_ORIGIN = HeaderOption.create( "Access-Control-Allow-Origin" );
    /**
     * Specifies which patch document
     * formats this server supports
     */
    public static final HeaderOption HEADER_ACCEPT_PATCH = HeaderOption.create( "Accept-Patch" );
    /**
     * What partial content range
     * types this server supports
     */
    public static final HeaderOption HEADER_ACCEPT_RANGES = HeaderOption.create( "Accept-Ranges" );
    /**
     * The age the object has been in a
     * proxy cache, in seconds
     */
    public static final HeaderOption HEADER_AGE = HeaderOption.create( "Age" );
    /**
     * Valid actions for a specified resource.
     * To be used for a 405 Method Not Allowed
     *
     * @see #STATUS_NOT_ALLOWED
     */
    public static final HeaderOption HEADER_ALLOW = HeaderOption.create( "Allow" );
    /**
     * Tells all caching mechanism
     * from server to client, whether
     * they may cache this object. It
     * is measured in seconds
     */
    public static final HeaderOption HEADER_CACHE_CONTROL = HeaderOption.create( "Cache-Control" );
    /**
     * Control options for the current
     * connection and list of hop-by-hop
     * response fields
     */
    public static final HeaderOption HEADER_CONNECTION = HeaderOption.create( "Connection" );
    /**
     * The type of encoding used on the data
     */
    public static final HeaderOption HEADER_CONTENT_ENCODING = HeaderOption.create( "Content-Encoding" );
    /**
     * The natural language, or languages
     * of the intended audience for the
     * enclosed content
     */
    public static final HeaderOption HEADER_CONTENT_LANGUAGE = HeaderOption.create( "Content-Language" );
    public static final HeaderOption HEADER_CONTENT_TRANSFER_ENCODING = HeaderOption.create( "Content-Transfer-Encoding" );
    /**
     * An opportunity to raise a "File
     * Download" dialogue box, for a
     * known MIME type with binary
     * format, or suggest a filename
     * for dynamic content. Quotes
     * are necessary with special
     * characters
     */
    public static final HeaderOption HEADER_CONTENT_DISPOSITION = HeaderOption.create( "Content-Disposition" );
    /**
     * Used in redirection, or when
     * a new resource has been created
     */
    public static final HeaderOption HEADER_LOCATION = HeaderOption.create( "Location" );
    /**
     * A Base64-encoded binary MD5 sum
     * of the content of the response
     */
    public static final HeaderOption HEADER_CONTENT_MD5 = HeaderOption.create( "Content-MD5" );
    /**
     * Where in a full body
     * message this partial message
     * belongs
     */
    public static final HeaderOption HEADER_CONTENT_RANGE = HeaderOption.create( "Content-Range" );
    public static final HeaderOption HEADER_X_POWERED_BY = HeaderOption.create( "X-Powered-By" );
    /**
     * The MIME type of this content
     */
    public static final HeaderOption HEADER_CONTENT_TYPE = HeaderOption.create( "Content-Type" );
    /**
     * CGI Heeader field specifying the
     * status of the HTTP response.
     * Normal HTTP response use
     * a Status-Line instead
     */
    public static final HeaderOption HEADER_STATUS = HeaderOption.create( "Status" );
    /**
     * The date and time that the
     * message was sent (in HTTP-date format
     * as defined by RFC 7231
     *
     * @see TimeUtil#getHTTPTimeStamp() To get a valid timestamp
     */
    public static final HeaderOption HEADER_DATE = HeaderOption.create( "Date" );
    /**
     * An identifier for a specific
     * version of a resource, often
     * a message digest
     */
    public static final HeaderOption HEADER_ETAG = HeaderOption.create( "ETag" );
    /**
     * A name for the server
     */
    public static final HeaderOption HEADER_SERVER = HeaderOption.create( "Server" );
    /**
     * Gives the date/time after which
     * the response is considered stale
     * (in HTTP-date format as defined by
     * RFC 7231)
     *
     * @see TimeUtil#getHTTPTimeStamp() To get a valid timestamp
     */
    public static final HeaderOption HEADER_EXPIRES = HeaderOption.create( "Expires" );
    /**
     * The last modified date for the
     * requested object (in HTTP-date
     * format as defined by RFC 7231)
     *
     * @see TimeUtil#getHTTPTimeStamp() To get a valid timestamp
     */
    public static final HeaderOption HEADER_LAST_MODIFIED = HeaderOption.create( "Last-Modified" );
    /**
     * Used to express a typed
     * relationship with another
     * resource, where the relation
     * type is defined by RFC 5988
     */
    public static final HeaderOption HEADER_LINK = HeaderOption.create( "Link" );
    /**
     * The length of the response
     * body in octets (8-bit bytes)
     */
    public static final HeaderOption HEADER_CONTENT_LENGTH = HeaderOption.create( "Content-Length" );
    /**
     * Implementation-specific fields
     * that may have various effects
     * anywhere along the request-
     * response chain
     */
    public static final HeaderOption HEADER_PRAGMA = HeaderOption.create( "Pragma" );

    public static final HeaderOption HEADER_WWW_AUTHENTICATE = HeaderOption.create( "WWW-Authenticate" );

    /**
     * Request authentication to
     * access the proxy
     */
    public static final HeaderOption HEADER_PROXY_AUTHENTICATE = HeaderOption.create( "Proxy-Authenticate" );
    /**
     * HTTP Public Key Pinning,
     * announces hash of a website's
     * authentic TLS certificate
     */
    public static final HeaderOption HEADER_PUBLIC_KEY_PINS = HeaderOption.create( "Public-Key-Pins" );
    /**
     * Used in redirection, or
     * when a new resource has been
     * created.
     */
    public static final HeaderOption HEADER_REFRESH = HeaderOption.create( "Refresh" );

    public static final HeaderOption HEADER_SET_COOKIE = HeaderOption.create( "Set-Cookie" );

    /**
     * If an entity is temporarily
     * unavailable, this instructs
     * the client to try again later.
     * Value could be a specified
     * period of time (in seconds)
     * or a HTTP-date
     */
    public static final HeaderOption HEADER_RETRY_AFTER = HeaderOption.create( "Retry-After" );

    private final ListMultimap<HeaderOption, String> headers = MultimapBuilder.hashKeys().arrayListValues().build();

    @Getter
    private String status;
    @Getter
    private String format;

    /**
     * Construct a new header instance
     *
     * @param status HTTP status
     * @param format HTTP version
     */
    public Header(final String status, final String format)
    {
        this.status = status;
        this.format = format;
    }

    /**
     * Construct a new header instance with a specified status,
     * specifying HTTP version HTTP/1.1
     *
     * @param status Status
     */
    public Header(final String status)
    {
        this( status, "HTTP/1.1" );
    }

    /**
     * Set the HTTP response status
     *
     * @param status Response status
     * @return Instance
     */
    public Header setStatus(@NonNull final String status)
    {
        this.status = status;
        this.set( HEADER_STATUS, status );
        return this;
    }

    /**
     * Store a header and overwrite previous entries
     *
     * @param key   Header key
     * @param value Header value
     * @return Instance
     */
    public Header set(final HeaderOption key, final String value)
    {
        return set( key, value, false );
    }

    /**
     * Store a header
     *
     * @param key             Header key
     * @param value           Header value
     * @param allowDuplicates If this is set to false, then previous entries will be overwritten
     * @return Instance
     */
    public Header set(@NonNull final HeaderOption key, @NonNull final String value, final boolean allowDuplicates)
    {
        if ( !allowDuplicates )
        {
            this.headers.removeAll( key );
        }
        this.headers.put( key, value );
        return this;
    }

    /**
     * Get all stored values for a header key
     *
     * @param key Header eky
     * @return Collection with all stored values
     */
    public Collection<String> getMultiple(@NonNull final HeaderOption key)
    {
        final Collection<String> values = new ArrayList<>();
        if ( this.headers.containsKey( key ) )
        {
            values.addAll( this.headers.get( key ) );
        }
        return values;
    }

    /**
     * Get a stored header value, if it exists
     *
     * @param key Header key
     * @return Optional
     */
    public Optional<String> get(@NonNull final HeaderOption key)
    {
        if ( this.headers.containsKey( key ) )
        {
            return Optional.of( this.headers.get( key ).get( 0 ) );
        }
        return Optional.empty();
    }


    /**
     * Redirect to another URL using status 307 Temporary Redirect
     *
     * @see #redirect(String, String) to specify redirect header
     */
    public void redirect(final String newURL)
    {
        redirect( newURL, Header.STATUS_TEMPORARY_REDIRECT );
    }

    /**
     * Used to redirect a request to another url
     *
     * @param newURL         New URL
     * @param redirectHeader Status code
     */
    public void redirect(@NonNull final String newURL, @NonNull final String redirectHeader)
    {
        set( Header.HEADER_LOCATION, newURL );
        set( Header.HEADER_STATUS, redirectHeader );
        setStatus( redirectHeader );
    }

    /**
     * Set a cookie and return this instance
     *
     * @param cookie Cookie
     * @return Instance
     */
    public Header setCookie(@NonNull final ResponseCookie cookie)
    {
        if ( CoreConfig.debug )
        {
            Logger.debug( "Cookie set! Key: %s, Value: %s, Full: %s", cookie.getCookie(), cookie.getValue(),
                    cookie.toString() );
        }
        if ( this.headers.containsEntry( HEADER_SET_COOKIE, cookie.toString() ) )
        {
            this.headers.remove( HEADER_SET_COOKIE, cookie.toString() );
        }
        this.headers.put( HEADER_SET_COOKIE, cookie.toString() );
        return this;
    }

    /**
     * Remove a specified cookie and return this instance
     *
     * @param cookie Cookie key
     * @return Instance
     */
    public Header removeCookie(@NonNull final String cookie)
    {
        final ResponseCookie responseCookie = ResponseCookie.builder().cookie( cookie )
                .value( "deleted" ).expires( new Date( 0 ) ).build();
        return setCookie( responseCookie );
    }

    /**
     * Clear all stored headers and return this instance
     *
     * @return Instance
     */
    public Header clear()
    {
        this.headers.clear();
        return this;
    }

    /**
     * Check if a header value is stored
     *
     * @param headerOption Key
     * @return True if it is stored
     */
    public boolean hasHeader(@NonNull final HeaderOption headerOption)
    {
        return this.headers.containsKey( headerOption );
    }

    /**
     * Get a copy of the stored header pairs
     *
     * @return Copy of the internal map
     */
    public Multimap<HeaderOption, String> getHeaders()
    {
        return MultimapBuilder.ListMultimapBuilder.hashKeys( headers.size() ).arrayListValues().build( headers );
    }
}
