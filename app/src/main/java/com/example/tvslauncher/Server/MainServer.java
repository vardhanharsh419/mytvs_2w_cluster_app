package com.example.tvslauncher.Server;

import ru.skornei.restserver.annotations.RestServer;
import ru.skornei.restserver.server.BaseRestServer;

@RestServer( port = MainServer.PORT,
        converter = JsonConverter.class, //Optional
        authentication = Authentication.class, //Optional
        controllers = {PingController.class} )
public class MainServer extends BaseRestServer {
    public static final int PORT = 8080;
}