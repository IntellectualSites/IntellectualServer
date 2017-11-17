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
package com.github.intellectualsites.kvantum.api.socket;

import com.github.intellectualsites.kvantum.api.core.ServerImplementation;
import com.github.intellectualsites.kvantum.api.util.ITempFileManager;
import lombok.Getter;

import javax.net.ssl.SSLSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Socket context used to make sure that sockets are handled
 * the same way, across implementations
 */
final public class SocketContext
{

    @Getter
    private final Socket socket;

    private ITempFileManager tempFileManager;

    /**
     * Construct a new socket context from a socket
     *
     * @param socket Incoming socket
     */
    public SocketContext(final Socket socket)
    {
        this.socket = socket;
    }

    /**
     * Get a {@link ITempFileManager} for this socket instance.
     * This loaded lazily, so it will be created on the first call to
     * this method.
     *
     * @return ITempFileManager instance
     */
    public ITempFileManager getTempFileManager()
    {
        if ( tempFileManager == null )
        {
            this.tempFileManager = ServerImplementation.getImplementation().getTempFileManagerFactory()
                    .newTempFileManager();
        }
        return tempFileManager;
    }

    /**
     * Check if the socket is connected over SSL
     *
     * @return true if the socket is connected over SSL (is a {@link SSLSocket})
     */
    public boolean isSSL()
    {
        return this.socket instanceof SSLSocket;
    }

    /**
     * Close the socket and its resources
     */
    public void close()
    {
        if ( this.isActive() )
        {
            try
            {
                this.socket.close();
            } catch ( final Exception e )
            {
                e.printStackTrace();
            }
        }
        try
        {
            if ( this.tempFileManager != null )
            {
                this.tempFileManager.clearTempFiles();
            }
        } catch ( final Exception e )
        {
            e.printStackTrace();
        }
    }

    /**
     * Get the socket address
     *
     * @return socket address
     */
    public InetAddress getAddress()
    {
        return this.socket.getInetAddress();
    }

    /**
     * Check if the socket is active
     *
     * @return true if the socket ias not closed and is connected
     */
    public boolean isActive()
    {
        return this.socket != null &&
                !this.socket.isClosed() &&
                this.socket.isConnected();
    }

}
