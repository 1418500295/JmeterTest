package com.socket;

import org.apache.commons.lang.text.StrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) throws IOException {
        //监听指定的端口
        int port = 5531;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("server一直等待连接的到来");
        Socket socket = serverSocket.accept();
        //建立连接后，从socket中获取输入流，并建立缓冲区读取
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        StrBuilder strBuilder = new StrBuilder();
        while ((len = inputStream.read(bytes)) != -1){
            strBuilder.append(new String(bytes, 0, len,"utf-8"));
        }
        System.out.println("get message from client:"+strBuilder);
        inputStream.close();
        socket.close();
        serverSocket.close();

    }
}
