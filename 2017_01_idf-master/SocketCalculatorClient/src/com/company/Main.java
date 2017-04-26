package com.company;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class Main {

    public static final int PLUS = 80;
    public static final int MINUS = 81;
    public static final int MULTIPLY = 82;
    public static final int DIVIDE = 83;
    public static final String SERVER = "127.0.0.1";
    public static final int PORT = 3000;

    public static void main(String[] args) {
        boolean go = true;
        while(go) {
            printMainMenu();
            String input = getInputFromUser();
            int action = 0;
            switch (input){
                case "+":
                    action = PLUS;
                    break;
                case "-":
                    action = MINUS;
                    break;
                case "*":
                    action = MULTIPLY;
                    break;
                case "/":
                    action = DIVIDE;
                    break;
                case "exit":
                    go = false;
                    System.out.println("bye bye, see you later.");
                    break;
                default:
                    System.out.println("illegal input");
            }
            if(action != 0){
                System.out.println("please enter first operand(integer):");
                String firstOperandString = getInputFromUser();
                int firstOperand = 0;
                try{
                    firstOperand = Integer.valueOf(firstOperandString);
                }catch (Exception ex){
                    System.out.println("you have not entered a valid integer");
                    continue;
                }
                System.out.println("please enter second operand(integer):");
                String secondOperandString = getInputFromUser();
                int secondOperand = 0;
                try{
                    secondOperand = Integer.valueOf(secondOperandString);
                }catch (Exception ex){
                    System.out.println("you have not entered a valid integer");
                    continue;
                }
                if(action == DIVIDE && secondOperand == 0){
                    System.out.println("division by zero is illegal");
                    continue;
                }
                try {
                    int result = sendToServer(action, firstOperand, secondOperand);
                    System.out.println("result is " + result);
                } catch (Exception e) {
                    System.out.println("error: " + e.getMessage());
                }

            }
        }

    }

    private static int sendToServer(int action, int firstOperand, int secondOperand) throws Exception {
        byte[] xBytes = new byte[4];
        byte[] yBytes = new byte[4];
        ByteBuffer.wrap(xBytes).putInt(firstOperand);
        ByteBuffer.wrap(yBytes).putInt(secondOperand);

        Socket socket = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try{
            socket = new Socket(SERVER, PORT);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            outputStream.write(action);
            outputStream.write(xBytes);
            outputStream.write(yBytes);
            byte[] resultBytes = new byte[4];
            int actuallyRead = inputStream.read(resultBytes);
            if(actuallyRead != 4)
                throw new Exception("something went wrong with the server");
            else{
                int result = ByteBuffer.wrap(resultBytes).getInt();
                return result;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
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
        throw new Exception("something went wrong with the server");
    }

    private static void printMainMenu() {
        System.out.println("please type:");
        System.out.println("+ for addition");
        System.out.println("- for subtraction");
        System.out.println("* for multiplication");
        System.out.println("/ for division");
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
