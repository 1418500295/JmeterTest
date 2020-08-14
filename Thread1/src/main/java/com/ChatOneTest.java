package com;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ChatOneTest extends WebSocketClient {

    public ChatOneTest(URI serverUri, Draft_6455 draft) {
        super(serverUri,draft);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("连接打开...");

    }

    @Override
    public void onMessage(String s) {
        System.out.println("服务端返回:"+s);

    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("连接关闭");

    }

    @Override
    public void onError(Exception e) {

    }

    public static void main(String[] args) {
        ChatOneTest chatOneTest = null;
        try {
             chatOneTest = new ChatOneTest(new URI(""),new Draft_6455());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        chatOneTest.connect();
        //等待连接打开成功，再发送数据
        while (!chatOneTest.getReadyState().equals(READYSTATE.OPEN)){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("连接还没有打开。。");
        }
        System.out.println("连接成功");
//        chatOneTest.send("hello");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        chatOneTest.close();
        while (true){
            Scanner scanner = new Scanner(System.in);
            String sendMsg = scanner.next();
            chatOneTest.send(sendMsg);
        }
    }
}
