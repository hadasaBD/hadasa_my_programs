package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by eladlavi on 14/03/2017.
 */
public class ClientThread extends Thread {

    public static final int PLUS = 80;
    public static final int MINUS = 81;
    public static final int MULTIPLY = 82;
    public static final int DIVIDE = 83;
    private Socket clientSocket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        inputStream = null;
        outputStream = null;
    }

    @Override
    public void run() {
        try{
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            int action = inputStream.read();
            switch (action){
                case PLUS:
                    plus();
                    break;
                case MINUS:
                    minus();
                    break;
                case MULTIPLY:
                    multiply();
                    break;
                case DIVIDE:
                    divide();
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


    void plus() throws IOException{
        Operands operands = getOperands();
        sendResult(operands.x + operands.y);
    }

    void minus() throws IOException{
        Operands operands = getOperands();
        sendResult(operands.x - operands.y);
    }

    void multiply() throws IOException{
        Operands operands = getOperands();
        sendResult(operands.x * operands.y);
    }

    void divide() throws IOException{
        Operands operands = getOperands();
        if(operands.y != 0)
            sendResult(operands.x / operands.y);
    }


    private void sendResult(int result) throws IOException {
        byte[] resultBytes = new byte[4];
        ByteBuffer.wrap(resultBytes).putInt(result);
        outputStream.write(resultBytes);
    }

    private Operands getOperands() throws IOException {
        byte[] operandBytes = new byte[4];
        int actuallyRead;
        Operands operands = new Operands();
        actuallyRead = inputStream.read(operandBytes);
        if(actuallyRead != 4)
            throw new IOException("illegal input");
        operands.x = ByteBuffer.wrap(operandBytes).getInt();

        actuallyRead = inputStream.read(operandBytes);
        if(actuallyRead != 4)
            throw new IOException("illegal input");
        operands.y = ByteBuffer.wrap(operandBytes).getInt();

        return operands;
    }




    static class Operands{
        int x;
        int y;
    }
}










