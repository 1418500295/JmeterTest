package com.socket;

import org.apache.commons.lang.text.StrBuilder;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class S1 {

    public static void main(String[] args) throws IOException {
        int port = 1111;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        System.out.println("有新的客户端连接来自"+socket.getInetAddress());
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        StrBuilder strBuilder = new StrBuilder();
        while (inputStream.read(bytes) != -1){
            strBuilder.append(new String(bytes, "utf-8"));

        }
        System.out.println("服务端收到的信息是"+strBuilder);
        OutputStream outputStream = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        outputStream.write(s.getBytes());





    }


}
