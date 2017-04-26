package com.company;

/**
 * Created by hackeru on 4/20/2017.
 */
public class FindWordsThread extends Thread {

    private byte[] bytes;
    private MyWords words;
    private int from,to;
    private WordsFoundListener listener;

    public FindWordsThread(byte[] bytes, MyWords words, WordsFoundListener listener,int from,int to) {
        this.bytes = bytes;
        this.words = words;
        this.listener = listener;
        this.from=from;
        this.to=to;
    }

    @Override
    public void run() {

        Decryptor decryptor=new Decryptor();
        for (int i = from; i < to; i++) {
            byte[] decrypted = Decryptor.decrypt(bytes, i);
            int wordsInFile = 0;
            String strings = new String(decrypted);
            for (String word : words.getWords()) {
                int indexOf = strings.indexOf(word);
                if (indexOf != -1) {
                    int s= indexOf+word.length();
                    if ((indexOf == 0 || ((strings.charAt(indexOf - 1) == ' ') || (strings.charAt(indexOf - 1) == '.') || (strings.charAt(indexOf - 1) == ',')))
                        && ((indexOf+word.length()==strings.length())||(strings.charAt(indexOf+word.length())==' ')||(strings.charAt(indexOf+word.length())=='.')||(strings.charAt(indexOf+word.length())==',')))
                        wordsInFile++;
                }

            }
            if (wordsInFile >= 3) {
                if (listener != null) {
                    listener.keyIsFound(i, strings);
                    return;
                }

            }

        }
        listener.keyIsNotFound();

    }

    interface WordsFoundListener {
        void keyIsFound(int key, String string);

        void keyIsNotFound();
    }
}

