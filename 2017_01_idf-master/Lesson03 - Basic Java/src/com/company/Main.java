package com.company;

import com.sun.org.apache.xpath.internal.operations.Div;

public class Main {

    public static void main(String[] args) {
	    //drawRectangle(5, 4, 10, 6);

        /*
        //int[] arr2 = {6, 2, 4, 8, 3};
        int[][] arr2 = {{2, 3}, {6, 7}, {8, 1, 4}, null};
        int[] arr3 = {4, 5, 6};
        arr2[3] = arr3;
        arr2[0] = new int[]{8, 3, 2, 5};
        int[][] arr4 = new int[5][];
        arr4[0] = new int[7];

        //int[] arr5 = new int[];


        int[] arr5 = new int[21];
        int[][] arr = new int[7][3];
        arr[6][1] = 1234;
        int x = arr[6][1];
        System.out.println(arr[0].length);


        TwoDimensionalArray matrix =
                new TwoDimensionalArray(4, 3);
        matrix.set(1, 2, 100);
        System.out.println(matrix.get(1, 2));

        */

        //drawRectangle(3, 2, 10, 5);
        //drawRectangle(5, 3, 10, 5);

        /*Canvas canvas = new Canvas(60, 50);
        canvas.drawRectangle(3, 2, 16, 5);
        canvas.drawRectangle(5, 3, 16, 5);
        canvas.render();*/


        /*MyPriorityQueue myPriorityQueue = new MyPriorityQueue();
        myPriorityQueue.insert(45);
        myPriorityQueue.insert(12);
        myPriorityQueue.insert(80);
        myPriorityQueue.insert(15);
        myPriorityQueue.insert(8);
        myPriorityQueue.insert(2);

        while(myPriorityQueue.getSize() > 0){
            System.out.println(myPriorityQueue.extractMax());
        }*/

        int[] arr = {45, 12, 80, 15, 8, 2};

        heapSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+",");
        }


    }

    static void heapSort(int[] arr){
        MyPriorityQueue myPriorityQueue = new MyPriorityQueue(arr);
        for (int i = 0; i < arr.length; i++) {
            int max = myPriorityQueue.extractMax();
            arr[arr.length-1-i] = max;
        }
    }





    static DivisionResult divide(int x, int y){
        if(y==0)
            return null;
        int result = 0;
        int sum = y;
        int largestProductOfY = 0;
        while(sum <= x){
            sum += y;
            largestProductOfY += y;
            result++;
        }
        DivisionResult divisionResult = new DivisionResult();
        divisionResult.times = result;
        divisionResult.remainder = x - largestProductOfY;
        //distance(x,largestProductOfY)
        return divisionResult;
    }




    static void drawRectangle(int x, int y, int width, int height){
        for (int i = 0; i < y; i++)
            System.out.println();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < x; j++)
                System.out.print(" ");
            for (int j = 0; j < width; j++) {
                System.out.print(i==0 || i==height-1 || j == 0 ||
                        j == width-1 ? "*" : " ");
            }
            System.out.println();
        }
    }

}
class DivisionResult{
    int times;
    int remainder;
}

class TwoDimensionalArray{
    //private int[][] arr;
    private int[] arr;
    private int rows;

    public TwoDimensionalArray(int rows, int columns){
        //arr = new int[rows][columns];
        arr = new int[rows * columns];
        this.rows = rows;
    }
    public void set(int row, int column, int value){
        //if(row >= arr.length || column >= arr[row].length)
        if(row >= arr.length || column >= columns())
            throw new IndexOutOfBoundsException();
        //arr[row][column] = value;
        arr[pos(row, column)] = value;
    }
    public int get(int row, int column){
        if(row >= arr.length || column >= columns())
            throw new IndexOutOfBoundsException();
        //return arr[row][column];
        return arr[pos(row, column)];
    }

    private int pos(int row, int column){
        return row * columns() + column;
    }

    public void print(){
        int col = columns();
        /*
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            if(i % col == 0)
                System.out.println();
        }*/
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(arr[pos(i, j)]);
            }
            System.out.println();
        }
    }

    public int rows(){
        return this.rows;
    }

    public int columns(){
        return arr.length / rows;
    }


    static void printMoreThankNd4(int[] arr){
        int k = 4 - 1;
        ElementCount[] elementCounts = new ElementCount[k];

        //Step 1 - initializing our temporary counting array
        for (int i = 0; i < elementCounts.length; i++)  // n iterations
            elementCounts[i] = new ElementCount(123, 0);
        //Step 2 - process all the elements in the array
        for (int i = 0; i < arr.length; i++) { // n * (3 + 3 + 3) = 9n
            int j;
            for (j = 0; j < k; j++) {
                if(arr[i] == elementCounts[j].element){
                    //the element arr[i] exists in eleCount
                    //so we increase its counter
                    elementCounts[j].counter++;
                    break;
                }
            }
            if(j == k){ //the element arr[i] was not found in eleCount
                int l;
                for (l = 0; l < k; l++) {
                    if(elementCounts[l].counter == 0){
                        elementCounts[l].element = arr[i];
                        elementCounts[l].counter = 1;
                        break;
                    }
                }
                if(l==k){ //there's no room for the new element
                    //so we decrease by one each counter
                    for (l = 0; l < k; l++) {
                        elementCounts[l].counter--;
                    }
                }
            }
        }

        //Step 3 - check the actual count
        for (int i = 0; i < k; i++) {  // 3 * n
            int actualCount = 0;
            for (int j = 0; j < arr.length; j++) {
                if(arr[j] == elementCounts[i].element)
                    actualCount++;
            }
            if(actualCount > arr.length/(k+1))
                System.out.println(elementCounts[i].element);
        }



    }


    /*static int findSmallLarge(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int x = arr[i];
            boolean f = false;
            for (int j = 0; j < i; j++) {
                if(arr[j]>x){
                    f = true;
                    break;
                }
            }
            if(f)
                continue;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]<x){
                    f = true;
                    break;
                }
            }
            if(!f)
                return i;
        }
        return -1;
    }*/


    static int max(int x, int y){
        if(x>y)
            return x;
        return y;
    }

    static int min(int x, int y){
        if(x<y)
            return x;
        return y;
    }

    static int findSmallLarge(int[] arr){
        /*
            1. Create two arrays, leftMax and rightMin
            2. Traverse the input array from left to right,
               and fill leftMax such that leftMax[i] will contains the
               max element up to index i in arr.
            3. Do the same with rightMin but from right left.
            4. Traverse the array, if an element in index i,
               if greater than leftMax[i] and also smaller than
               rightMin[i] then we've found such element.
         */

        // 1    5  4  7  9  6   -1
        //               -1   -1    9999

        // -999 1  5  5  7  9   9

        int[] leftMax = new int[arr.length];
        leftMax[0] = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            leftMax[i] = max(leftMax[i-1], arr[i-1]);
        }
        int rightMin = Integer.MAX_VALUE;
        for (int i = arr.length-1; i >= 0; i--) {
            if(leftMax[i] < arr[i] && rightMin > arr[i])
                return i;
            rightMin = min(rightMin, arr[i]);
        }
        return -1;
    }


}

class ElementCount{
    int element;
    int counter;

    public ElementCount(int element, int counter) {
        this.element = element;
        this.counter = counter;
    }
}