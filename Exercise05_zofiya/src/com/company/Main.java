package com.company;

public class Main {

    public static void main(String[] args) {

        /*byte b=127;
        b=(byte)((int)b+200);
        System.out.println(b);
        b=(byte)((int)b-200);
        System.out.println(b);*/



        /*int key=6;
        int original=250;
        int en=(original*key)&0x000000FF;//מספר בין 0 ל255
        int decryptionKey=0;
        for (int i = 1; i <=255 ; i++) {
            int k= i*key;
            if(((i*key)&0x000000FF)==1) {
                decryptionKey=i;
                break;
            }
        }
        int decrypted=(en*decryptionKey)&0x000000FF;*/

        Menu myMenu = new Menu(new ScreenOutput(), new ScreenInput());
        Algorithms.setListener(myMenu);
        myMenu.printMenu();
    }
}
