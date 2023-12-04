package queue.linkedList;

import binaryTree.Node;

public class SortedList extends LinkedList {

    public SortedList() {
        super();
    }

    public void insert(Node item) {
        Link current = this.getFirst();
        int index = 0;
        while (current != null && current.getData() < item.getData()) {
            current = current.getNext();
            index++;
        }
        this.insert(item, index);
    }
}
