package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        /*Map<String, String> users = new HashMap<>();
        String userName = "elad";
        String password = "12345";
        MyInteger myInteger = new MyInteger();

        MyThread myThread1 = new MyThread(users, userName, password, myInteger);
        MyThread myThread2 = new MyThread(users, userName, password, myInteger);

        myThread1.start();
        myThread2.start();


        AtomicInteger atomicInteger = new AtomicInteger(0);
        int x = atomicInteger.incrementAndGet();
        atomicInteger.compareAndSet(5, 8);
        int num = 7;
        if(num == 5){
            num = 8;
        }*/


        //Semaphore:
        /*Semaphore machines = new Semaphore(2);
        PersonThread personThread1 = new PersonThread(machines, "Hadasa");
        PersonThread personThread2 = new PersonThread(machines, "Sarah");
        PersonThread personThread3 = new PersonThread(machines, "Shira");
        PersonThread personThread4 = new PersonThread(machines, "Noa");
        PersonThread personThread5 = new PersonThread(machines, "Maayan");
        personThread1.start();
        personThread2.start();
        personThread3.start();
        personThread4.start();
        personThread5.start();*/


        //CountDownLatch
        /*CountDownLatch counter = new CountDownLatch(7);
        //count from 5 to 0 and then start the race;
        new RunnerThread(counter, "Hadasa");
        new RunnerThread(counter, "Sarah");
        new RunnerThread(counter, "Shira");
        new RunnerThread(counter, "Noa");
        new RunnerThread(counter, "Maayan");

        System.out.println("Starting the countdown");
        long countValue = counter.getCount();
        while(countValue > 0){
            try {
                Thread.sleep(1000);//sleep for 1 second
            } catch (InterruptedException e) {

            }
            System.out.println(countValue);
            if(countValue == 1){
                //once counter.countDown(); in the next statement
                //is called, count down will reach zero, so shout "start"
                System.out.println("Start");
            }
            counter.countDown(); //count down by 1 for each second;
            countValue = counter.getCount();
        }*/


        //Exchanger:
        /*Exchanger<String> sillyTalk = new Exchanger<>();
        new FirstThread(sillyTalk).start();
        new SecondThread(sillyTalk).start();
        */

        //CyclicBarrier:
        /*//a mixed-double tennis game requires four players.
        // so wait for four players to join to start the game
        System.out.println("Reserving tennis court, as soon as four players arrive, game will start");
        CyclicBarrier barrier = new CyclicBarrier(4, new MixedDoubleTennisGame());
        new Player(barrier, "Sari").start();
        new Player(barrier, "Malki").start();
        new Player(barrier, "Sapir").start();
        new Player(barrier, "Zofiya").start();*/



    }



    //public static void stam() throws MyException1, MyException2 {
    public static void stam() throws Exception {
        if(5<6)
            throw new MyException1();
        else
            throw new MyException2();



    }



}


class MyInteger{
    int x;
}



class PersonThread extends Thread{
    private Semaphore machines;
    private String name;

    public PersonThread(Semaphore machines, String name) {
        this.machines = machines;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " waiting to access ATM machine");
        try {
            machines.acquire();
            System.out.println(name + " is accessing the ATM machine");
            Thread.sleep(20000);
            System.out.println(name + " is done using the ATM machine");
            machines.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}




class RunnerThread extends Thread{
    private CountDownLatch timer;
    private String name;

    public RunnerThread(CountDownLatch timer, String name) {
        this.timer = timer;
        this.name = name;
        System.out.println(name + " ready and waiting for the count down to start");
        start();
    }

    @Override
    public void run() {
        try {
            timer.await();
        } catch (InterruptedException e) {
            System.out.println("interrupted - can't start running the race");
        }
        System.out.println(name + " started running");
    }
}

class FirstThread extends Thread{
    private Exchanger<String> sillyTalk;

    public FirstThread(Exchanger<String> sillyTalk){
        this.sillyTalk = sillyTalk;
    }

    @Override
    public void run() {
        String reply = null;
        try{
            //start conversation with SecondThread
            reply = sillyTalk.exchange("knock knock");
            //now print the response from SecondThread
            System.out.println("SecondThread: " + reply);
            reply = sillyTalk.exchange("FirstThread!");
            System.out.println("SecondThread: " + reply);
            reply = sillyTalk.exchange("FirstThread that was here before you!");
            System.out.println("SecondThread: " + reply);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class SecondThread extends Thread{
    private Exchanger<String> sillyTalk;

    public SecondThread(Exchanger<String> sillyTalk) {
        this.sillyTalk = sillyTalk;
    }

    @Override
    public void run() {
        String reply = null;
        try{
            //exchange the first message:
            reply = sillyTalk.exchange("Who's there?");
            //print FirstThread response:
            System.out.println("FirstThread: " + reply);
            reply = sillyTalk.exchange("FirstThread who ?");
            System.out.println("FirstThread: " + reply);
            reply = sillyTalk.exchange("");
            System.out.println("FirstThread: " + reply);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MixedDoubleTennisGame extends Thread{
    @Override
    public void run() {
        System.out.println("all four players are ready. game can start");
    }
}
class Player extends Thread{
    private CyclicBarrier waitPoint;
    private String name;

    public Player(CyclicBarrier waitPoint, String name) {
        this.waitPoint = waitPoint;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Player " + name + " is ready ");
        try {
            waitPoint.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}


class MyException1 extends Exception{

}
class MyException2 extends Exception{

}






class MyAtomicInt{
    int x;

    public int incrementAndGet(){
        int temp;
        synchronized (this){
            x++;
            temp = x++;
        }
        return temp;
    }
}


