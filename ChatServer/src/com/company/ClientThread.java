package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by hackeru on 3/21/2017.
 */
public class ClientThread extends Thread {

    public static final int SEND_MESSAGE = 100;
    public static final int GET_MESSAGE = 101;
    public static final int OKAY = 90;

    private Socket clientSocket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private List<String> messages;

    public ClientThread(Socket clientSocket,List<String> massages) {
        this.clientSocket = clientSocket;
        this.messages = massages;
    }

    @Override

    public void run() {
        try {
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            int action = inputStream.read();
            switch (action){
                case SEND_MESSAGE:
                    sendMassage();
                    break;
                case GET_MESSAGE:
                    getMassage();
                    break;
            }


            /*byte[] buffer = new byte[1024];
            outputStream.write("please choose:\n 1. SignUp \n 2. logIn".getBytes());
            int actuallyRead = inputStream.read(buffer);
            String messageFromClient =
                    new String(buffer, 0, actuallyRead);
            System.out.println(messageFromClient);
            outputStream.write("thank you client!".getBytes());*/



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
    private void sendMassage() throws IOException {
        int massageLength = inputStream.read();
        if (massageLength == -1)
            return;
        byte[] massageBytes = new byte[massageLength];
        int actuallyRead = inputStream.read(massageBytes);
        if (actuallyRead != massageLength)
            return;
        String massage = new String(massageBytes);
        messages.add(massage);
        outputStream.write(OKAY);
    }
    private void getMassage() throws IOException {
        byte[] massageFromBytes = new byte[4];
        int actuallyRead = inputStream.read(massageFromBytes);
        if (actuallyRead != 4)
            return;
        int massageFrom = ByteBuffer.wrap(massageFromBytes).getInt();
        for (int i = massageFrom; i < messages.size() ; i++) {
            String message = messages.get(i);
            byte[] messageBytes = message.getBytes();
            outputStream.write(messageBytes.length);
            outputStream.write(messageBytes);
        }
    }
}
