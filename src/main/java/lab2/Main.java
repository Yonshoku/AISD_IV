package lab2;

import lab2.structures.LinkedList;
import lab1.structures.Map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{
        if (args.length != 0) {
            String[] strings = args;
        }
        // Create reader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Get number of strings
        System.out.print("Enter number of strings: ");
        int num = Integer.parseInt(br.readLine());

        // Encode given strings and print tables
        for (int i = 0; i < num; i++) {
            System.out.print("Enter the string: ");
            String s = br.readLine();

            double initSize = s.length() * 8;
            double newSize;

            // Get frequencies
            Map<Character, Integer> frequencies = getFrequencies(s);
            Tree tree = getHoffmanTree(frequencies);

            String encoded = tree.encode(s);
            System.out.println("Encoded: " + encoded);
            String decoded = tree.decode(encoded);
            System.out.println("Decoded: " + decoded);

            newSize = String.join("", encoded.split(" ")).length();
            System.out.println("Initial memory: " + initSize);
            System.out.println("New size: " + newSize);
            System.out.println("Compression ratio: " + String.format("%.2f", initSize / newSize));

            tree.printTable();
        }

    }

    static Map<Character, Integer> getFrequencies(String s) {
        Map<Character, Integer> frequencies = new Map<>();
        Integer f;

        for (int i = 0; i < s.length(); i++) {
            f = frequencies.find(s.charAt(i));

            if (f != null) { // Increment char counter
                frequencies.remove(s.charAt(i));
                frequencies.insert(s.charAt(i), f+=1);
            } else {
                frequencies.insert(s.charAt(i), 1);
            }
        }

        return frequencies;
    }

    static Tree getHoffmanTree(Map<Character, Integer> frequencies) {
        LinkedList<Node> nodes = new LinkedList<>();

        // Get sorted list of nodes by frequencies
        for (Character c : frequencies.getKeys()) {
            nodes.insertByPriority(new Node(c, frequencies.find(c)));
        }

        // Create Hoffman tree
        int nodesSize = nodes.get_size();

        for (int j = 0; j < nodesSize - 1; j++) {
            Node newNode = new Node();
            Node left = nodes.at(0);
            Node right = nodes.at(1);

            nodes.pop_front();
            nodes.pop_front();

            newNode.left = left;
            newNode.right = right;
            left.parent = right.parent = newNode;
            newNode.frequency = left.frequency + right.frequency;

            nodes.insertByPriority(newNode);
        }

        Node root = nodes.at(0);

        return new Tree(root);
    }

}



