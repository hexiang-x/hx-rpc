package com.hexiang.hxrpc.server;

import io.vertx.core.Vertx;

public class VertxHttpServer implements HttpServer{
    @Override
    public void doStart(int port) {
        Vertx vertx = Vertx.vertx();
        io.vertx.core.http.HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(
                httpServerRequest -> {
                    System.out.println("Request Param " + httpServerRequest.method() + " " + httpServerRequest.uri());
                    httpServerRequest.response().putHeader("content-type", "text/plain")
                            .end("Hello from vertx server");
                }
        );

        httpServer.requestHandler(new HttpServerHandle());

        httpServer.listen(port, httpServerAsyncResult -> {
            if(httpServerAsyncResult.succeeded()){
                System.out.println("Server is now listening " + port);
            }else{
                System.out.println("Server start failed");
            }
        });
    }
}
