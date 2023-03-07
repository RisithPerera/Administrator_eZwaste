package com.base.list;

public class BufferQueue{

    private static class Node{
        private String data;
        private Node next;
        private Node(String data){
            this.data = data;
        }
    }

    private Node head; //remove things from here
    private Node tail; //add things here

    public boolean isEmpty(){
        return head == null;
    }

    public String peek(){
        return head.data;
    }

    public void add(String data){
        Node node = new Node(data);
        if(tail != null){
            tail.next = node;
        }
        tail = node;
        if(head == null){
            head = node;
        }
    }

    public String remove(){
        if(head == null) return null;
        String data = head.data;
        head = head.next;
        if(head == null){
            tail = null;
        }
        return data;
    }

    public String toString(){
        String queue = "[";
        Node cur = head;
        while(cur != null){
            queue += cur.data+",";
            cur = cur.next;
        }
        return queue+"]";
    }
}
