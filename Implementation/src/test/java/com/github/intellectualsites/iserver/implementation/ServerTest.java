package com.github.intellectualsites.iserver.implementation;

import com.github.intellectualsites.iserver.api.views.RequestHandler;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeout;

class ServerTest extends com.github.intellectualsites.iserver.implementation.GenericServerTest
{

    @Test
    void stop()
    {
        assertTimeout( Duration.ofSeconds( 10 ), () -> serverInstance.stopServer() );
    }

    @Test
    void createSimpleRequestHandler()
    {
        RequestHandler created = serverInstance.createSimpleRequestHandler( "/", (request, response ) -> {} );
        assertNotNull( created );
    }

    @Test
    void getInstance()
    {
        assertNotNull( Server.getInstance() );
        assertEquals( serverInstance, Server.getInstance() );
    }

    @Test
    void getLogWrapper()
    {
        assertNotNull( serverInstance.getLogWrapper() );
        assertTrue( serverInstance.getLogWrapper() instanceof DefaultLogWrapper );
    }

    @Test
    void isStandalone()
    {
        assertTrue( serverInstance.isStandalone() );
    }

    @Test
    void getSocketHandler()
    {
        assertNotNull( serverInstance.getSocketHandler() );
    }

    @Test
    void getMetrics()
    {
        assertNotNull( serverInstance.getMetrics() );
    }

    @Test
    void getCacheManager()
    {
        assertNotNull( serverInstance.getCacheManager() );
    }

    @Test
    void isSilent()
    {
        assertFalse( serverInstance.isSilent() );
    }

    @Test
    void getRouter()
    {
        assertNotNull( serverInstance.getRouter() );
    }

    @Test
    void getSessionManager()
    {
        assertNotNull( serverInstance.getSessionManager() );
    }

    @Test
    void getTranslations()
    {
        assertNotNull( serverInstance.getTranslations() );
    }

    @Test
    void getCoreFolder()
    {
        assertEquals( new File( temporaryFolder, ".iserver" ).getAbsolutePath(), serverInstance.getCoreFolder()
                .getAbsolutePath() );
    }

    @Test
    void isPaused()
    {
        assertFalse( serverInstance.isPaused() );
    }

    @Test
    void isStopping()
    {
        assertFalse( serverInstance.isStopping() );
    }

    @Test
    void isStarted()
    {
        assertFalse( serverInstance.isStarted() );
    }

    @Test
    void getFileSystem()
    {
        assertNotNull( serverInstance.getFileSystem() );
    }

    @Test
    void getCommandManager()
    {
        assertNotNull( serverInstance.getCommandManager() );
    }

}