package com.company;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        /*MyThread myThread1 = new MyThread();
        myThread1.start();
        MyThread myThread2 = new MyThread();
        myThread2.start();*/

        ScreenInputOutput screen = new ScreenInputOutput();
        Menu menu = new Menu(screen,screen);
        menu.printMenu();

    }
}


//my test

/*
Press a number from the menu shown:
 1. Insert a new common word
 2. Enter a path to an encrypted file
 3. Print common word list
 4. exit from program
1
Enter a list of new common words separated by a comma
hadasa,ben,david
Press a number from the menu shown:
 1. Insert a new common word
 2. Enter a path to an encrypted file
 3. Print common word list
 4. exit from program
2
Enter a path to the file
C:\Users\hbd.hbd-PC\Desktop\java project\hbd_encrypted.txt
The key is : 135
hadasa ben david
Press a number from the menu shown:
 1. Insert a new common word
 2. Enter a path to an encrypted file
 3. Print common word list
 4. exit from program
1
Enter a list of new common words separated by a comma
a,be,to,in,the
Press a number from the menu shown:
 1. Insert a new common word
 2. Enter a path to an encrypted file
 3. Print common word list
 4. exit from program
3
the a be in hadasa ben david to
Press a number from the menu shown:
 1. Insert a new common word
 2. Enter a path to an encrypted file
 3. Print common word list
 4. exit from program
1
Enter a list of new common words separated by a comma
we
Press a number from the menu shown:
 1. Insert a new common word
 2. Enter a path to an encrypted file
 3. Print common word list
 4. exit from program
1
Enter a list of new common words separated by a comma
a,to,are
error, The word a is present
error, The word to is present
Press a number from the menu shown:
 1. Insert a new common word
 2. Enter a path to an encrypted file
 3. Print common word list
 4. exit from program
3
the a be in are hadasa ben david to we
Press a number from the menu shown:
 1. Insert a new common word
 2. Enter a path to an encrypted file
 3. Print common word list
 4. exit from program
1
Enter a list of new common words separated by a comma
      ,to,
error, The word to is present
Press a number from the menu shown:
 1. Insert a new common word
 2. Enter a path to an encrypted file
 3. Print common word list
 4. exit from program
3
the a be in are hadasa ben david to we
Press a number from the menu shown:
 1. Insert a new common word
 2. Enter a path to an encrypted file
 3. Print common word list
 4. exit from program
4
bye bye.

Process finished with exit code 0
 */
