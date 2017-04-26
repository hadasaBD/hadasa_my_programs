package com.company;

/**
 * Created by eladlavi on 15/03/2017.
 */
public class Menu2 {

    private static final int INITIAL_STATE = 1;
    private static final int GET_PATH = 2;


    private UserInterface userInterface;
    private int state;

    private MathOperations mathOperations;

    private Dog d;

    public void setD(Dog d) {
        this.d = d;
    }

    public Menu2(UserInterface userInterface) {
        this.userInterface = userInterface;

    }


    public void setMathOperations(MathOperations mathOperations) {
        this.mathOperations = mathOperations;
    }

    public void start(){
        if(this.userInterface != null){
            this.userInterface.output("please choose:\n 1. encryption\n 2. decryption\n 0.exit");
            String input = this.userInterface.input();
        }
    }

    public UserInterface getUserInterface() {
        return userInterface;
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public static interface UserInterface{
        void output(String s);
        String input();
    }


    public int stam(int x, int y){
        if(mathOperations != null)
            return mathOperations.add(x , y) * 2;
        return 18;
    }

    public int stam2(){
        return d.bark()*2;

        //when(server.login("elad","12345").thenReturn(true)
    }




}
