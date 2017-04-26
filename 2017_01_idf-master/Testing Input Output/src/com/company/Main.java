package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static final String EXIT = "exit";

    private Menu menu;

    public Main(){
        menu = new Menu();
    }
    public static void main(String[] args) {

        //Main main = new Main();
        //main.userInterface();

        /*Menu2 menu2 = new Menu2(new Menu2.UserInterface() {
            @Override
            public void output(String s) {
                System.out.println(s);
            }

            @Override
            public String input() {
                return getInputFromUser();
            }
        });*/


        Menu3 menu3 = new Menu3(new UserInterface() {
            @Override
            public void output(String s) {
                System.out.println(s);
            }

            @Override
            public String input() {
                return getInputFromUser();
            }
        });

        menu3.start();



    }

    public void userInterface(){
        String input;
        System.out.println(menu.getInitialInstructions());
        while ((input = getInputFromUser()) != EXIT){
            System.out.println(menu.processInput(input));
        }

    }


    public static String getInputFromUser(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {

        }
        return EXIT;
    }




}
