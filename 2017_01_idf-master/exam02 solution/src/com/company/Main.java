package com.company;

import java.security.InvalidParameterException;
import java.util.Random;

public class Main {

    static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        int[] arr = {12, 4, 7, 9, 2, 23, 25, 41, 30, 40, 28, 42, 30, 44, 48, 43, 50};
        int m = 7;
        System.out.println(findMinDiff(arr, m));
    }

    static boolean searchInSortedMatrix(int[][] mat, int x){
        int n = mat.length;
        int i=0, j=n-1;//set the index to top right element
        while(i<n && j>=0){
            if(mat[i].length != n)
                throw new InvalidParameterException("row " + i +
                        " is not length: " + n);
            if(mat[i][j] == x)
                return true;
            if(mat[i][j] > x)
                j--;
            else
                i++;
        }
        return false;
    }


    static int findMinDiff(int[] arr, int m){
        if(m==0 || arr.length==0)
            return 0;
        if(arr.length < m)
            return -1;
        quickSort(arr, 0, arr.length-1);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i+m-1 < arr.length; i++) {
            int diff = arr[i+m-1] - arr[i];
            if(diff < minDiff)
                minDiff = diff;
        }
        return minDiff;
    }

    static int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if(arr[j] <= pivot){
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        i++;
        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;
        return i;
    }

    static int randomizedPartition(int[] arr, int low, int high){
        if(high == low)
            return low;
        int pos = random.nextInt(high-low) + low;
        int temp = arr[high];
        arr[high] = arr[pos];
        arr[pos] = temp;
        return partition(arr, low, high);
    }

    static int quickSelect(int[] arr, int l, int r, int k){
        if(k>0 && k <= r-l+1){
            int pos = randomizedPartition(arr, l, r);
            if(pos-l == k-1)
                return arr[pos];
            if(pos-l > k-1)
                return quickSelect(arr, l, pos-1, k);
            return quickSelect(arr, pos+1, r, k-pos+l-1);
        }
        return Integer.MAX_VALUE;
    }

    static void quickSort(int[] arr, int low, int high){
        if(low < high){
            int pi = randomizedPartition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
}
