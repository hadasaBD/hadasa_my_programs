package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static final int PORT = 3000;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while(true) {
                System.out.println("waiting for incoming communication...");
                Socket socket = serverSocket.accept();
                System.out.println("client connected");
                ClientThread clientThread = new ClientThread(socket);
                clientThread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

