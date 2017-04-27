
package com.company;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import static com.company.GetMessagesThread.*;

public class Main {

    public static void main(String[] args) {
        GetMessagesThread getMessagesThread = new GetMessagesThread();
        getMessagesThread.start();

        String input;
        while(!(input = getInputFromUser()).equals("exit")){
            Socket clientSocket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try{
                clientSocket = new Socket(SERVER_IP, PORT);
                inputStream = clientSocket.getInputStream();
                outputStream = clientSocket.getOutputStream();
                outputStream.write(SEND_MESSAGE);
                byte[] inputBytes = input.getBytes();
                outputStream.write(inputBytes.length);
                outputStream.write(inputBytes);
                int result = inputStream.read();
                if(result != OKAY)
                    System.out.println("error sending message");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        getMessagesThread.stopGettingMessages();
    }

    private static String getInputFromUser(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}