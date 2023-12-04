import binaryTree.BinaryTree;
import binaryTree.Node;
import queue.PriorityQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static String getCodeTable(Node node, String binary, HashMap<String, String> codeTable) {
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            codeTable.put("" + node.getCharacter(), binary);
            return binary;
        }
        else if (node.getLeftChild() != null && node.getRightChild() == null) {
            binary += "0";
            binary = getCodeTable(node.getLeftChild(), binary, codeTable);
            binary = binary.substring(0, binary.length() - 1);
        }
        else if (node.getLeftChild() == null && node.getRightChild() != null) {
            binary += "1";
            binary = getCodeTable(node.getRightChild(), binary, codeTable);
            binary = binary.substring(0, binary.length() - 1);
        }
        else if (node.getLeftChild() != null && node.getRightChild() != null) {
            binary += "0";
            binary = getCodeTable(node.getLeftChild(), binary, codeTable);
            binary = binary.substring(0, binary.length() - 1);

            binary += "1";
            binary = getCodeTable(node.getRightChild(), binary, codeTable);
            binary = binary.substring(0, binary.length() - 1);
        }
        return binary;
    }

    public static void main(String[] args) throws FileNotFoundException{
        // read document
        File file = new File("huffman_input.txt");
        Scanner scan = new Scanner(file);
        String text = "";
        while (scan.hasNextLine()) {
            text += scan.nextLine();
        }
        scan.close();
        
        // get character frequencies
        

        PriorityQueue charQ = new PriorityQueue();
        BinaryTree huffmanTree = new BinaryTree();
        HashMap<String, String> codeTable = new HashMap<String, String>();

        charQ.enqueue(new Node(2, 'h', null));
        charQ.enqueue(new Node(2, 'e', null));
        charQ.enqueue(new Node(3, 'l', null));
        charQ.enqueue(new Node(5, 'o', null));
        charQ.enqueue(new Node(2, 'w', null));
        charQ.enqueue(new Node(2, 'r', null));
        charQ.enqueue(new Node(2, 'd', null));
        charQ.enqueue(new Node(5, ' ', null));
        charQ.enqueue(new Node(1, 'a', null));
        charQ.enqueue(new Node(1, 'y', null));
        charQ.enqueue(new Node(1, 'u', null));
        charQ.enqueue(new Node(1, 'i', null));
        charQ.enqueue(new Node(1, 'n', null));
        charQ.enqueue(new Node(1, 'g', null));
        charQ.enqueue(new Node(1, '?', null));

        while (charQ.getSize() > 1) {
            Node small1 = charQ.dequeue();
            Node small2 = charQ.dequeue();
            Node newNode = new Node(small1.getData() + small2.getData(), '\0', null);
            newNode.setLeftChild(small1);
            newNode.setRightChild(small2);
            charQ.enqueue(newNode);
        }

        huffmanTree.setRoot(charQ.dequeue());

        getCodeTable(huffmanTree.root, "", codeTable);

        String encodedText = "";
        for (int i = 0; i < text.length(); i++) {
            encodedText += codeTable.get(text.substring(i, i + 1));
        }
        System.out.println(encodedText);
    }
}

/*hello world how are you doing?
 * 
 *                                          30
 *                                     /           \
 *                                   12               18
 *                                /    \              /       \
 *                             5(o)     7            8             10  
 *                                    /  \         /     \         /  \
 *                                 3(l)  4        4       4        5   5(sp)
 *                                     /   \     / \     / \      /   \
 *                                  2(w)  3(e) 2(d)2(r) 2     2   2(h)   3 
 *                                                     / \    / \       /   \
 *                                                 1(n) 1(i) 1(?) 1(g) 1(a)  2
 *                                                                          / \
 *                                                                       1(u)  1(y)
 * 
 *  
  */                                                   