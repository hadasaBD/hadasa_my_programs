package com.company;


import java.util.Random;

public class Main {

    public static int findStartingStation2(Station[] stations) {

        int sum=0,position=0;
        for (int i = 0; i <stations.length ; i++) {
            sum += stations[i].charge;
            sum -= stations[i].distanceToNext;
            if(sum<0){
                position=i+1;
                sum=0;
            }
        }
        return position;
    }

    public static void main(String[] args) {



    }


    static boolean isEvenFirst(int[] arr){
        boolean found = false;
        for (int i = 0; i < arr.length - 1; i++) {
            if(arr[i] % 2 == 0){
                if(found)
                    return false;
            }else{
                found = true;
            }
        }
        return true;
    }



    //{1, 2, 3}
    //{1} {1,2}  {1,2,3},
    //{2} {2,3}
    //{3}


    //(n-i)  +   (n-i)*i  = (n-i)*(i+1)
    public static long subArraySum(int[] arr){
        long result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += (arr[i] * (i+1) * (arr.length-i));
        }
        return result;
    }

    public static long subArraySum2(int[] arr){
        long result = 0;
        final int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                for (int k = i; k <= j; k++) {
                    result += arr[k];
                }
            }
        }
        return result;
    }


    public static boolean isMountain(int[] arr){
        boolean foundPeak = false;
        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i] < arr[i+1]){
                if(foundPeak)
                    return false;
            }else if(arr[i] > arr[i+1]){
                foundPeak = true;
            }else{
                return false;
            }
        }
        return true;
    }

    static int findPeak(int[] arr, int from, int to){
        if(from == to)
            return from;
        if(to-from==1){
            if(arr[from] > arr[to])
                return from;
            return to;
        }
        int middle = from + (to-from)/2;
        boolean greaterThanMyLeft = arr[middle] > arr[middle-1];
        boolean greaterThanMyRight = arr[middle] > arr[middle+1];
        if(greaterThanMyLeft && greaterThanMyRight)
            return middle;
        if(greaterThanMyLeft && !greaterThanMyRight)
            return findPeak(arr, middle + 1, to);
        else
            return findPeak(arr, from, middle - 1);
    }


    static void arrangeAllEvenAtStart(int[] arr){
        int i=-1, temp = 0;
        for (int j = 0; j < arr.length; j++) {
            if(arr[j]%2 == 0 ){
                i++;
                if(j!=i) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }

    static void arrangeAllEvenAtStart2(int[] arr){
        int i=-1, temp = 0;
        int j=arr.length;
        while(true) {
            do {
                j--;
            } while (j > -1 && arr[j] % 2 != 0);
            do {
                i++;
            } while (i < arr.length && arr[i] % 2 == 0);

            if (i < j) {
                temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            } else {
                return;
            }

        }
    }

    public static int findStartingStation(Station[] stations){
        boolean validPath;
        for (int i = 0; i < stations.length; i++) {
            int sum = 0;
            int j=i;
            validPath = true;
            do {
                sum += stations[j].charge;
                sum -= stations[j].distanceToNext;
                if(sum<0){
                    validPath = false;
                    break;
                }
                j++;
                if(j==stations.length)
                    j=0;
            }while(j != i);
            if(validPath)
                return i;
        }
        return -1;
    }




}

class Station{

    int charge;
    int distanceToNext;

    public Station(int charge, int distanceToNext) {
        this.charge = charge;
        this.distanceToNext = distanceToNext;
    }
}
