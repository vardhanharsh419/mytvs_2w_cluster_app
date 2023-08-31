package com.example.tvslauncher.Server;

import android.content.Context;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

import ru.skornei.restserver.annotations.Accept;
import ru.skornei.restserver.annotations.ExceptionHandler;
import ru.skornei.restserver.annotations.Produces;
import ru.skornei.restserver.annotations.RequiresAuthentication;
import ru.skornei.restserver.annotations.RestController;
import ru.skornei.restserver.annotations.methods.GET;
import ru.skornei.restserver.annotations.methods.POST;
import ru.skornei.restserver.server.dictionary.ContentType;
import ru.skornei.restserver.server.protocol.RequestInfo;
import ru.skornei.restserver.server.protocol.ResponseInfo;

@RestController("/ping")
public class PingController {

    @GET
    @RequiresAuthentication
    public void ping() {
        System.out.println("work!");
    }

    @POST
    @Produces(ContentType.APPLICATION_JSON)
    @Accept(ContentType.APPLICATION_JSON)
    public void test(Context context, RequestInfo request, ResponseInfo response, TestEntity testEntity) {

        Log.d("rdctfvygbuh", testEntity.getSpeed());

//        return new TestEntity(testEntity.getSpeed());
    }

    private static String getStackTrace(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    @ExceptionHandler
    @Produces(ContentType.TEXT_PLAIN)
    public void handleThrowable(Throwable throwable, ResponseInfo response) {
        String throwableStr = getStackTrace(throwable);
        response.setBody(throwableStr.getBytes());
    }
}
