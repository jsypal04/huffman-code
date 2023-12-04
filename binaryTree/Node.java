package binaryTree;

public class Node {
    private int data;
    private char character;
    private Node parent;
    private Node leftChild;
    private Node rightChild;

    public Node(int data, char character, Node parent) {
        this.data = data;
        this.character = character;
        this.parent = parent;
        this.leftChild = null;
        this.rightChild = null;
    }

    public int getData() {
        return this.data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public char getCharacter() {
        return this.character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public Node getLeftChild() {
        return this.leftChild;
    }

    public void setLeftChild(Node newNode) {
        this.leftChild = newNode;
    }

    public Node getRightChild() {
        return this.rightChild;
    }

    public void setRightChild(Node newNode) {
        this.rightChild = newNode;
    }

    public Node getParent() {
        return this.parent;
    }

    public void print() {
        System.out.println(this.data);
    }
}
