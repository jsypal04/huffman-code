import binaryTree.BinaryTree;
import binaryTree.Node;
import queue.PriorityQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static String getCodeTable(Node node, String binary, HashMap<Character, String> codeTable) {
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            codeTable.put(node.getCharacter(), binary);
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
        HashMap<Character, Integer> frequencies = new HashMap<Character, Integer>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!frequencies.containsKey(c)) {
                frequencies.put(c, 1);
            }
            else {
                int frequency = frequencies.get(c);
                frequency++;
                frequencies.put(c, frequency);
            }
        }

        PriorityQueue charQ = new PriorityQueue();
        BinaryTree huffmanTree = new BinaryTree();
        HashMap<Character, String> codeTable = new HashMap<Character, String>();

        for (Character key : frequencies.keySet()) {
            charQ.enqueue(new Node(frequencies.get(key), key, null));
        }

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

        // print the code table
        for (Character key : codeTable.keySet()) {
            System.out.print(key + ": ");
            System.out.println(codeTable.get(key));
        }

        String encodedText = "";
        for (int i = 0; i < text.length(); i++) {
            encodedText += codeTable.get(text.charAt(i));
        }
    }
}                                                 