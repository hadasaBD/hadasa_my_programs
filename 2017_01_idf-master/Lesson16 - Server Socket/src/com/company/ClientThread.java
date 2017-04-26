package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by eladlavi on 08/03/2017.
 */
public class ClientThread extends Thread {

    Socket clientSocket;
    InputStream inputStream;
    OutputStream outputStream;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            byte[] buffer = new byte[1024];
            int actuallyRead = inputStream.read(buffer);
            String messageFromClient =
                    new String(buffer, 0, actuallyRead);
            System.out.println(messageFromClient);
            outputStream.write("thank you client!".getBytes());



        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(clientSocket != null)
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }
}
