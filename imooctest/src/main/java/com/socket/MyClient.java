package com.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {

    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 5531;
        Socket socket = new Socket(host,port);
        OutputStream outputStream = socket.getOutputStream();
//        String message = "我是daine";
        Scanner scanner = new Scanner(System.in);
        String message = scanner.next();
        outputStream.write(message.getBytes("utf-8"));
        outputStream.close();

    }

}
