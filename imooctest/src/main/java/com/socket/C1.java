package com.socket;

import org.apache.commons.lang.text.StrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class C1 {
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 1111;
        Socket socket = new Socket(host,port);
        OutputStream outputStream = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        outputStream.write(s.getBytes("utf-8"));
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        StrBuilder strBuilder = new StrBuilder();
        while (inputStream.read(bytes) != -1){
            strBuilder.append(new String(bytes,"utf-8"));
        }
        System.out.println("客户端收到的消息是"+strBuilder);
    }


}
