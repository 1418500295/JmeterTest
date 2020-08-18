package com;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class Server1 extends WebSocketServer {

    public Server1(int port) {
        super(new InetSocketAddress(port));
        System.out.println("服务端启动:"+port);

    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        System.out.println("来自新的客户端连接:"+webSocket.getRemoteSocketAddress()
        .getAddress().getHostAddress()+":"+webSocket.getRemoteSocketAddress()
        .getPort());

    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {

    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        System.out.println("客户端发送的信息是:"+s);
        //向客户端发送消息
        webSocket.send(s);

    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        e.printStackTrace();

    }

    @Override
    public void onStart() {

    }

    public static void main(String[] args) {
        Server1 server1 = new Server1(1423);
        server1.start();

    }
}
