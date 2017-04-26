package com.company;

import java.io.File;

/**
 * Created by hackeru on 4/6/2017.
 */
public class Menu implements DecryptionHack.MatchFound {
    private static final String ADD_WORD="1";
    private static final String PRINT="2";
    private static final String DECRYPT="3";
    public static final String EXIT="4";
    private OutputInterface output;
    private InputInterface input;
    private Dictionary dictionary;
    private DecryptionHack decryptionHack;

    public Menu() {
        this.output = new Output();
        this.input = new Input();
        dictionary = new Dictionary(output);
    }

    public void getMenu() {
        String choice;
        while (true) {
            output.getOutput("print 1.add word\n2.print words\n3.hack key\n4.exit");
            choice = input.getInput();
            switch (choice) {
                case ADD_WORD:
                    output.getOutput("enter word");
                    dictionary.addWords(input.getInput());
                    break;
                case PRINT:
                    dictionary.printWords();
                    break;
                case DECRYPT:
                    output.getOutput("enter path");
                    String path = input.getInput();
                    File file = new File(path);
                    if (!file.exists() && file.isFile())
                        output.getOutput("error input");
                    decryptionHack = new DecryptionHack(file, dictionary.getListWord(), this);
                    decryptionHack.decrypt();
                    break;
                case EXIT:
                    break;
                default:
                    output.getOutput("error choice");

            }
            if (choice.equals(EXIT)) {
                if (decryptionHack != null)
                    decryptionHack.stop();
                output.getOutput("bye");
                break;
            }

        }
    }

    @Override
    public void found(byte key, String file) {
        output.getOutput("key:" + key + " the file:" + file);
    }
}
