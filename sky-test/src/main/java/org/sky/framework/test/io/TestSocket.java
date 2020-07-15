package org.sky.framework.test.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author roc
 * @since 2020/4/24 15:26
 */
public class TestSocket {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8090);
        System.out.println("Step1: new ServerSocket(80) ");
        new Thread(() -> {
            try {
                Socket socket = server.accept();
                InputStream in = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while (true){
                    System.out.println(reader.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
