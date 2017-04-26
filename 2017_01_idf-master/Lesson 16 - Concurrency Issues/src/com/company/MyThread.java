package com.company;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eladlavi on 08/03/2017.
 */
public class MyThread extends Thread {

    Map<String, String> users;
    String userName, password;
    MyInteger myInteger;

    public MyThread(Map<String, String> users, String userName, String password, MyInteger myInteger) {
        this.users = users;
        this.userName = userName;
        this.password = password;
        this.myInteger = myInteger;
    }

    @Override
    public void run() {
        boolean success = false;
        synchronized (users) {
            if (!users.containsKey(userName)) {
                users.put(userName, password);
                success = true;
            }
        }
        if(success){
            //update the user for success
        }else{
            //update the user that this username is taken
        }




    }
}












