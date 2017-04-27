package com.company;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static final int PORT = 3000;




    public static void main(String[] args) {
	// write your code here
       // String  userName;
      //  String  password;
        boolean go = true;
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            User u = new User();
            // 127.0.0.1 this is ip server
            // אפשר לשנות את כתובת ה IP לפי איזה שרת שרוצים כמו של שרה 10.0.11.4
            socket = new Socket("127.0.0.1", PORT);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            printMainMenu();
            //outputStream.write("hi server, how are you?".getBytes());
            byte[] buffer = new byte[1024];
            int choice = inputStream.read(buffer);
            while (go){
            switch (choice){
                case 1:
                    outputStream.write("please enter user name".getBytes());
                    u.userName=getInputFromUser();
                    outputStream.write("please enter password".getBytes());
                    u.password = getInputFromUser();
                     if(!register(u.userName,u.password)) {
                         go = true;
                         continue;
                     }
                     else
                         go= false;
                     break;

                case 2:
                    outputStream.write("please enter user name".getBytes());
                    u.userName=getInputFromUser();
                    outputStream.write("please enter password".getBytes());
                    u.password = getInputFromUser();
                    login(u.userName,u.password);
                    break;
                    default:
                        go=false;
                        break;
            }
            }
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

    private static boolean register(String  userName,String  password) {

        User user = new User(userName, password);
        if (user.isUserNameValid(userName) && user.isPasswordValid(password)) {
            user.dataUser.put(userName, password);
            return true;
        } else
            return false;
    }

    private static void login(String  userName,String  password) {
    }




    private static void printMainMenu() {
        System.out.println("please choose:\\n 1. SignUp \\n 2. logIn");
        System.out.println("type 'exit' to exit");
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
