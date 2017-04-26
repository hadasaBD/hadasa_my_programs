package com.company

import org.junit.Test

/**
 * Created by hackeru on 4/6/2017.
 */
class T1Test extends GroovyTestCase {

    static String word=null;
    @Test
    void addWord() {
        Set<String> listWord;
        Set<String> list =new HashSet<>();
        OutputInterface outputInterface=new OutputInterface() {

            @Override
            void getOutput(String print) {
                if(!print.equals("success"))
                    fail();
            }
        }
        T1 t1=new T1(list,outputInterface);
        t1.addWord("and");
    }
}
