package com.example.tvslauncher.Server;

import ru.skornei.restserver.server.authentication.BaseAuthentication;
import ru.skornei.restserver.server.protocol.RequestInfo;

public class Authentication implements BaseAuthentication {
    @Override
    public boolean authentication(RequestInfo requestInfo) {
        return true;
    }
}