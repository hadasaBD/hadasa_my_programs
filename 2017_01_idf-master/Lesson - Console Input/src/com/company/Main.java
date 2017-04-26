package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidParameterException;

public class Main {

    static List<User> users;
    static User loggedInUser;

    public static void main(String[] args) {
        users = new LinkedList<>();
        loggedInUser = null;
        printMenu();
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("please choose:");
        System.out.println("1. sign up");
        System.out.println("2. log in");
        System.out.println("3. log out");
        System.out.println("4. reverse string");
        System.out.println("0. exit");
        System.out.println("your choice: ");
        String input = readInput();
        if (input.length() != 0) {
            switch (input) {
                case "1":
                    signup(0);
                    break;
                case "2":
                    login(0);
                    break;
                case "3":
                    logout();
                    break;
                case "4":
                    reverseString();
                    break;
                case "0":
                    System.out.println("bye bye.");
                    return;
                default:
                    System.out.println("invalid option. try again.");
            }
        }
        printMenu();
    }

    static void signup(int counter) {
        System.out.println();
        System.out.println("please enter a username: ");
        String userName = readInput();
        System.out.println();
        if (!User.isUserNameValid(userName)) {
            System.out.println("username is invalid. try again");
            if (counter < 3)
                signup(counter + 1);
            return;
        }

        User u = User.getUser(users, userName);
        if (u != null) {
            System.out.println("username is taken. choose another'");
            if (counter < 3)
                signup(counter + 1);
            return;
        }

        String password = null;
        String repeatPassword = null;
        do {
            if (password != null) {
                System.out.println("must repeat password exactly");
            }
            System.out.println("please enter password: ");
            password = readInput();
            if (!User.isPasswordValid(password)) {
                System.out.println("password is invalid. try again");
                if (counter < 3)
                    signup(counter + 1);
                return;
            }
            System.out.println();
            System.out.println("please enter password again: ");
            repeatPassword = readInput();
        } while (!password.equals(repeatPassword));

        loggedInUser = new User(userName, password);
        users.add(loggedInUser);
        System.out.println("welcome.");
    }




    static void login(int counter){
        loggedInUser = null;
        System.out.println();
        System.out.print("please enter a username: ");
        String userName = readInput();
        if (!User.isUserNameValid(userName)) {
            System.out.println("username is invalid. try again");
            if (counter < 3)
                login(counter + 1);
            return;
        }
        User u = User.getUser(users, userName);
        System.out.print("please enter password: ");
        String password = readInput();
        if (!User.isPasswordValid(password)) {
            System.out.println("password is invalid. try again");
            if (counter < 3)
                login(counter + 1);
            return;
        }
        System.out.println();
        if(u == null || !u.getPassword().equals(password)){
            System.out.println("login failed. try again");
            if (counter < 3)
                login(counter + 1);
            return;
        }
        loggedInUser = u;
        System.out.println("welcome back " + userName);
    }

    static void logout(){
        if(loggedInUser == null){
            System.out.println("no user is logged in");
            return;
        }
        loggedInUser = null;
        System.out.println("bye bye, see you later");
    }

    static void reverseString(){
        if(loggedInUser == null){
            System.out.println("you must log in first");
            return;
        }
        System.out.print("please enter a string: ");
        String s = readInput();
        char[] chars = s.toCharArray();
        for (int i = chars.length-1; i >= 0; i--) {
            System.out.print(chars[i]);
        }
        System.out.println();
    }

    public static String readInput(){
        String input = null;
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            input = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }


}
