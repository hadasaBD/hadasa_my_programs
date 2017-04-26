package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Main {

    public static final int PORT = 3000;

    //Socket Client
    public static void main(String[] args) {
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            socket = new Socket("127.0.0.1", PORT);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            outputStream.write("hi server, how are you?".getBytes());
            byte[] buffer = new byte[1024];
            int actuallyRead = inputStream.read(buffer);
            String responseFromServer = new String(buffer, 0, actuallyRead);
            System.out.println(responseFromServer);
            inputStream.close();
            inputStream = null;
            outputStream.close();
            outputStream = null;
            socket.close();
            socket = null;
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
            if(socket != null)
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
