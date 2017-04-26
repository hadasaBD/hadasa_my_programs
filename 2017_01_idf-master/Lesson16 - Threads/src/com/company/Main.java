package com.company;

public class Main {


    public static long count = 0;

    public static void main(String[] args) {
        /*MyClass myClass = new MyClass();
        myClass.run();
        Runnable myRunnable = new MyClass();
        myRunnable.run();
        Runnable myRunnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("running...");
            }
        };
        Runnable myRunnable3 = ()-> System.out.println("run2");
        myRunnable3.run();*/


        /*Thread thread1 = new Thread(()->slowRunningFunction(1));
        thread1.start();
        Thread thread2 = new Thread(()->slowRunningFunction(2));
        thread2.start();*/


        /*Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("thread1 " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                }
            }
        });
        thread1.start();
        //thread1.interrupt();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    if(i==5)
                        try {
                            thread1.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    System.out.println("thread2 " + i);
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {

                    }
                }
            }
        });
        thread2.start();*/



        /*System.out.println("first call to slowRunningFunction:");
        slowRunningFunction(1);
        System.out.println("second call to slowRunningFunction:");
        slowRunningFunction(2);*/

        //System.out.println("done calling slowRunningFunction");


        /*MyThread myThread = new MyThread();
        myThread.start();*/


        //int[] arr = {5, 2, 3, 6, 8, 4, 2, 4, 6};
        //search(arr, 6);

        /*Thread t = new Thread(()-> System.out.println("test"));
        t.setName("my thread");
        t.getName();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        t.setPriority(Thread.MAX_PRIORITY);*/

        //System.out.println(Runtime.getRuntime().availableProcessors());


        /*MyTomato myTomato = new MyTomato();
        myTomato.start();

        Tomato tomato = new Tomato(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable tomato");
            }
        });

        tomato.start();*/


        /*Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {

                    long temp;
                    synchronized (this){
                        count++;
                        temp = count;


                    }
                    System.out.print(temp + " ");
                }

            }
        };

        for (int i = 0; i < 1; i++) {
            count = 0;
            Thread thread1 = new Thread(runnable);
            Thread thread2 = new Thread(runnable);
            Thread thread3 = new Thread(runnable);
            thread1.start();
            thread2.start();
            thread3.start();
            try {
                thread1.join();
                thread2.join();
                thread3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count != 9){
                System.out.println();
                System.out.println("count is " + count);
            }
        }*/





    }


    public static void TheRaceProblem(){

    }


    public static int slowRunningFunction(int x){

        System.out.println("starting " + x);
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100000000; j++) {
                if(j % 2 == 0)
                    sum++;
                else
                    sum--;
            }
        }
        System.out.println("done " + x);
        return sum;
    }

    public static void search(int[] arr, int num){


        class SearchFoundListener implements SearchThread.FoundListener{
            boolean f = false;
            int index;
            SearchThread thread1, thread2;

            public void setThread1(SearchThread thread1) {
                this.thread1 = thread1;
            }

            public void setThread2(SearchThread thread2) {
                this.thread2 = thread2;
            }

            @Override
            public void found(int index, SearchThread searchThread) {
                if(!f){
                    f = true;
                    if(index == -1){
                        this.index = -1;
                    }else{
                        System.out.println("found at " + index);
                        this.index = index;
                        if(searchThread == thread1)
                            thread2.stopSearching();
                        else
                            thread1.stopSearching();
                    }
                }else{
                    if(index == -1){
                        if(this.index == -1){
                            System.out.println("not found");
                        }else{

                        }
                    }else{
                        if(this.index == -1){
                            System.out.println("found at " + index);
                        }else{

                        }
                    }
                }
            }
        }


        SearchFoundListener listener = new SearchFoundListener();
        int n = arr.length-1;
        SearchThread searchThread1 =
                new SearchThread(arr, 0, n/2, num, listener);
        SearchThread searchThread2 =
                new SearchThread(arr, n/2+1, n, num, listener);
        listener.setThread1(searchThread1);
        listener.setThread2(searchThread2);
        searchThread1.start();
        searchThread2.start();
    }




}

class MyClass implements Runnable{

    @Override
    public void run() {
        System.out.println("running...");
    }
}








class Tomato{

    private Runnable runnable;

    public Tomato(){

    }

    public Tomato(Runnable runnable){
        this.runnable = runnable;
    }


    public void run(){

    }

    public final void start(){
        if(runnable != null)
            runnable.run();
        else
            run();
    }
}


class MyTomato extends Thread{


    @Override
    public void run() {
        System.out.println("run MyTomato");
    }
}










