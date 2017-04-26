package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }

    public static void reverseStack(Stack stack){
        Stack stack1 = new ArrayStack();
        while(!stack.isEmpty()){
            stack1.push(stack.pop());
        }
        Stack stack2 = new ArrayStack();
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        while (!stack2.isEmpty()){
            stack.push(stack2.pop());
        }

    }

    public static void remove(Stack stack, Object obj){
        Stack temp = new ArrayStack();
        while(!stack.isEmpty()){
            Object o = stack.pop();
            if(!obj.equals(o)) {
                temp.push(o);
            }else{
                //break;
            }
        }
        while (!temp.isEmpty())
            stack.push(temp.pop());
    }

    public void sortStack(Stack<Comparable> stack){

    }
}










