package com.im.socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static String host = "127.0.0.1";
    static int port = 12345;

    public static void main(String[] args) {
        NettyServer server = new NettyServer(port);
        server.run();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        NettyClient client = new NettyClient(host, port);
        client.connect();

//        ExecutorService service = Executors.newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
//            service.execute(new Runnable() {
//                @Override
//                public void run() {
//                    NettyClient client = new NettyClient(host, port);
//                    client.connect();
//                }
//            });
//        }
    }

}
