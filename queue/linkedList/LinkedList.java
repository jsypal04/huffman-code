package queue.linkedList;

import binaryTree.Node;

// class to manage the linked list
public class LinkedList {
    private Link first; // points at first link
    public Link last; // points at last link

    public LinkedList() {
        this.first = null;
        this.last = null;
    }

    /**
     * @return - returns true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.first == null;
    }

    /**
     * @param index - the index from the front of the item to be retrieved
     * @return - the Link object at that index, null if the list is empty
     */
    public Link get(int index) {
        if (this.first != null) {
            int i = 0; // keeps track of the index of the Links
            Link current = this.first;
            while (i < index) {
                current = current.getNext();
                i++;
            }
            return current;
        }  
        return null; 
    }

    public Link search(int key) {
        Link currentLink = this.first;
        while (currentLink.getData() != key) {
            if (currentLink.getNext() == null) {
                return null;
            }
            else {
                currentLink = currentLink.getNext();
            }
        }

        return currentLink;
    } 

    /**
     * @param item - the item to be inserted
     * @param index - the location from the front where the item is to be inserted
     */
    public void insert(Node item, int index) {
        if (index == 0) {
            Link newLink = new Link(item);
            newLink.setNext(this.first);
            this.first = newLink;

            if (newLink.getNext() == null) {
                this.last = newLink;
            }
        }
        else {
            Link current = this.get(index - 1);
            Link newLink = new Link(item);
            newLink.setNext(current.getNext());
            current.setNext(newLink);

            if (newLink.getNext() == null) {
                this.last = newLink;
            }
        }
    }

    /**
     * @param index - the location from the front of the item to be deleted
     */
    public void delete(int index) {
        if (index == 0) {
            this.first = this.first.getNext();
        }
        else {
            Link prior = this.get(index - 1);
            Link current = this.get(index);

            prior.setNext(current.getNext());
        }
    }

    public void print() {
        Link currentLink = this.first;
        while (currentLink != null) {
            System.out.print(currentLink.getData());
            System.out.print("(");
            System.out.print(currentLink.getCharacter());
            System.out.print(") ");
            currentLink = currentLink.getNext();
        }
        System.out.println();
    }

    public Link getFirst() {
        return this.first;
    }
}