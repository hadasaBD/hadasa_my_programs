package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final int PORT = 3000;
    public static void main(String[] args) {
	// write your code here
        ServerSocket serverSocket=null;
        try {
            serverSocket = new ServerSocket(PORT);
            List<String> massages =new  ArrayList<>();
            while(true) {
                System.out.println("waiting for incoming communication...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("client connected");
                ClientThread clientThread = new ClientThread(clientSocket,massages);
                clientThread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
