import queue.Node;
import queue.PriorityQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    // method to get the code table from the huffman tree
    public static String getCodeTable(Node node, String binary, HashMap<Character, String> codeTable) {

        // base case: if the node is a leaf, put it's character in the code table mapped to the binary string
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            codeTable.put(node.getCharacter(), binary);
            return binary;
        }

        // left child case: add a 0 to the binary string and call the method on the left child
        else if (node.getLeftChild() != null && node.getRightChild() == null) {
            binary += "0";
            binary = getCodeTable(node.getLeftChild(), binary, codeTable);
            binary = binary.substring(0, binary.length() - 1); // removes the last number of the binary string
        }

        // right child case: add a 1 to the binary string and call the method on the right child
        else if (node.getLeftChild() == null && node.getRightChild() != null) {
            binary += "1";
            binary = getCodeTable(node.getRightChild(), binary, codeTable);
            binary = binary.substring(0, binary.length() - 1);
        }

        // two children case: go left then right
        else if (node.getLeftChild() != null && node.getRightChild() != null) {
            binary += "0";
            binary = getCodeTable(node.getLeftChild(), binary, codeTable);
            binary = binary.substring(0, binary.length() - 1);

            binary += "1";
            binary = getCodeTable(node.getRightChild(), binary, codeTable);
            binary = binary.substring(0, binary.length() - 1);
        }
        // the return for the recursive cases
        return binary;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // initialize main data structures
        PriorityQueue charQ = new PriorityQueue();
        HashMap<Character, String> codeTable = new HashMap<Character, String>();
        HashMap<Character, Integer> frequencies = new HashMap<Character, Integer>();

        // read document
        File file = new File("huffman_input.txt");
        Scanner scan = new Scanner(file);
        String text = "";
        while (scan.hasNextLine()) {
            text += scan.nextLine();
        }
        scan.close();
        
        // get character frequencies
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

        // for each key in the frequencies hashmap, enqueue it in the priority queue
        for (Character key : frequencies.keySet()) {
            charQ.enqueue(new Node(frequencies.get(key), key, null));
        }

        // the loop to create the huffman tree
        while (charQ.getSize() > 1) {
            // dequeue the two smallest items
            Node small1 = charQ.dequeue();
            Node small2 = charQ.dequeue();
            // create a new node with those items as its children
            Node newNode = new Node(small1.getData() + small2.getData(), '\0', null);
            newNode.setLeftChild(small1);
            newNode.setRightChild(small2);
            // enqueue the new node
            charQ.enqueue(newNode);
        }

        // set the root of the huffman tree to the last node in the priority queue
        // huffmanTree.setRoot(charQ.dequeue());

        getCodeTable(charQ.dequeue(), "", codeTable);

        // print the code table
        for (Character key : codeTable.keySet()) {
            if (key == ' ') {
                System.out.print("sp: ");
            }
            else {
                System.out.print(key + ": ");
            }
            System.out.println(codeTable.get(key));
        }

        // encode the text
        String encodedText = "";
        for (int i = 0; i < text.length(); i++) {
            encodedText += codeTable.get(text.charAt(i));
        }

        // write the encoded text to huffman_output.txt
        FileWriter writer = new FileWriter("huffman_output.txt");
        writer.write(encodedText);
        writer.close();
    }
}                                                 