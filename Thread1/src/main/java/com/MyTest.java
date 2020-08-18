package com;

import org.java_websocket.drafts.Draft_6455;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

public class MyTest implements Runnable {

    @Override
    public void run() {
        try {
            new Client1(new URI("ws://localhost:1423"),new Draft_6455()).doIt();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        for (int i=0;i<2;i++){
//            new Thread(new MyTest()).start();
//        }






    }
}
