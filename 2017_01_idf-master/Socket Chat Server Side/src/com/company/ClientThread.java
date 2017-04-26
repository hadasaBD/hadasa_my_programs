package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/**
 * Created by eladlavi on 28/03/2017.
 */
public class ClientThread extends Thread {

    public static final int SEND_MESSAGE = 100;
    public static final int GET_MESSAGES = 101;
    public static final int SIGN_UP = 102;
    public static final int LOGIN = 103;
    public static final int OKAY = 90;
    public static final int FAILURE = 91;
    private Socket clientSocket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private List<Message> messages;
    private Map<String, String> users;

    public ClientThread(Socket clientSocket, List<Message> messages, Map<String, String> users) {
        this.clientSocket = clientSocket;
        this.messages = messages;
        this.users = users;
    }

    @Override
    public void run() {
        try {
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            int action = inputStream.read();
            switch (action){
                case SEND_MESSAGE:
                    sendMessage();
                    break;
                case GET_MESSAGES:
                    getMessages();
                    break;
                case SIGN_UP:
                    signup();
                    break;
                case LOGIN:
                    login();
                    break;
            }
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

    private void sendMessage() throws IOException {
        User user = readUserFromStream();
        if(!validUser(user))
            return;

        int messageLength = inputStream.read();
        if(messageLength == -1)
            return;
        byte[] messageBytes = new byte[messageLength];
        int actuallyRead = inputStream.read(messageBytes);
        if(actuallyRead != messageLength)
            return;
        String message = new String(messageBytes);
        messages.add(new Message(user.getUserName(), message));
        outputStream.write(OKAY);
    }



    private void login() throws IOException {
        User user = readUserFromStream();
        outputStream.write(validUser(user) ? OKAY : FAILURE);

    }

    private boolean validUser(User user){
        if(user == null)
            return false;
        String existingPassword =
                users.get(user.getUserName());
        return existingPassword != null &&
                existingPassword.equals(
                        user.getPassword());
    }

    private void signup() throws IOException {
        User user = readUserFromStream();
        if(user == null)
            return;
        boolean success = false;
        synchronized (users){
            if(!users.containsKey(user.getUserName())){
                users.put(user.getUserName(), user.getPassword());
                success = true;
            }
        }
        outputStream.write(success ? OKAY : FAILURE);
    }

    private void getMessages() throws IOException {
        User user = readUserFromStream();
        if(!validUser(user))
            return;
        byte[] messageFromBytes = new byte[4];
        int actuallyRead = inputStream.read(messageFromBytes);
        if(actuallyRead != 4)
            return;
        int messageFrom = ByteBuffer.wrap(messageFromBytes).getInt();
        for (int i = messageFrom; i < messages.size(); i++) {
            String message = messages.get(i).getContent();
            byte[] messageBytes = message.getBytes();
            outputStream.write(messageBytes.length);
            outputStream.write(messageBytes);
            byte[] messageSenderBytes = messages.get(i).getSender().getBytes();
            outputStream.write(messageSenderBytes.length);
            outputStream.write(messageSenderBytes);
        }
    }


    private User readUserFromStream() throws IOException {
        User user = new User();
        int userNameLength = inputStream.read();
        if(userNameLength == -1)
            return null;
        byte[] userNameBytes = new byte[userNameLength];
        int actuallyRead = inputStream.read(userNameBytes);
        if(actuallyRead != userNameLength)
            return null;
        user.setUserName(new String(userNameBytes));
        int passwordLength = inputStream.read();
        if(passwordLength == -1)
            return null;
        byte[] passwordBytes = new byte[passwordLength];
        actuallyRead = inputStream.read(passwordBytes);
        if(actuallyRead != passwordLength)
            return null;
        user.setPassword(new String(passwordBytes));
        return user;
    }
}


