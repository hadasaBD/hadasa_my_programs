package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int k = 7;
        int n = 20;
        Integer[][] matrix = new Integer[k][];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < k; i++) {
            Integer[] sorted = new Integer[n];
            int x = 10;
            for (int j = 0; j < n; j++) {
                int r = random.nextInt(8);
                sorted[j] = x + r;
                x = sorted[j];
            }
            matrix[i] = sorted;
        }

        /*
        Integer[] arr = new Integer[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000);
        }
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 >  o2)
                    return 1;
                if(o1 < o2)
                    return -1;
                return 0;
            }
        });
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        */

        //mergeSortedArrays(matrix);

        Node<Integer> n1 = new Node<>(18);
        Node<Integer> n2 = new Node<>(8);
        Node<Integer> n3 = new Node<>(19);
        Node<Integer> n4 = new Node<>(21);
        Node<Integer> n5 = new Node<>(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        Node sorted = bubbleSort(n1);
        printLinkedList(sorted);


    }


    static void mergeSortedArrays(Integer[][] matrix){
        Element[] firstOfEach = new Element[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            firstOfEach[i] = new Element(matrix[i][0], i, 0);
        }
        MinHeap minHeap = new MinHeap(firstOfEach);
        while(minHeap.getSize() > 0){
            Element minElement = (Element) minHeap.extractMin();
            System.out.print(minElement.value + " ");
            if(minElement.col < matrix[minElement.row].length - 1){
                Element element =
                        new Element(matrix[minElement.row][minElement.col+1],
                        minElement.row, minElement.col+1);
                minHeap.insert(element);
            }
        }
        System.out.println();
    }


    static Node<Integer> bubbleSort(Node<Integer> node){
        Node<Integer> anchor = new Node<>(123);
        anchor.next = node;
        boolean isSorted = false;
        int size = 0;
        Node<Integer> current = node;
        while(current != null){
            size++;
            current = current.next;
        }
        int upTo = size-1;
        current = anchor.next;
        Node<Integer> previous = anchor;
        while(!isSorted){
            isSorted = true;
            for (int i = 0; i < upTo; i++) {
                if(current.value > current.next.value){

                    Node<Integer> temp = current.next.next;
                    current.next.next = current;
                    previous.next = current.next;
                    current.next = temp;
                    isSorted = false;
                }
                previous = previous.next;
                current = previous.next;

            }
            upTo--;
            previous = anchor;
            current = previous.next;
        }
        return anchor.next;
    }



    static void printMatrix(Integer[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void checkMinHeap() {
        Integer[] nums = new Integer[1000];
        Random random = new Random(System.currentTimeMillis());
        Integer min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(1000);
            if(nums[i] < min)
                min = nums[i];
        }
        MinHeap minHeap = new MinHeap(nums);
        if(min != minHeap.getMin() || min != minHeap.extractMin()){
            System.out.println("extractMin or getMin error");
        }
        minHeap.insert(random.nextInt(1000));
        minHeap.extractMin();
        minHeap.insert(random.nextInt(1000));
        minHeap.extractMin();
        minHeap.insert(random.nextInt(1000));
        minHeap.extractMin();
        minHeap.extractMin();
        minHeap.extractMin();
        minHeap.extractMin();
        System.out.println(MinHeap.isValidMinHeap(minHeap.arr, 0, minHeap.size));
    }

    public static void generateLinkedListOfLists(){
        Node<Integer> n1 = new Node<>(5);
        Node<Integer> n2 = new Node<>(8);
        Node<Integer> n3 = new Node<>(9);
        Node<Integer> n4 = new Node<>(11);
        Node<Integer> n5 = new Node<>(19);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        Node<Node<Integer>> head1 = new Node<>(n1);
        Node<Integer> n6 = new Node<>(2);
        Node<Integer> n7 = new Node<>(7);
        Node<Integer> n8 = new Node<>(28);
        Node<Integer> n9 = new Node<>(39);
        Node<Integer> n10 = new Node<>(42);
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;
        n9.next = n10;
        Node<Node<Integer>> head2 = new Node<>(n6);
        Node<Integer> n11 = new Node<>(14);
        Node<Integer> n12 = new Node<>(18);
        Node<Integer> n13 = new Node<>(22);
        Node<Integer> n14 = new Node<>(91);
        Node<Integer> n15 = new Node<>(105);
        n11.next = n12;
        n12.next = n13;
        n13.next = n14;
        n14.next = n15;
        Node<Node<Integer>> head3 = new Node<>(n11);
        Node<Integer> n16 = new Node<>(2);
        Node<Integer> n17 = new Node<>(15);
        Node<Integer> n18 = new Node<>(17);
        Node<Integer> n19 = new Node<>(29);
        Node<Integer> n20 = new Node<>(39);
        n16.next = n17;
        n17.next = n18;
        n18.next = n19;
        n19.next = n20;
        Node<Node<Integer>> head4 = new Node<>(n16);
        head1.next = head2;
        head2.next = head3;
        //head3.next = head4;


        Node<Integer> merged = mergeLinkedListOfLinkedList2(head1);
        printLinkedList(merged);
    }


    public static Node<Integer> mergeLinkedListOfLinkedList(Node<Node<Integer>> head){
        if(head == null)
            return null;
        while (head.next != null){
            head.value = merge(head.value, head.next.value);
            head.next = head.next.next;
        }
        return head.value;
    }

    public static Node<Integer> mergeLinkedListOfLinkedList2(Node<Node<Integer>> head){
        if(head == null)
            return null;
        if(head.next == null)
            return head.value;

        Node<Node<Integer>> temp = new Node<>(null);
        Node<Node<Integer>> headResult = temp;
        while (head != null && head.next != null){
            temp.next = new Node<>(merge(head.value, head.next.value));
            head = head.next.next;
            temp = temp.next;
        }
        if(head != null){
            temp.next = head;
        }
        return mergeLinkedListOfLinkedList2(headResult.next);
    }




    public static void printLinkedList(Node node){
        while(node != null){
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static <T extends Comparable<T>> Node<T> merge(Node<T> a, Node<T> b){

        Node<T> anchor = new Node<>(null);
        Node<T> tail = anchor;
        while(true){
            if(a == null) {
                //if either list runs out, use the other one.
                tail.next = b;
                break;
            }else if (b == null){
                tail.next = a;
                break;
            }
            if(b.value.compareTo(a.value) >= 0){
                tail.next = a;
                a = a.next;
            }else{
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        return anchor.next;
    }


}
