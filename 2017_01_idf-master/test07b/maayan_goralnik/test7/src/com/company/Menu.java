package com.company;

import java.io.File;

/**
 * Created by hackeru on 4/6/2017.
 */
public class Menu implements DecryptionHack.MatchFound{
    private  OutputInterface output;
    private  InputInterface input;
    private  Dictionary dictionary;
    private  DecryptionHack  decryptionHack;

    public Menu() {
        this.output =new Output() ;
        this.input=new Input();
        dictionary=new Dictionary(output);
    }

    public  void getMenu(){
        String choice;
        while (true) {
            output.getOutput("print 1.add word\n2.print words\n3.hack key\n4.exit");
            choice=input.getInput();
            switch (choice) {
                case "1":
                    output.getOutput("enter word");
                    dictionary.addWords(input.getInput());
                    break;
                case "2":
                    dictionary.printWords();
                    break;
                case"3":
                    output.getOutput("enter path");
                    String path=input.getInput();
                    File file=new File(path);
                    if(!file.exists()&&file.isFile())
                        output.getOutput("error input");
                    decryptionHack=new DecryptionHack(file,dictionary.getListWord(),this);
                    decryptionHack.decrypt();
                    break;
                case "4":
                    break;
                default:
                    output.getOutput("error choice");

            }
           if(choice.equals("4")){
               if(decryptionHack!=null)
                   decryptionHack.stop();
               output.getOutput("bye");
               break;
           }

        }
    }


    @Override
    public void found(int key,String file) {
        output.getOutput("key:"+key+" the file:"+file);
    }
}
