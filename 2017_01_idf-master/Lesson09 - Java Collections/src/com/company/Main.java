package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        //Important Abstract Classes and Interfaces regarding Collections:
        //Iterable
        //Collection
        //List
        //Set, SortedSet, NavigableSet
        //Queue, Deque
        //Map
        //Map<Integer, User> map;
        //SortedMap, NavigableMap



        //Important concrete classes regarding collection
        //ArrayList
        //LinkedList
        //HashSet
        //TreeSet
        //HashMap
        //TreeMap
        //PriorityQueue

        Set<Point> points = new HashSet<>();
        Point p1 = new Point(4, 5);
        Point p2 = new Point(3, 4);
        points.add(p1);
        //points.add(p2);


        Map<String, User> users = new HashMap<>();
        User user1 = new User("hadasa", "12345");
        User user2 = new User("zofiya", "123456");
        User user3 = new User("zofiya", "121212");
        users.put(user1.getUserName(), user1);
        users.put(user2.getUserName(), user2);
        users.put(user3.getUserName(), user3);
        users.put("elad", null);
        System.out.println(users.size());
        Iterator<User> allTheUsers = users.values().iterator();
        while(allTheUsers.hasNext()){
            User u = allTheUsers.next();
        }
        //User user3 = users.get(user1.getUserName());


    }
}
class User{
    private String userName, password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}








