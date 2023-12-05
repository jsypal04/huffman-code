package queue;

import queue.linkedList.SortedList;

public class PriorityQueue {
    private SortedList qList;
    private int size;
    
    public PriorityQueue() {
        this.qList = new SortedList();
        this.size = 0;
    }

    public void enqueue(Node item) {
        this.qList.insert(item);
        this.size++;
    }

    public Node dequeue() {
        Node smallest = this.qList.getFirst().getNode();
        this.qList.delete(0);
        this.size--;
        return smallest;
    }

    public Integer peek() {
        try {
            return this.qList.getFirst().getData();
        }
        catch (Exception NullPointerException) {
            return null;
        }
    }

    public void print() {
        System.out.print("(front--->rear): ");
        this.qList.print();
    }

    public boolean isEmpty() {
        return this.qList.isEmpty();
    }

    public int getSize() {
        return this.size;
    }

}
