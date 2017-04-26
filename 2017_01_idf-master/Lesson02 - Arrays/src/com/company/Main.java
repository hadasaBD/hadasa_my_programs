package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
	    /*int[] arr = {3, 6, 2, 5};


	    int x = 5;
	    int y = x;
	    x = 6;

	    int[] arr2 = arr;
		arr[0] = 4;
		bubbleSort(arr);
		bubbleSort(arr);

		printArray(arr);

        System.out.println(binarySearch(arr, 0, arr.length-1, 2));*/



	    int[] arr = new int[20];

	    Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(100);
		}

		printArray(arr);
		System.out.println(quickSelect(arr, 0, arr.length-1, 2));
		quickSort(arr, 0, arr.length-1);
		printArray(arr);



	}

    static int binarySearch(int[] arr, int left, int right, int x){
    	if(right >= left){
			int middle = left + (right-left)/2;
			if(arr[middle] == x)
				return middle;
			if(arr[middle] < x)
				return binarySearch(arr, middle+1, right, x);
			return binarySearch(arr, left, middle-1, x);
		}
    	return -1;
	}


	static void bubbleSort(int[] arr){
    	int upTo = arr.length-1;
    	boolean isSorted = false;
    	while(!isSorted){
    		isSorted = true;
			for (int i = 0; i < upTo; i++) {
				if(arr[i] > arr[i+1]){
					int temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
					isSorted = false;
				}
			}
			upTo--;
		}
	}

	static void insertionSort(int arr[]){
		for (int i = 1; i < arr.length; i++) {
			int key = arr[i];
			int j = i-1;
			while(j>=0 && arr[j] > key){
				arr[j+1] = arr[j];
				j--;
			}
			arr[j+1] = key;
		}
	}

	static void merge(int[] arr, int l, int m, int r){
		int n1 = m-l+1;
		int n2 = r-m;
		int[] L = new int[n1];
		int[] R = new int[n2];
		for (int i = 0; i < n1; i++) {
			L[i] = arr[l + i];
		}
		for (int i = 0; i < n2; i++) {
			R[i] = arr[m + 1 + i];
		}
		int i=0, j=0;
		int k = l;
		while (i<n1 && j<n2){
			if(L[i] <= R[j]){
				arr[k] = L[i];
				i++;
			}else{
				arr[k] = R[j];
				j++;
			}
			k++;
		}
		while(i<n1){
			arr[k] = L[i];
			i++;
			k++;
		}
		while(j<n2){
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	static void mergeSort(int[] arr, int l, int r){
		if(l < r){
			int m = (l+r)/2;
			mergeSort(arr, l, m);
			mergeSort(arr, m+1, r);
			merge(arr, l, m, r);
		}
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
		Random random = new Random(System.currentTimeMillis());
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
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}


	//{4, 2, 7, 12, 8, 1}  sum =  5
	//min=2 max=19    19-2+1=18
	//{1,0,1,0,0,1,1,0,0,0,1,0,0,0,0,0,0,0}
	// 0 1 2 3 4 5 6 7 8 91011121314151617



	static boolean containsPairWithSumX2(int[] arr, int sum){
		if(arr.length < 100)
			return containsPairWithSumX(arr, sum);
		int min = arr[0];
		int max = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] > max)
				max = arr[i];
			if(arr[i] < min)
				min = arr[i];
		}
		if(max-min > 10000)
			return containsPairWithSumX(arr, sum);
		boolean[] binmap = new boolean[max - min + 1];
		for (int i = 0; i < arr.length; i++) {
			int temp = sum - arr[i];
			if(temp>=min && binmap[temp-min]){
				return true;
			}
			binmap[arr[i]-min] = true;
		}
		return false;
	}

	//O(nlgn)
	static boolean containsPairWithSumX(int[] arr, int sum){
		quickSort(arr, 0, arr.length-1);
		int l = 0,r = arr.length - 1;
		while (l < r){
			int temp = arr[l] + arr[r];
			if(temp == sum)
				return true;
			else if(temp < sum)
				l++;
			else
				r--;
		}
		return false;
	}



    static void printArray(int[] arr){
		System.out.print("{");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if(i<arr.length-1)
				System.out.print(",");
		}
		System.out.println("}");
	}
}















