package com.company;
import java.io.*;

/**
 * Created by MyUser on 18/04/2017.
 */
public class DecryptionCaesar extends File {
    MyThread.Listener listener = new MyThread.Listener() {
        OutPut forOutPut = new OutPut();
        Input forInput = new Input();
        @Override
        public void found(int key, String text) {
            forOutPut.output("Key suspect is:"+key);
            forOutPut.output("the text is:"+text);
        }
    };

    public DecryptionCaesar(String pathname) {
        super(pathname);
    }

    public File getPathFromInput(String path) {
        OutPut forOutPut = new OutPut();
        Input forInput = new Input();
        File file = new File(path);
        if (!file.exists()) {
            forOutPut.output("is not file or file not exists,please enter again");
            String path2 = forInput.input();
            getPathFromInput(path2);
        }
        try {
            action(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return file;
    }

    public void action(File file) throws IOException {

        InputStream inputStream = null;
        inputStream = new FileInputStream(file);
        int actuallyRead = 0;
        byte[] buffer = new byte[1024];
        actuallyRead = inputStream.read(buffer);
        byte[] byteArr = new byte[actuallyRead];
        for (int i = 0; i < actuallyRead; i++) {
            byteArr[i] = buffer[i];
        }
        /*char[] charArray = new char[actuallyRead];
        for (int i = 0; i < charArray.length; i++) {

            charArray[i] = (char) buffer[i];
        }*/
        int from=0;
        int middle = 256 / 2;
        int end=256;
        MyThread thread1 = new MyThread(byteArr, Menu.listOfWords, from, middle, listener);
        MyThread thread2 = new MyThread(byteArr, Menu.listOfWords, middle, end, listener);
        thread1.start();
        thread2.start();

    }

    public static boolean containWord(String s, String text) {
        int index = text.indexOf(s);
        if (index == -1)
            return false;
        if (index != 0) {
            char charBefore = text.charAt(index - 1);
            if (charBefore != '.' && charBefore != ' ' && charBefore != ',')
                return false;
        }
        return true;
    }


}




