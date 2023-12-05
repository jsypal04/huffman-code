package queue.linkedList;

import queue.Node;

// class to be a link in a linked list
public class Link {
    private Node data;
    private Link next; // next link in the list

    public Link(Node item) {
        this.data = item;
        this.next = null;
    }

    // getter and setter methods for Link's fields
    public void setNext(Link newLink) {
        this.next = newLink;
    }

    public Link getNext() {
        return this.next;
    }

    public Node getNode() {
        return this.data;
    }

    public int getData() {
        return this.data.getData();
    }

    public char getCharacter() {
        return this.data.getCharacter();
    }
}
