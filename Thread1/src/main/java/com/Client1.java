package com;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class Client1 extends WebSocketClient {
    public Client1(URI serverUri, Draft_6455 draft_6455) {
        super(serverUri,draft_6455);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("连接打开");

    }

    @Override
    public void onMessage(String s) {
        System.out.println("收到服务端的消息是："+s);

    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("关闭连接");

    }

    @Override
    public void onError(Exception e) {

    }

    public  void doIt() {
        Client1 client1 = null;
        try {
            client1  = new Client1(new URI("ws://localhost:1423"),new Draft_6455());
            //发起连接
            client1.connect();
            while (!client1.getReadyState().equals(READYSTATE.OPEN)){
                System.out.println("正在连接。。。");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(client1).send("你好啊");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        client1.close();

    }
}
