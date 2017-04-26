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
        hack(byteArr);
    }
        /*char[] charArray = new char[actuallyRead];
        for (int i = 0; i < charArray.length; i++) {

            charArray[i] = (char) buffer[i];
        }*/

        public void hack(byte[] arr) {
            boolean stop=false;
            for (int i = 0; i < 256||stop==true; i++) {
                for (int j = 0; j < arr.length; j++) {
                    arr[j]--;
                    if(j==arr.length)
                        stop=true;
                }
                int cnt = 0;
                String text = new String(arr);
                for (String s : Menu.listOfWords) {
                    if (DecryptionCaesar.containWord(s, text)) {
                        cnt++;
                        if (cnt==3)
                            listener.found(i+1, text);

                    }
                }
            }
        }


        public interface Listener {
            void found(int key, String text);
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




