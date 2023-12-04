package binaryTree;

public class BinaryTree {
    public Node root;

    public BinaryTree() {
        this.root = null;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node find(int key) {

        Node current = this.root;
        while (current != null) {
            if (key < current.getData()) {
                current = current.getLeftChild();
            }
            else if (key > current.getData()) {
                current = current.getRightChild();
            }
            else {
                return current;
            }
        }

        return current;
    }

    /**
     * @param key - the data to be inserted
     * @return - returns 0 if the insertion was successful, -1 if it was unsuccessful
     */
    public int insert(int key) {
        // if the tree is empty, insert into the root
        if (this.root == null) {
            this.root = new Node(key, '\0', null);
            return 0;
        }
        
        Node parent = null;
        Node current = this.root;
        while (current != null) {
            if (key < current.getData()) {
                parent = current;
                current = current.getLeftChild();
            }
            else {
                parent = current;
                current = current.getRightChild();
            }
        }
        
        if (key < parent.getData()) {
            parent.setLeftChild(new Node(key, '\0', parent));
        }
        else {
            parent.setRightChild(new Node(key, '\0', parent));
        }

        return 0;
    }

    public int delete(int key) {
        // find the node to be deleted
        Node node = this.find(key);

        if (node == null) {
            return -1; // key not found
        }

        // leaf case
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            Node parent = node.getParent();
            if (node.getData() < parent.getData()) {
                parent.setLeftChild(null);
            }
            else {
                parent.setRightChild(null);
            }
        }

        // one left child case
        else if (node.getLeftChild() != null && node.getRightChild() == null) {
            Node parent = node.getParent();
            if (node.getData() < parent.getData()) {
                parent.setLeftChild(node.getLeftChild());
            }
            else {
                parent.setRightChild(node.getLeftChild());
            }
        }

        // one right child case
        else if (node.getLeftChild() == null && node.getRightChild() != null) {
            Node parent = node.getParent();
            if (node.getData() < parent.getData()) {
                parent.setLeftChild(node.getRightChild());
            }
            else {
                parent.setRightChild(node.getRightChild());
            }
        }

        // two children case
        else if (node.getLeftChild() != null && node.getRightChild() != null) {
            Node successor = node.getRightChild();
            while (successor.getLeftChild() != null) {
                successor = successor.getLeftChild();
            }
            node.setData(successor.getData());
            
            // delete successor
            if (successor.getRightChild() != null) {
                Node parent = successor.getParent();
                // theoreticall only the first case of this if statement should be needed
                if (successor.getData() < parent.getData()) {
                    parent.setLeftChild(successor.getRightChild());
                }
                else {
                    parent.setRightChild(successor.getRightChild());
                }
            }
            else {
                Node parent = successor.getParent();
                // theoretically on the first case of this if statement should be needed
                if (successor.getData() < parent.getData()) {
                    parent.setLeftChild(null);
                }
                else {
                    parent.setRightChild(null);
                }
            }
        }

        return 0;
    }
}