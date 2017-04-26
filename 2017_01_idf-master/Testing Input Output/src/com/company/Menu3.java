package com.company;

/**
 * Created by eladlavi on 16/03/2017.
 */
public class Menu3 {

    private UserInterface userInterface;

    public Menu3(UserInterface userInterface) {
        this.userInterface = userInterface;
    }



    public void start(){
        userInterface.input();
        userInterface.output("potato");
        userInterface.input();
        userInterface.output("tomato");
        userInterface.input();
        userInterface.output("mockito");
    }
}
